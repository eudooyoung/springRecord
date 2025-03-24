package com.multi.restapi.section04.hateoas;


import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hateoas")
public class HateoasTestController {

    private final DtoModelAssembler assembler;

    private List<UserDTO> users;

    public HateoasTestController(DtoModelAssembler assembler) {
        this.assembler = assembler;
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new Date()));
    }

    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable("userNo") int userNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).toList().get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "조회 성공", responseMap));
    }

    // # WebMvclinkBuilder를 사용하여 링크를 만든다.
    // Link Object에는 rel와 href를 넣는다.
    // rel : 이름 / href : 실제 링크
    // linkTo() : methodOn에 컨트롤러 이름을 주고, 그 컨트롤러 메서드 이름을 준다.
    // withSelfRes() : self를 주려면 이렇게 / 아니면 .withRel("~") 이렇게 준다.
    // EntityModel은 Spring HATEOAS 라이브러리의 일부로, RESTful 웹 서비스에서 하이퍼미디어 기반의 응답을 제공. EntityModel 객체는 데이터와 함께 이 데이터와 관련된 링크를 포함

//    @GetMapping("/users")
//    public ResponseEntity<ResponseMessage> findAllUsers() {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
//
//
//        List<EntityModel<UserDTO>> usersWithRel =
//                users.stream().map(user -> EntityModel.of(user,
//                        linkTo(methodOn(HateoasTestController.class).findUserByNo(user.getNo())).withSelfRel(),
//                        linkTo(methodOn(HateoasTestController.class).findAllUsers()).withRel("users")
//                )).collect(Collectors.toList());
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("users", usersWithRel);
//
//
//        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공!", responseMap);
//        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
//    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<EntityModel<UserDTO>> usersWithRel =
                users.stream().map(user -> assembler.toModel(user)).collect(Collectors.toList());
        //        users.stream().map(assembler::toModel).collect(Collectors.toList());

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", usersWithRel);


        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공!", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }
}
