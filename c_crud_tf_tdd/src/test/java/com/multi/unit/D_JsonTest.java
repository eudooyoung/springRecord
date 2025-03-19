package com.multi.unit;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.assertj.core.api.Assertions.assertThat;

public class D_JsonTest {
    /**
     * JSONAssert는 JSON 데이터를 구조적 비교를 통해 검증하는 라이브러리
     * 두 JSON이 정확히 일치할 필요가 없더라도, 주요 구조나 필드만 검증해야 하는 경우 매우 유용
     * JSON 데이터 간의 정확한 구조 및 값 일치 여부를 비교.
     * <p>
     * 비교 모드:
     * STRICT: 모든 필드와 순서까지 완벽히 일치
     * LENIENT: 중요 필드가 일치하면 순서나 불필요한 필드를 무시
     * <p>
     * <p>
     * <p>
     * JsonPath는 JSON 데이터를 경로 기반으로 접근하여 특정 요소를 쉽게 검증하는 라이브러리
     * <p>
     * JsonPath 표현식
     * $.필드명: 루트 경로에서 시작하여 필드 접근.
     * [index]: 배열 내 특정 인덱스에 접근.
     * $.필드명.length(): 배열의 길이 확인.
     */

    @Test
    @DisplayName("Json 문자열 비교")
    void testJsonAssert() throws JSONException {
        // given
        String expectedJson = "{name: 'john', age: 30}";
        String actualJson = "{name: 'john', age: 30}";

        assertThat(actualJson).isEqualToIgnoringWhitespace(expectedJson);

        JSONAssert.assertEquals(expectedJson, actualJson, false);

        // 엄격한 검증
        // 모든 필드가 일치해야 함
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);

        String expectedJson2 = "{name: 'john'}";

        // 유연한 검증
        // 누락된 필드는 건너뛰고 비교
        JSONAssert.assertEquals(expectedJson2, actualJson, JSONCompareMode.LENIENT);

    }

    @Test
    @DisplayName("AssertJ + JsonPath - 특정 JSON 경로 기반 데이터 검증")
    void testJsonPath() {
        // Given: JSON 데이터 준비
        String jsonData = "{"
                + "\"store\": {"
                + "  \"book\": ["
                + "    { \"category\": \"fiction\", \"title\": \"Effective Java\", \"price\": 30.0 },"
                + "    { \"category\": \"technical\", \"title\": \"Java Concurrency in Practice\", \"price\": 40.0 }"
                + "  ],"
                + "  \"bicycle\": { \"color\": \"red\", \"price\": 19.95 }"
                + "}}";

        // When & Then: JsonPath를 통해 특정 필드 검증
        // 'store.bicycle.color' 필드가 "red" 인지 확인
        assertThat(JsonPath.parse(jsonData).read("$.store.bicycle.color", String.class))
                .isEqualTo("red");
        // 'store.book[0].title'이 "Effective Java"인지 확인
        assertThat(JsonPath.parse(jsonData).read("$.store.book[0].title", String.class))
                .isEqualTo("Effective Java");

        // 'store.book[1].price'가 40.0 인지 확인
        assertThat(JsonPath.parse(jsonData).read("$.store.book[1].price", Double.class))
                .isEqualTo(40.0);

        // store 아래의 book 배열의 크기가 2개인지 확인
        assertThat(JsonPath.parse(jsonData).read("$.store.book.length()", Integer.class))
                .isEqualTo(2);
    }
}
