package com.multi.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class B_CalculatorTest {

    @Test
    @DisplayName("두 수의 차가 올바른지 확인") // 테스트의 목적을 명확히 명시
    void testMinus() {
        // given
        int a = 10, b = 3;

        // when
        int result = Calculator.minus(a, b);

        // then
        assertThat(result).as("두 수의 차를 검증 합니다.").isEqualTo(7);
    }
}

class Calculator {

    static int minus(int a, int b) {
        return a - b;
    }
}
