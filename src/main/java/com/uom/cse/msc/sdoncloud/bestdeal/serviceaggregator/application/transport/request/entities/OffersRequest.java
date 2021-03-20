
package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator.application.transport.request.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OffersRequest {

    @NotNull
    private String productId;

}
