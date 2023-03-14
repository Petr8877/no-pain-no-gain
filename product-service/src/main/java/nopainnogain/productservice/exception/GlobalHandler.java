package nopainnogain.productservice.exception;

import nopainnogain.productservice.core.dto.exception.MultipleErrorResponseDto;
import nopainnogain.productservice.core.dto.exception.SingleErrorResponseDto;
import nopainnogain.productservice.core.exception.MyError;
import nopainnogain.productservice.core.exception.SingleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SingleErrorResponseDto catchSingleErrorResponse(SingleErrorResponse e) {
        return new SingleErrorResponseDto("Не верный запрос", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MultipleErrorResponseDto catchMultipleErrorResponse(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        ArrayList<MyError> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errors.add(new MyError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new MultipleErrorResponseDto("Прроверьте верность внесенных данных", errors);
    }
}
