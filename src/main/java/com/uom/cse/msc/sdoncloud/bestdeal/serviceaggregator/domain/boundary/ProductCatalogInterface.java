package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.FeatureDetection;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.MatchingProducts;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;

public interface ProductCatalogInterface {
    public MatchingProducts getMatchingProducts(FeatureDetection featureDetection) throws WebClientException;

    public DomainProductOffersResponseEntity getProductOffers(DomainProductOffersRequestEntity domainProductOffersRequestEntity) throws WebClientException;

}

