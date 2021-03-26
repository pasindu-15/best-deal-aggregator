package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityTransformer;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductResponseTransform implements ResponseEntityInterface {
    @Override
    public Map transform(Object entity) {
        Product product = (Product)entity;
        Map<String,Object> mapping = new HashMap<>();
        mapping.put("id",product.getId());
        mapping.put("description",product.getDescription());
        mapping.put("shopCode",product.getShopCode());
        mapping.put("image",product.getImage());
        mapping.put("itemName",product.getItemName());
        mapping.put("itemType",product.getItemType());
        mapping.put("itemCode",product.getItemCode());
        mapping.put("shopName",product.getShopName());
        return mapping;
    }
}

