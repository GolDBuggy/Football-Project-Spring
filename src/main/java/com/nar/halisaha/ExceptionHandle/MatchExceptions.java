package com.nar.halisaha.ExceptionHandle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@ControllerAdvice
public class MatchExceptions {

    private static Logger logger=Logger.getLogger(MatchExceptions.class.getName());

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> teamExc(TeamException e){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage(), LocalDateTime.now());
        logger.info(errorResponse+"");
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
