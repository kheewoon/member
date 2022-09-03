package report.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import report.member.common.code.ErrorCode;
import report.member.common.code.MemberEnumCode;

@Getter
@AllArgsConstructor
public class MemberException extends RuntimeException{
    private final MemberEnumCode memberEnumCode;
}
