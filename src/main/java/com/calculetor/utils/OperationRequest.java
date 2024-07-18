package com.calculetor.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationRequest  {
    private String type; // e.g., addition, subtraction, etc.
    private Integer cost ;

}
