
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.service;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.FeatureDetectionInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.ProductCatalogInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainFeatureResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainImageRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainMatchingProductsResponseEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class ProductsFinderService {

    @Autowired
    FeatureDetectionInterface featureDetectionInterface;

    @Autowired
    ProductCatalogInterface productCatalogInterface;

    public DomainMatchingProductsResponseEntity findProducts(DomainImageRequestEntity domainImageRequestEntity) throws Exception{

//      TODO: Find features from FeatureDetectionService
        DomainFeatureResponseEntity domainFeatureResponseEntity = featureDetectionInterface.getFeatures(domainImageRequestEntity);

//      TODO: Find best matching products from ProductCatalogService
        DomainMatchingProductsResponseEntity domainMatchingProductsResponseEntity = productCatalogInterface.getMatchingProducts(domainFeatureResponseEntity);

        return domainMatchingProductsResponseEntity;
    }

}
