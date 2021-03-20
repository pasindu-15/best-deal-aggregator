
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.external.exception;

import com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.exception.type.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class WebClientException extends BaseException {
    public WebClientException(String message) {
        super(message);
    }

    public WebClientException(String message, String code) {
        super(message, code);
    }
}
