package org.pet.shop.exception;


import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class PetShopControllerAdvice {

    public PetShopControllerAdvice() {
    }

    @ExceptionHandler(PetShopApplicationException.class)
    public ResponseEntity<?> handlePetShopApplicationException(
        final PetShopApplicationException ex,
        final WebRequest webRequest)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PetShopExceptionBadRequest.class)
    public ResponseEntity<?> handlePetShopExceptionBadRequest(
            final PetShopApplicationException ex,
            final WebRequest webRequest)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PetShopExceptionNotFound.class)
    public ResponseEntity<?> handlePetShopExceptionNotFound(
            final PetShopApplicationException ex,
            final WebRequest webRequest)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
