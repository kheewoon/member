package report.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import report.member.common.code.ErrorCode;

@Getter
@AllArgsConstructor
public class ErrorException extends RuntimeException{
    private final ErrorCode errorCode;
}
