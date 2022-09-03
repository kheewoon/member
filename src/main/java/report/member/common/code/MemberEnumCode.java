package report.member.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberEnumCode {

    /*
     * 200010 : 인증번호가 전송 되었습니다.
     */
    AUTH_CODE_SEND_SUCESS("200010", "인증번호가 전송 되었습니다."),

    /*
     * 200020 : 인증번호에 실패 하였습니다.
     */
    AUTH_CODE_SEND_FAIL("200020", "인증번호에 실패 하였습니다."),

    /*
     * 201030 : 인증이 완료 되었습니다.
     */
    CERT_SUCESS("201030", "인증이 완료 되었습니다."),

    /*
     * 201040 : 인증에 실패 하였습니다.
     */
    CERT_FAIL("201040", "인증에 실패 하였습니다."),

    /*
     * 200050 : 회원정보가 정상적으로 저장 되었습니다.
     */
    MEMBER_SAVE_SUCESS("200050", "회원정보가 정상적으로 등록 되었습니다."),

    /*
     * 200060 : 회원정보 저장에 실패 하였습니다.
     */
    MEMBER_SAVE_FAIL("200060", "회원정보 저장에 실패 하였습니다."),

    /*
     * 200070 : 로그인 성공
     */
    LOGIN_SUCESS("200070", "로그인 성공"),

    /*
     * 200070 : 로그인 실패
     */
    LOGIN_FAIL("200070", "로그인 성공"),

    /*
     * 200080 : 존재하지 않는 회원 정보입니다.
     */
    NOT_FOUND_MEMBER("200080", "존재하지 않는 회원 정보입니다."),

    /*
     * 200090 : 비밀번호가 재설정 되었습니다.
     */
    RESET_PASSWORD_SUCESS("200090", "비밀번호가 재설정 되었습니다.");


    private final String code;
    private final String message;

}