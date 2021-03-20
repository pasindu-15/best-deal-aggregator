package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.service;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.FeatureDetectionInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainFeatureResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainImageRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Log4j2
@Service
public class FeatureDetectionService implements FeatureDetectionInterface {

    @Value("${external-urls.feature-detection}")
    String url;

    public DomainFeatureResponseEntity getFeatures(DomainImageRequestEntity domainImageRequestEntity) throws WebClientException {

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("UUID", MDC.get("UUID"));

        //Constructing request
        JSONObject request = new JSONObject();
        request.put("imagesBase64",new JSONArray().put(domainImageRequestEntity.getImageBase64()));

        log.info("FeatureDetectionService: Transformed Request : "+request.toString());

        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(request,headers);

        JSONObject response = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class).getBody();

        log.info("FeatureDetectionService: Transformed Response : "+response.toString());

        if(! response.getString("resCode").equals("00")){
            throw new WebClientException("Product Detection Web Client Exception!", "99");
        }

        DomainFeatureResponseEntity domainFeatureResponseEntity = new DomainFeatureResponseEntity();
        domainFeatureResponseEntity.setData(response.getJSONArray("data"));
        domainFeatureResponseEntity.setResDesc(response.getString("resCode"));
        domainFeatureResponseEntity.setResCode(response.getString("resCode"));

        return domainFeatureResponseEntity;
    }
}
