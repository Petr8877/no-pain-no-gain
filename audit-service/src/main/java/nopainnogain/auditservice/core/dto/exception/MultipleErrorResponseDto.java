package nopainnogain.auditservice.core.dto.exception;


import nopainnogain.auditservice.core.exception.MyError;

import java.util.List;

public record MultipleErrorResponseDto(String logref,
                                       List<MyError> errors) {

}
