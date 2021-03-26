package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Deal {
    private Long dealId;
    private String bankName;
    private Long productId;
    private String cardName;
    private Integer bankCode;
    private String offer;


}
