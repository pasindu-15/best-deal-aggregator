package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.ProductCatalogInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.*;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.externalrequest.MatchingProductsRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class ProductCatalogService implements ProductCatalogInterface {

    @Value("${external-urls.product-catalog.matching-products}")
    String matchingProductsUrl;

    @Value("${external-urls.product-catalog.preferred-product}")
    String preferredProductUrl;

//    @Autowired
//    RestTemplate restTemplate;

    Gson gson = new GsonBuilder().create();

    public MatchingProducts getMatchingProducts(FeatureDetection featureDetection) throws WebClientException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("UUID", MDC.get("UUID"));

        MatchingProductsRequest matchingProductsRequest = new MatchingProductsRequest(featureDetection.getMainFeature(),featureDetection.getFeatures());

        String request = gson.toJson(matchingProductsRequest);

        log.info("Search matching products request : {}",request);


        HttpEntity<?> entity = new HttpEntity<>("",headers);

        String response = restTemplate.exchange(matchingProductsUrl, HttpMethod.POST, entity, String.class).getBody();
        JSONObject jsonRes = new JSONObject(response);

        if(! jsonRes.getString("resCode").equals("00")){
            throw new WebClientException("Product Catalog Web Client Exception!", "99");
        }

        MatchingProducts matchingProducts = new MatchingProducts();
        List<Product> products = new ArrayList<>();
        for(Object ob : jsonRes.getJSONArray("data")){
            JSONObject job = (JSONObject)ob;
            Product p = new Product();
            p.setId(job.getLong("id"));
            p.setDescription(job.getString("description"));
            p.setImage(job.getString("image"));
            p.setItemCode(job.getInt("itemCode"));
            p.setItemName(job.getString("itemName"));
            p.setItemType(job.getString("itemType"));
            p.setShopCode(job.getInt("shopCode"));
            p.setShopName(job.getString("shopName"));
            products.add(p);

        }

        matchingProducts.setData(products);
        matchingProducts.setResCode(jsonRes.getString("resCode"));
        matchingProducts.setResDesc(jsonRes.getString("resDesc"));

        return matchingProducts;
    }

    public DomainProductOffersResponse getProductOffers(Long productId) throws WebClientException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("UUID", MDC.get("UUID"));

        String url =  preferredProductUrl+productId;



        HttpEntity<?> entity = new HttpEntity<>(null,headers);


        String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        JSONObject jsonRes = new JSONObject(response);

        if(! jsonRes.getString("resCode").equals("00")){
            throw new WebClientException("Product Catalog Web Client Exception!", "99");
        }

        DomainProductOffersResponse domainProductOffersResponse = new DomainProductOffersResponse();
        List<Deal> deals = new ArrayList<>();
        for (Object ob: jsonRes.getJSONArray("data")) {
            JSONObject jobj = (JSONObject)ob;
            Deal deal = new Deal();
            deal.setDealId(jobj.getLong("dealId"));
            deal.setProductId(jobj.getLong("productId"));
            deal.setBankCode( jobj.getInt("bankCode"));
            deal.setBankName(jobj.getString("bankName"));
            deal.setCardName(jobj.getString("cardName"));
            deal.setOffer( jobj.getString("offer"));
            deals.add(deal);

        }
        domainProductOffersResponse.setData(deals);
        domainProductOffersResponse.setResCode(jsonRes.getString("resCode"));
        domainProductOffersResponse.setResDesc(jsonRes.getString("resDesc"));

        return domainProductOffersResponse;
    }
}
