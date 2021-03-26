package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponse;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.FeatureDetection;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.MatchingProducts;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;

public interface ProductCatalogInterface {
    public MatchingProducts getMatchingProducts(FeatureDetection featureDetection) throws WebClientException;

    public DomainProductOffersResponse getProductOffers(Long productId) throws WebClientException;

}

