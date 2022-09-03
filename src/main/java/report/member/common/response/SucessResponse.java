package report.member.common.response;

import lombok.Getter;
import report.member.common.code.MemberEnumCode;

@Getter
public class SucessResponse <T>{

    private final String code;
    private final String message;
    private T data;
    private final String status;

    public SucessResponse(String status, MemberEnumCode memberEnumCode, T parameter) {
        this.status = status;
        this.code = memberEnumCode.getCode();
        this.message = memberEnumCode.getMessage();
        this.data = parameter;
    }

    public SucessResponse(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
