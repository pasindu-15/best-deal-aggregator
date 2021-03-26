package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.Deal;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.Product;

import java.util.HashMap;
import java.util.Map;

public class DealResponseTransform implements ResponseEntityInterface {
    @Override
    public Map transform(Object entity) {
        Deal deal = (Deal) entity;
        Map<String,Object> mapping = new HashMap<>();
        mapping.put("dealId",deal.getDealId());
        mapping.put("productId",deal.getProductId());
        mapping.put("bankCode",deal.getBankCode());
        mapping.put("bankName",deal.getBankName());
        mapping.put("cardName",deal.getCardName());
        mapping.put("offer",deal.getOffer());

        return mapping;
    }
}

