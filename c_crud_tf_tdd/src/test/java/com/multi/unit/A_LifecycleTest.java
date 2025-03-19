package com.multi.unit;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class A_LifecycleTest {

    @BeforeAll
    static void setupBeforeAll() {
        System.out.println("모든 테스트 시작 전>>");
    }

    @AfterAll
    static void clearAfterAll() {
        System.out.println("모든 테스트 종료 후>>");
    }

    @BeforeEach
    void setupBeforeEach() {
        System.out.println("각 테스트 시작 전>>");
    }

    @AfterEach
    void clearAfterEach() {
        System.out.println("각 테스트 종료 후>>");
    }

    @DisplayName("값이 양수인지 확인")
    @Test
    void testPositive() {
        // given: test 할 값을 정의
        int value = 5;
        // when & then: 검증, 결과 출력. 양수임을 검증
        assertThat(value).as("값이 양수인지 확인").isGreaterThan(0);
    }

    @DisplayName("값이 음수인지 확인")
    @Test
    void testNegative() {
        // given: test 할 값을 정의
        int value = -3;
        // when & then: 검증, 결과 출력. 음수임을 검증
        assertThat(value).as("값이 음수인지 확인").isLessThan(0);
    }

    @Nested
    @DisplayName("객체 null 검증 테스트")
    class NullTest {
        @DisplayName("객체가 null 이 아닌지 확인")
        @Test
        void testNotNull() {
            //given: 비어있지 않은 객체 준비
            Object obj = new Object();

            // when & then: 객체가 null 이 아닌지 확인
            assertThat(obj).as("객체가 null 이 아니어야 합니다.").isNotNull();
        }

        @DisplayName("객체가 null 인지 확인")
        @Test
        void testNull() {
            //given: 비어있는 객체 준비
            Object obj = null;

            // when & then: 객체가 null 이 아닌지 확인
            assertThat(obj).as("객체가 null 이어야 합니다.").isNull();
        }
    }


}
