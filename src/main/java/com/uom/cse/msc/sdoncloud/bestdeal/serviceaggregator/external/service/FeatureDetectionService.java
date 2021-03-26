package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.FeatureDetectionInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.FeatureDetection;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainImageRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.externalrequest.ProductDetectionRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class FeatureDetectionService implements FeatureDetectionInterface {

    @Value("${external-urls.feature-detection}")
    String url;



    public FeatureDetection getFeatures(DomainImageRequestEntity domainImageRequestEntity) throws WebClientException {

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("UUID", MDC.get("UUID"));

        //Constructing request
//        JSONObject request = new JSONObject();
//        request.put("imagesBase64",new JSONArray().put(domainImageRequestEntity.getImageBase64()));

        ProductDetectionRequest productDetectionRequest = new ProductDetectionRequest(domainImageRequestEntity.getImageBase64());


        log.info("FeatureDetectionService Request : "+productDetectionRequest.toString());


        HttpEntity<?> entity = new HttpEntity<>(productDetectionRequest.toString(),headers);

        String response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

        JSONObject resJson = new JSONObject(response);

        log.info("FeatureDetectionService Response : "+response.toString());

        if(! resJson.getString("resCode").equals("00")){
            throw new WebClientException("Product Detection Web Client Exception!", "99");
        }

        FeatureDetection featureDetection = new FeatureDetection();
        featureDetection.setMainFeature(resJson.getJSONArray("data").getJSONObject(0).getString("mainFeature"));
        List<String> features = new ArrayList<>();
        for (Object ob:resJson.getJSONArray("data").getJSONObject(0).getJSONArray("features")) {
            String f = (String)ob;
            features.add(f);

        }
        featureDetection.setFeatures(features);
        featureDetection.setResDesc(resJson.getString("resCode"));
        featureDetection.setResCode(resJson.getString("resCode"));

        return featureDetection;
    }


}
