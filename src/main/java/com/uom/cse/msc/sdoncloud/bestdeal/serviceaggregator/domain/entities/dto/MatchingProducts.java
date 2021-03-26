package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchingProducts {
    private List<Product> data;
    private String resDesc;
    private String resCode;
}
