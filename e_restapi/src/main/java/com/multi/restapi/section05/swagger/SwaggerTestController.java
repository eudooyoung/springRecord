package com.multi.restapi.section05.swagger;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://support.smartbear.com/swaggerhub-explore/docs/?lang=en
//http://localhost:8001/swagger-ui/index.html

@Tag(name = "스프링 부트 스웨거 연동 테스트용 컨트롤러")
@RestController
@RequestMapping("/swagger")
public class SwaggerTestController {

	private List<UserDTO> users;

	public SwaggerTestController() {
		users = new ArrayList<>();

		users.add(new UserDTO(1, "user01", "pass01", "홍길동", new java.util.Date()));
		users.add(new UserDTO(2, "user02", "pass02", "유관순", new java.util.Date()));
		users.add(new UserDTO(3, "user03", "pass03", "이순신", new java.util.Date()));
	}
	@Operation(summary = "모든 회원 목록 조회")
	@GetMapping("/users")
	public ResponseEntity<ResponseMessage> findAllUsers() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("users", users);

		ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공!", responseMap);

		return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
	}


	@Operation(summary = "회원 번호로 회원 조회")
	@GetMapping("/users/{userNo}")
	public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable("userNo") int userNo) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

		UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).toList().get(0);

		System.out.println(foundUser);

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("user", foundUser);

		return ResponseEntity
				.ok()
				.headers(headers)
				.body(new ResponseMessage(200, "조회 성공!", responseMap));
	}


	@Operation(summary = "신규 회원 추가")
	@PostMapping("/users")
	public ResponseEntity<?> registUser(@RequestBody UserDTO newUser)  {

		System.out.println(newUser);

		int lastUserNo = users.get(users.size() - 1).getNo();
		newUser.setNo(lastUserNo + 1);

		users.add(newUser);

		return ResponseEntity
				.created(URI.create("/entity/users/" + users.get(users.size() - 1).getNo()))
				.build();
	}



	@Operation(summary = "회원 정보 수정")
	@PutMapping("/users/{userNo}")
	public ResponseEntity<?> modifyUser(@RequestBody UserDTO modifyInfo, @PathVariable("userNo") int userNo) throws URISyntaxException {

		System.out.println(modifyInfo);

		UserDTO foundUser =
				users.stream().filter(user -> user.getNo() == userNo).toList().get(0);
		foundUser.setId(modifyInfo.getId());
		foundUser.setPwd(modifyInfo.getPwd());
		foundUser.setName(modifyInfo.getName());

		return ResponseEntity
				.created(URI.create("/entity/users/" + userNo))
				.build();
	}



	@Operation(summary = "회원 정보 삭제")
//    @ApiResponses({
//            @ApiResponse(code = 204, message = "회원 정보 삭제 성공"),
//            @ApiResponse(code = 400, message = "잘못 입력 된 파라미터")
//    })
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "회원 정보 삭제 성공"),
			@ApiResponse(responseCode = "400", description = "잘못 입력 된 파라미터")
	})
	@DeleteMapping("/users/{userNo}")
	public ResponseEntity<?> removeUser(@PathVariable("userNo") int userNo) {

		UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).toList().get(0);
		users.remove(foundUser);

		return ResponseEntity
				.noContent()
				.build();
	}
}
