package report.member.common.response;

import lombok.Getter;
import org.json.simple.JSONObject;
import report.member.common.code.ErrorCode;
import report.member.common.code.MemberEnumCode;

@Getter
public class ErrorResponse {

    private final String status;
    private JSONObject errors = new JSONObject();

    public ErrorResponse(String status, int errorCode, String errorMessage) {
        this.status = status;
        this.errors.put("code", errorCode);
        this.errors.put("message", errorMessage);

    }

    public ErrorResponse(String status, ErrorCode errorCode) {
        this.status = status;
        this.errors.put("code", errorCode.getStatus().value());
        this.errors.put("message", errorCode.getMessage());

    }

    public ErrorResponse(String status, MemberEnumCode memberEnumCode) {
        this.status = status;
        this.errors.put("code", memberEnumCode.getCode());
        this.errors.put("message", memberEnumCode.getMessage());

    }

}
