
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityTransformer;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponse;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityInterface;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductOffersResponseTransformer implements ResponseEntityInterface {


    ResponseEntityTransformer responseEntityTransformer = new ResponseEntityTransformer();
    @Override
    public Map transform(Object entity) {
        DomainProductOffersResponse domainProductOffersResponse = (DomainProductOffersResponse) entity;

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("resCode", domainProductOffersResponse.getResCode());
        mapping.put("resDesc", domainProductOffersResponse.getResDesc());
        mapping.put("data", responseEntityTransformer.transform(domainProductOffersResponse.getData(), new DealResponseTransform()));

        return mapping;
    }
}

