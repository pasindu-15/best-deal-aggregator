package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.externalrequest;

import org.json.JSONArray;

import java.util.List;

public class MatchingProductsRequest {

    private List<String> features;
    private String mainFeature;

    public MatchingProductsRequest(String mainFeature, List<String> features) {
        this.features = features;
        this.mainFeature = mainFeature;
    }

//    @Override
//    public String toString() {
//        return "{ \n   " +
//                "   \"features\": "+features+",\n   " +
//                "   \"mainFeature\": \""+mainFeature+"\" \n" +
//                "} ";
//    }
}
