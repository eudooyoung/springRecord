package com.multi.restapi.section01.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/response")
public class ResponseRestController {

    // 1. 문자열 응답
    @GetMapping("/hello")
    public String helloworld() {
        return "hello world";
    }

    // 2. 기본자료형
    @GetMapping("/random")
    public int getRandom() {
        return (int) (Math.random() * 10) + 1;
    }

    // 3. Object
    @GetMapping("/message")
    public Message getMessage() {
        return new Message(200, "메세지 응답 성공");
    }

    // 4. list
    @GetMapping("/list")
    public List<String> getList() {
        return List.of(new String[]{"사과", "바나나", "복숭아"});
    }

    // 5. Map
    @GetMapping("/map")
    public Map<Integer, String> getMap() {
        List<Message> messages = new ArrayList<>();

        messages.add(new Message(200, "정상 응답"));
        messages.add(new Message(404, "페이지 찾을 수 없음"));
        messages.add(new Message(500, "서버 측 에러"));
        return messages.stream().collect(Collectors.toMap(Message::getHttpStatusCode, Message::getMessage)); // collector 자료형 변경
    }

    // 6. image file
    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage() throws IOException {
        System.out.println(getClass());
        System.out.println(getClass().getResourceAsStream("/images/sample.PNG")); // 인풋 스트림. 바이트형태 입출력
        return getClass().getResourceAsStream("/images/sample.PNG").readAllBytes();
    }

    /* 7. RepsonseEntity를 이용한 응답
      *  * 타입은 ResponseEntity<반환할 타입> 으로 지정
    * HTTP 응답에 필요한 요소들 중 대표적인 Status, Header , Body 를 지정하여 응답을 만든다
    *
    * Builder Pattern  활용하는 것을 권장 - 숫자로 된 상태 코드를 넣을 때, 잘못된 숫자를 넣을 수 있는 실수 때문
    * Builder Pattern 을 활용하면 각 상태에 매칭되는 숫자 코드를 외울 필요 없이 Builder 메소드를 선택
    * https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/201
    * return ResponseEntity.ok()
          .headers(headers)
          .body(xxxDto);
          *
         생성자일경우 return new ResponseEntity<xxxDto>(xxxDto, headers, HttpStatus.valueOf(200));

      *
      * */

    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity() {
//        return new ResponseEntity<Message>(new Message(200, "hello world"), HttpStatus.valueOf(200)); // 상태 코드값 까지 넣어줘야 함

        return ResponseEntity
                .ok()
                .body(new Message(200, "hello world"));
    }



}
