
package com.uom.msc.cse.sdoncloud.aggregator.domain.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
public class SampleDomainResponseEntity {
    String resCode;
    String resDesc;
    String errorMessage;


}
