
package com.uom.msc.cse.sdoncloud.aggregator.domain.service;

import com.uom.msc.cse.sdoncloud.aggregator.domain.entities.dto.SampleDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.aggregator.domain.entities.dto.SampleDomainResponseEntity;
import com.uom.msc.cse.sdoncloud.aggregator.external.proddetect.ProductDetectionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.ToDoubleBiFunction;


@Service
@Log4j2
public class OfferFinderService {
    @Autowired
    SampleDomainResponseEntity sampleDomainResponseEntity;


    @Autowired
    ProductDetectionService productDetectionService;
    /**
     * handle business logic
     * @param
     * @return
     */
    public SampleDomainResponseEntity find(SampleDomainRequestEntity sampleDomainRequestEntity) throws Exception{
        log.info("INFO|START use case...| request : " );
//        TODO: Execute business logic


        //call product detection service and get features
//        TODO: add return object with features
        productDetectionService.detectProduct("imag");

        //call product catalog service and get the best matching images


        sampleDomainResponseEntity.setResCode("200");
        sampleDomainResponseEntity.setResDesc("Operation Success");

        return sampleDomainResponseEntity;
    }

}
