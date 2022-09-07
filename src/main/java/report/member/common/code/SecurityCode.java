package report.member.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurityCode {

    /*
     * 401005 : 아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.
     */
    BAD_CREDENTIALS("401005", "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요."),
    /*

    /*
     * 401006 : 계정이 비활성화되었습니다.
     */
    DISALED("401006", "계정이 비활성화되었습니다."),
    /*
     * 401007: 비밀번호 유효기간이 만료 되었습니다.
     */
    CREDENTIALS_EXPIRED("401007", "비밀번호 유효기간이 만료 되었습니다."),
    /*
     * 401008: 계정이 잠겨있습니다.
     */
    LOCKED("401008", "계정이 잠겨있습니다."),
    /*
     * 401009 : 계정이 만료되었습니다.
     */
    ACCOUNT_EXPIRED("401009", "계정이 만료되었습니다.");


    private final String code;
    private final String message;
}
