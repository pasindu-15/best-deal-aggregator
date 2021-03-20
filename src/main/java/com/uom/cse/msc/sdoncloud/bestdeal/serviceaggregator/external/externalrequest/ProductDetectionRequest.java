package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.externalrequest;

public class ProductDetectionRequest {

    private String imagesBase64;

    public ProductDetectionRequest(String imagesBase64) {
        this.imagesBase64 = imagesBase64;
    }

    @Override
    public String toString() {
        return  "{\n  " +
                "   \"imagesBase64\": [ \""+imagesBase64+"\"]\n  " +
                "}";
    }
}
