package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Product {
    private Long id;
    private String description;
    private Integer shopCode;
    private String image;
    private String itemName;
    private String itemType;
    private Integer itemCode;
    private String shopName;


}