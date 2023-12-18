package com.devops.v1.examTermOne.services;


import com.devops.v1.examTermOne.exceptions.InvalidOperationException;
import com.devops.v1.examTermOne.serviceImpls.MathOperatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MathOperatorServiceImplTest {
    @Mock
    private MathOperatorImpl mathOperatorService;
//    PcUserService mock = org.mockito.Mockito.mock(PcUserService.class);

    @Test
    public void should_create_math_operation_success() throws InvalidOperationException {
        int operator1 = 4;
        int operator2 = 9;
        String operation = "+";

        double mathOperation = mathOperatorService.doMath(operator1,operator2, operation);
        when(mathOperatorService.doMath(operator1, operator2, operation)).thenReturn(mathOperation);

        double actualMathOperator = mathOperatorService.doMath(operator1, operator2, operation);

        assertThat(mathOperation).isEqualTo(actualMathOperator);
    }

}
