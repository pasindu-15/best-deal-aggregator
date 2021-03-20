
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResponseEntityTransformer {
    public Map transform(Object entity, ResponseEntityInterface transformer) throws JsonProcessingException {
        return transformer.transform(entity);
    }

//    public List<Map> transform(List<?> entityList, ResponseEntityInterface transformer){
//        return entityList.stream().map(entity -> {
//            return transformer.transform(entity);
//        }).collect(Collectors.toList());
//    }
}
