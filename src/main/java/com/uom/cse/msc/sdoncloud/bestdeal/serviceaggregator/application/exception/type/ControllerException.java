
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.exception.type;

public class ControllerException extends BaseException {
    public ControllerException(String message){
        super(message);
    }
    public ControllerException(String message, String code){
        super(message,code);
    }
}
