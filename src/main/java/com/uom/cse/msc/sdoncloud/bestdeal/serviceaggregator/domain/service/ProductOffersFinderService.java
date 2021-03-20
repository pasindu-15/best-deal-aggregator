package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.service;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.boundary.ProductCatalogInterface;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponseEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class ProductOffersFinderService {

    @Autowired
    ProductCatalogInterface productCatalogInterface;

    public DomainProductOffersResponseEntity findProductOffers(DomainProductOffersRequestEntity domainProductOffersRequestEntity) throws Exception{

//      TODO: Find given product from ProductCatalogService
        DomainProductOffersResponseEntity domainProductOffersResponseEntity = productCatalogInterface.getProductOffers(domainProductOffersRequestEntity);

        return domainProductOffersResponseEntity;
    }

}

