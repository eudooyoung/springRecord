package com.multi.restapi.section03.valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserNotFoundException e) {

        String code = "ERROR_CODE_0000";
        String desc = "회원 정보 조회 실패";
        String msg = e.getMessage();

        return new ResponseEntity<>(new ErrorResponse(code, desc, msg), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidException(MethodArgumentNotValidException e) {

        String code = "";
        String desc = "";
        String msg = "";

        if (e.getBindingResult().hasErrors()) {

            msg = e.getBindingResult().getFieldError().getDefaultMessage(); // 어노테이션에 저장되어있는 메시지

            String bindCode = e.getBindingResult().getFieldError().getCode();

            switch (bindCode) {
                case "NotNull":
                    code = "ERROR_CODE_00001";
                    desc = "필수값이 누락 되었습니다.";
            }

        }
        ErrorResponse errorResponse = new ErrorResponse(code, desc, msg);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

}
