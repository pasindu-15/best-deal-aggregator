package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.service;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.ProductCatalogInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainFeatureResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainMatchingProductsResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;
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

    public DomainMatchingProductsResponseEntity getMatchingProducts(DomainFeatureResponseEntity domainFeatureResponseEntity) throws WebClientException {

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("UUID", MDC.get("UUID"));

        JSONObject request = new JSONObject(domainFeatureResponseEntity.getData().get(0));

        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(request,headers);

        JSONObject response = restTemplate.exchange(matchingProductsUrl, HttpMethod.POST, entity, JSONObject.class).getBody();

        if(! response.getString("resCode").equals("00")){
            throw new WebClientException("Product Catalog Web Client Exception!", "99");
        }

        DomainMatchingProductsResponseEntity domainMatchingProductsResponseEntity = new DomainMatchingProductsResponseEntity();
        domainMatchingProductsResponseEntity.setData(response.getJSONArray("data"));
        domainMatchingProductsResponseEntity.setResCode(response.getString("resCode"));
        domainMatchingProductsResponseEntity.setResDesc(response.getString("resDesc"));

        return domainMatchingProductsResponseEntity;
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
