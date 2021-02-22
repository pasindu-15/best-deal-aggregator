
package com.uom.msc.cse.sdoncloud.aggregator.application.transport.request.entities;

import com.uom.msc.cse.sdoncloud.aggregator.application.validator.RequestEntityInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SampleRequestEntity implements RequestEntityInterface {

    @NotNull
    private Integer id;

}
