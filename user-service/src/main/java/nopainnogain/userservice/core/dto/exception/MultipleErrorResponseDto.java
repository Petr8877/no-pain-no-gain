package nopainnogain.userservice.core.dto.exception;


import nopainnogain.userservice.core.exception.MyError;

import java.util.List;

public record MultipleErrorResponseDto(String logref, List<MyError> errors) {

}
