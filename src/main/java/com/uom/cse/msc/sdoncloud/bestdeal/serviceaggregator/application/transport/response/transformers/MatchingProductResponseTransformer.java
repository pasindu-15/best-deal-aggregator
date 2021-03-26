
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityTransformer;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.MatchingProducts;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MatchingProductResponseTransformer implements ResponseEntityInterface {


    ResponseEntityTransformer responseEntityTransformer = new ResponseEntityTransformer();

    @Override
    public Map transform(Object entity) {

        MatchingProducts products = (MatchingProducts)entity;


        Map<String,Object> mapping = new HashMap<>();
        mapping.put("resCode",products.getResCode());
        mapping.put("resDesc",products.getResDesc());
        mapping.put("data",responseEntityTransformer.transform(products.getData(), new ProductResponseTransform()));

        return mapping;
    }
}

