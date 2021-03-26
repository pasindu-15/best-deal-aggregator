
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transformer;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface ResponseEntityInterface {

    public Map transform(Object entity);
}
