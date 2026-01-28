package com.yonsai.books.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리를 위한 Controller Advice
 *
 * 역할 :
 *  - 컨트롤러에서 발생하는 예외를 처리하기 위함
 * @return
 *  - 특정하는 예외 :
 *      - IllegalArgumentException : 400 + message
 *      - MethodArgumentNotValidException : 400 + message
 *      - Exception : 500 + message
 *
 * @author 노수한
 */
@RestControllerAdvice
public class GlobalExceoptionHandler {

    /**
     * IllegalArgumentException 예외 처리
     * @param e IllegalArgumentException 예외
     * @return ResponseEntity<String> 400 Bad Request
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * MethodArgumentNotValidException 예외 처리
     * @param e MethodArgumentNotValidException 예외
     * @return ResponseEntity<String> 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * Exception 예외 처리
     * @param e Exception 예외
     * @return ResponseEntity<String> 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
