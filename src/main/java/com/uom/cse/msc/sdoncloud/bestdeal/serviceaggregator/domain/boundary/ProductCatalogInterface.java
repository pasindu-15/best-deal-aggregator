package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainFeatureResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainMatchingProductsResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;

public interface ProductCatalogInterface {
    public DomainMatchingProductsResponseEntity getMatchingProducts(DomainFeatureResponseEntity domainFeatureResponseEntity) throws WebClientException;

    public DomainProductOffersResponseEntity getProductOffers(DomainProductOffersRequestEntity domainProductOffersRequestEntity) throws WebClientException;

}

