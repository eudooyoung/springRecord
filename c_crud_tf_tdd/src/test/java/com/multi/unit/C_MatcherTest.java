package com.multi.unit;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class C_MatcherTest {
    /**
     * AssertJ: 직관적인 API를 통해 가독성 높은 어서션을 제공.
     * Hamcrest: 가독성 높은 매처로 복잡한 조건의 어서션을 쉽게 구성 가능.  테스트에서 매처를 사용해 객체나 값의 특정 조건 충족 여부를 검증하는 데 유용한 라이브러리
     * 문자열 매처:
     * <p>
     * startsWith(String prefix): 문자열이 특정 접두사로 시작하는지 검증
     * endsWith(String suffix): 문자열이 특정 접미사로 끝나는지 검증
     * containsString(String substring): 문자열에 특정 하위 문자열이 포함되는지 확인
     * 컬렉션 매처:
     * <p>
     * hasSize(int size): 컬렉션의 크기가 예상된 값인지 확인
     * hasItem(T item): 컬렉션에 특정 요소가 포함되어 있는지 확인
     * containsInAnyOrder(T... items): 컬렉션이 특정 항목들을 포함하며, 순서에 관계없이 포함 여부만 확인할 때 사용
     * 숫자 매처:
     * <p>
     * greaterThan(T value): 값이 특정 값보다 큰지 검증
     * lessThan(T value): 값이 특정 값보다 작은지 검증
     * equalTo(T value): 값이 특정 값과 일치하는지 검증
     */

    @Test
    void testAssertJ() {
        // given: 리스트 준비
        List<String> fruits = List.of("apple", "banana", "orange");

        // when & then: 리스트 크기와 항목 포함 여부 검증
        assertThat(fruits)
                .hasSize(3)
                .contains("banana")
                .doesNotContain("grape");

        assertThat(fruits, hasSize(3));
        assertThat(fruits, hasItem("banana"));
        assertThat(fruits, not(hasItem("grape")));
    }

    @Test
    void testHamcrest() {
        // given: 문자열 준비
        String message = "Hello, Spring Boot";

        // when & then: 문자열 검증
        assertThat(message, startsWith("Hello"));
        assertThat(message, containsString("Spring"));
        assertThat(message, endsWith("Boot"));

    }
}
