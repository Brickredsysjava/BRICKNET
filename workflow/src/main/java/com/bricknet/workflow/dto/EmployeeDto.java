package com.bricknet.workflow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
//    @NotNull(message = "provide employee id for this operation")
    private String employeeId;
}
