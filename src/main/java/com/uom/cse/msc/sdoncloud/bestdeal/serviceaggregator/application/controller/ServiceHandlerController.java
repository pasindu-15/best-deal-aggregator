package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.controller;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.request.entities.ImageRequest;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers.MatchingProductResponseTransformer;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers.ProductOffersResponseTransformer;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.response.transformers.ProductResponseTransform;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainImageRequestEntity;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.DomainProductOffersResponse;
import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.domain.entities.dto.MatchingProducts;
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
@RequestMapping("${base-url.context}/service-handler")
@Log4j2
public class ServiceHandlerController extends BaseController {

    @Autowired
    ProductsFinderService productsFinderService;

    @Autowired
    ProductOffersFinderService productOffersFinderService;

    @Autowired
    ResponseEntityTransformer responseEntityTransformer;



    @Autowired
    ProductOffersResponseTransformer productOffersResponseTransformer;

    @Autowired
    private RequestEntityValidator validator;

    @PostMapping(value="/searchProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> searchProducts(@Validated @RequestBody(required = true) ImageRequest imageRequest, HttpServletRequest request)throws Exception{
        setLogIdentifier(request);


        log.info("Transformed imageRequestEntity : "+imageRequest.toString());

        DomainImageRequestEntity domainImageRequestEntity = new ModelMapper().map(imageRequest, DomainImageRequestEntity.class);


        MatchingProducts matchingProducts = productsFinderService.findProducts(domainImageRequestEntity);


        Map trResponse = responseEntityTransformer.transform(matchingProducts, new MatchingProductResponseTransformer());
        log.info("ServiceHandlerController: searchProducts : Transformed response : "+trResponse.toString());


        return ResponseEntity.status(HttpStatus.OK).body(trResponse);
    }

    @GetMapping(value="/getProductOffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> getProductOffers(@Validated @RequestParam(name = "productId") Long productId, HttpServletRequest request)throws Exception{


        setLogIdentifier(request);


        log.info("Request Received productId : {}",productId );

        DomainProductOffersResponse domainProductOffersResponse = productOffersFinderService.findProductOffers(productId);

        Map trResponse = responseEntityTransformer.transform(domainProductOffersResponse, new ProductOffersResponseTransformer());
        log.info("Transformed response : "+trResponse.toString());

        return ResponseEntity.status(HttpStatus.OK).body(trResponse);
    }
}

