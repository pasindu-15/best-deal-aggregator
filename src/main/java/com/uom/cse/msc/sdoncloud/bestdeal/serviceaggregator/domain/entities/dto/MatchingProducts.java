package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchingProducts {
    private JSONArray data;
    private String resDesc;
    private String resCode;
}
