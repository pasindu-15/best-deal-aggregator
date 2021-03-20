package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.service;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.ProductCatalogInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.FeatureDetection;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.MatchingProducts;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.externalrequest.MatchingProductsRequest;
import org.json.JSONObject;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ProductCatalogService implements ProductCatalogInterface {

    @Value("${external-urls.product-catalog.matching-products}")
    String matchingProductsUrl;

    @Value("${external-urls.product-catalog.preferred-product}")
    String preferredProductUrl;

    public MatchingProducts getMatchingProducts(FeatureDetection featureDetection) throws WebClientException {

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("UUID", MDC.get("UUID"));

        MatchingProductsRequest matchingProductsRequest = new MatchingProductsRequest(featureDetection.getMainFeature(),featureDetection.getFeatures());

//        JSONObject request = new JSONObject();
//        request.put("features",featureDetection.getFeatures());
//        request.put("mainFeature",featureDetection.getMainFeature());

        HttpEntity<?> entity = new HttpEntity<>(matchingProductsRequest.toString(),headers);

        String response = restTemplate.exchange(matchingProductsUrl, HttpMethod.POST, entity, String.class).getBody();
        JSONObject jsonRes = new JSONObject(response);

        if(! jsonRes.getString("resCode").equals("00")){
            throw new WebClientException("Product Catalog Web Client Exception!", "99");
        }

        MatchingProducts matchingProducts = new MatchingProducts();
        matchingProducts.setData(jsonRes.getJSONArray("data"));
        matchingProducts.setResCode(jsonRes.getString("resCode"));
        matchingProducts.setResDesc(jsonRes.getString("resDesc"));

        return matchingProducts;
    }

    public DomainProductOffersResponseEntity getProductOffers(DomainProductOffersRequestEntity domainProductOffersRequestEntity) throws WebClientException {

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("UUID", MDC.get("UUID"));

        JSONObject request = new JSONObject().put("productId", domainProductOffersRequestEntity.getProductId());

        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(request,headers);

        JSONObject response = restTemplate.exchange(preferredProductUrl, HttpMethod.POST, entity, JSONObject.class).getBody();

        if(! response.getString("resCode").equals("00")){
            throw new WebClientException("Product Catalog Web Client Exception!", "99");
        }

        DomainProductOffersResponseEntity domainProductOffersResponseEntity = new DomainProductOffersResponseEntity();
        domainProductOffersResponseEntity.setItemName(response.getString("itemName"));
        domainProductOffersResponseEntity.setDealList(response.getJSONArray("dealList"));
        domainProductOffersResponseEntity.setResCode(response.getString("resCode"));
        domainProductOffersResponseEntity.setResDesc(response.getString("resDesc"));

        return domainProductOffersResponseEntity;
    }
}
