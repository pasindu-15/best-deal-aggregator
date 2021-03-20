package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.controller;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.request.entities.ImageRequest;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.request.entities.OffersRequest;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers.MatchingProductResponseTransformer;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers.ProductOffersResponseTransformer;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainImageRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainMatchingProductsResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponseEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.service.ProductOffersFinderService;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.service.ProductsFinderService;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer.ResponseEntityTransformer;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.validator.RequestEntityValidator;
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
@RequestMapping("${base-url.context}/mobile")
@Log4j2
public class MobileController extends BaseController {

    @Autowired
    ProductsFinderService productsFinderService;

    @Autowired
    ProductOffersFinderService productOffersFinderService;

    @Autowired
    ResponseEntityTransformer responseEntityTransformer;

    @Autowired
    MatchingProductResponseTransformer matchingProductResponseTransformer;

    @Autowired
    ProductOffersResponseTransformer productOffersResponseTransformer;

    @Autowired
    private RequestEntityValidator validator;

    @GetMapping("")
    String hello() {
        return "Hello! Mobile Controller is running";
    }

    @PostMapping(value="/searchProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> searchProducts(@Validated @RequestBody(required = true) ImageRequest imageRequest, HttpServletRequest request)throws Exception{

//      TODO: set UUID
            setLogIdentifier(request);
//      TODO: validate the request
//            validator.validate(imageRequest);
//            log.info("Request validation success");

            log.info("Transformed imageRequestEntity : "+imageRequest.toString());
//      TODO: request object map to domain entity object
            DomainImageRequestEntity domainImageRequestEntity = new ModelMapper().map(imageRequest, DomainImageRequestEntity.class);

//      TODO: call domain business logic
            DomainMatchingProductsResponseEntity domainMatchingProductsResponseEntity = productsFinderService.findProducts(domainImageRequestEntity);

//      TODO: transform domain response
            Map trResponse = responseEntityTransformer.transform(domainMatchingProductsResponseEntity, matchingProductResponseTransformer);
            log.info("MobileController: searchProducts : Transformed response : "+trResponse.toString());

//      TODO: return response
            return ResponseEntity.status(HttpStatus.OK).body(trResponse);
    }

    @PostMapping(value="/getProductOffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> getProductOffers(@Validated @RequestBody(required = true) OffersRequest offersRequest, HttpServletRequest request)throws Exception{

//      TODO: set UUID
        setLogIdentifier(request);
//      TODO: validate the request
//            validator.validate(imageRequest);
//            log.info("Request validation success");

        log.info("Transformed imageRequestEntity : "+ offersRequest.toString());
//      TODO: request object map to domain entity object
        DomainProductOffersRequestEntity domainProductOffersRequestEntity = new ModelMapper().map(offersRequest, DomainProductOffersRequestEntity.class);

//      TODO: call domain business logic
        DomainProductOffersResponseEntity domainProductOffersResponseEntity = productOffersFinderService.findProductOffers(domainProductOffersRequestEntity);

//      TODO: transform domain response
        Map trResponse = responseEntityTransformer.transform(domainProductOffersResponseEntity, productOffersResponseTransformer);
        log.info("Transformed response : "+trResponse.toString());

//      TODO: return response
        return ResponseEntity.status(HttpStatus.OK).body(trResponse);
    }
}

