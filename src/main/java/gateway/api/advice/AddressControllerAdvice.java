package gateway.api.advice;

import gateway.api.exceptions.AddressNotFoundException;
import gateway.api.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AddressControllerAdvice {

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> iDInvalidException(HttpServletRequest request, Exception ex) {

        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder()
                .status(404)
                .error("Not Found")
                .message(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
