package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.request.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ImageRequest {

    @NotNull
    private String imageBase64;
}


