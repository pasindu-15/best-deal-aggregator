
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.service;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.FeatureDetectionInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.ProductCatalogInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.FeatureDetection;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainImageRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.MatchingProducts;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.exception.DomainException;
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

    public MatchingProducts findProducts(DomainImageRequestEntity domainImageRequestEntity) throws Exception{

//      TODO: Find features from FeatureDetectionService
        FeatureDetection featureDetection = featureDetectionInterface.getFeatures(domainImageRequestEntity);
        if(!featureDetection.getResCode().equals("00")){
            throw new DomainException("Feature Detection Error !!!","89");
        }

//      TODO: Find best matching products from ProductCatalogService
        MatchingProducts matchingProducts = productCatalogInterface.getMatchingProducts(featureDetection);

        if(!matchingProducts.getResCode().equals("00")){
            throw new DomainException("Product Matching Error !!!","88");
        }

        return matchingProducts;
    }

}
