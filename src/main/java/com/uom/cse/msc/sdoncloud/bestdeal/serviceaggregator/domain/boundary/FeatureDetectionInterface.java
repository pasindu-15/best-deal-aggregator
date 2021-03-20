package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.FeatureDetection;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainImageRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception.WebClientException;

public interface FeatureDetectionInterface {
    public FeatureDetection getFeatures(DomainImageRequestEntity domainImageRequestEntity) throws WebClientException;
}
