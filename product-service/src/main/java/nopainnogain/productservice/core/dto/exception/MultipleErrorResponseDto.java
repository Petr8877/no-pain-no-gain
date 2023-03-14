package nopainnogain.productservice.core.dto.exception;



import nopainnogain.productservice.core.exception.MyError;

import java.util.List;

public record MultipleErrorResponseDto(String logref, List<MyError> errors) {

}
