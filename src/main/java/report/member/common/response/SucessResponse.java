package report.member.common.response;

import lombok.Getter;
import report.member.common.code.MemberEnumCode;

@Getter
public class SucessResponse <T>{

    //private final LocalDateTime timestamp = LocalDateTime.now();
    //private final int status;
    //private final String error;
    private final String code;
    private final String message;
    private T result;

    public SucessResponse(MemberEnumCode memberEnumCode, T parameter) {
        //this.status = errorCode.getStatus().value();
        //this.error = errorCode.getStatus().name();
        this.code = memberEnumCode.getCode();
        this.message = memberEnumCode.getMessage();
        this.result = parameter;
    }

    public SucessResponse(String code, String message) {
        //this.status = errorCode.getStatus().value();
        //this.error = errorCode.getStatus().name();
        this.code = code;
        this.message = message;
    }

}
