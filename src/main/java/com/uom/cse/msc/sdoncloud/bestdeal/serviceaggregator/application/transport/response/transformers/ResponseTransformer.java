
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainMatchingProductsResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityInterface;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ResponseTransformer implements ResponseEntityInterface {
    @Override
    public Map transform(Object entity) {
        DomainMatchingProductsResponseEntity response = (DomainMatchingProductsResponseEntity)entity;

        Map<String,Object> mapping = new HashMap<>();
        mapping.put("resCode",response.getResCode());
        mapping.put("resDesc",response.getResDesc());
        mapping.put("data",response.getData());

        return mapping;
    }
}
