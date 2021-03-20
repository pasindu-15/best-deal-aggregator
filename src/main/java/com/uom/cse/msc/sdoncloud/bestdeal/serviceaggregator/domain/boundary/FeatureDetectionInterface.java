package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainFeatureResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainImageRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;

public interface FeatureDetectionInterface {
    public DomainFeatureResponseEntity getFeatures(DomainImageRequestEntity domainImageRequestEntity) throws WebClientException;
}
