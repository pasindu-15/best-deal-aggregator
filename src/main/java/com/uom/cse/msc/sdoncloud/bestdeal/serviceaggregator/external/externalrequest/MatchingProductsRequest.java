package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.externalrequest;

import org.json.JSONArray;

import java.util.List;

public class MatchingProductsRequest {

    private String features;
    private JSONArray mainFeature;

    public MatchingProductsRequest(String features, JSONArray mainFeature) {
        this.features = features;
        this.mainFeature = mainFeature;
    }

    @Override
    public String toString() {
        return "{ \n   " +
                "   \"features\": "+features+",\n   " +
                "   \"mainFeature\": \""+mainFeature+"\" \n} ";
    }
}
