
package com.uom.msc.cse.sdoncloud.aggregator.application.controller;

import com.uom.msc.cse.sdoncloud.aggregator.application.transformer.ResponseEntityTransformer;
import com.uom.msc.cse.sdoncloud.aggregator.application.transport.request.entities.SampleRequestEntity;
import com.uom.msc.cse.sdoncloud.aggregator.application.transport.response.transformers.SampleResponseTransformer;
import com.uom.msc.cse.sdoncloud.aggregator.application.validator.RequestEntityValidator;
import com.uom.msc.cse.sdoncloud.aggregator.domain.entities.dto.SampleDomainRequestEntity;
import com.uom.msc.cse.sdoncloud.aggregator.domain.entities.dto.SampleDomainResponseEntity;
import com.uom.msc.cse.sdoncloud.aggregator.domain.service.OfferFinderService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("${base-url.context}")
@Log4j2
public class OfferController extends BaseController {


    @Autowired
    OfferFinderService offerFinderService;

    @Autowired
    ResponseEntityTransformer responseEntityTransformer;

    @Autowired
    SampleResponseTransformer sampleResponseTransformer;

    @Autowired
    private RequestEntityValidator validator;

    @PostMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> integration(@Validated @RequestBody(required = true) SampleRequestEntity sampleRequestEntity, HttpServletRequest request)throws Exception{

//        TODO: set UUID
        setLogIdentifier(request);
//        TODO: validate the request
        validator.validate(sampleRequestEntity);
//        logger.info("Request validation success");

//        TODO: request object map to domain entity object
        SampleDomainRequestEntity sampleDomainRequestEntity = new ModelMapper().map(sampleRequestEntity, SampleDomainRequestEntity.class);

//          TODO: call domain business logic
        SampleDomainResponseEntity sampleDomainResponseEntity = offerFinderService.find(sampleDomainRequestEntity);

//        TODO: transform domain response
        Map trResponse = responseEntityTransformer.transform(sampleDomainResponseEntity,sampleResponseTransformer);
//        logger.info("Transformed response : "+trResponse.toString());

//        TODO: return response
         return ResponseEntity.status(HttpStatus.OK).body(trResponse);
    }
}
