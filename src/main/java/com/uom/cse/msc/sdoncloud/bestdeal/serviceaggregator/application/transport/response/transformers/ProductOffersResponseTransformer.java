
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductOffersResponseTransformer implements ResponseEntityInterface {
    @Override
    public Map transform(Object entity) throws JsonProcessingException {
        DomainProductOffersResponseEntity response = (DomainProductOffersResponseEntity)entity;

        HashMap<String,Object> mapping = new ObjectMapper().readValue(response.toString(), HashMap.class);

//        Map<String,Object> mapping = new HashMap<>();
//        mapping.put("resCode",response.getResCode());
//        mapping.put("resDesc",response.getResDesc());
//        mapping.put("data",response.getData());

        return mapping;
    }
}

