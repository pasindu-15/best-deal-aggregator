package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.service;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.ProductCatalogInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class ProductOffersFinderService {

    @Autowired
    ProductCatalogInterface productCatalogInterface;

    public DomainProductOffersResponse findProductOffers(Long productId) throws Exception{

        DomainProductOffersResponse domainProductOffersResponse = productCatalogInterface.getProductOffers(productId);

        return domainProductOffersResponse;
    }

}

