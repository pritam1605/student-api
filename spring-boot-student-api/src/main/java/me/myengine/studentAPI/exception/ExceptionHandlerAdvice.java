package me.myengine.studentAPI.exception;

import me.myengine.studentAPI.exception.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerAdvice {

  @ExceptionHandler(NotFoundException.class)
  public @ResponseBody ResponseEntity<List<String>> handleNotFoundException(NotFoundException e) {

    List<String> errorMessages = new ArrayList<>();
    errorMessages.add(e.getMessage());
    return new ResponseEntity<List<String>>(errorMessages,  HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public @ResponseBody ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

    List<ObjectError> errors = e.getBindingResult().getAllErrors();
    List<String> errorMessages = new ArrayList<>();

    errors.forEach(objectError -> errorMessages.add(objectError.getDefaultMessage()));

    return new ResponseEntity<List<String>>(errorMessages, HttpStatus.BAD_REQUEST);
  }

  // For all other exceptions
  @ExceptionHandler(Exception.class)
  public @ResponseBody ResponseEntity<List<String>> handleException(Exception e) {

    List<String> errorMessages = new ArrayList<>();
    errorMessages.add(e.getMessage());
    return new ResponseEntity<List<String>>(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
