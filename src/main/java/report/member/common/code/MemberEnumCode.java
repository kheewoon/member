package report.member.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberEnumCode {

    /*
     * 200010 : 인증번호가 전송 되었습니다.
     */
    AUTH_CODE_SEND_SUCESS("200010", "전화번호 인증에 성공 하였습니다."),

    /*
     * 200020 : 인증번호에 실패 하였습니다.
     */
    AUTH_CODE_SEND_FAIL("200020", "전화번호 인증에 실패 하였습니다."),

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
     * 200070 : 로그인 인증토큰이 발급 되었습니다.
     */
    LOGIN_SUCESS("200070", "로그인 인증토큰이 발급 되었습니다."),

    /*
     * 200070 : 로그인 실패
     */
    LOGIN_FAIL("200070", "로그인 실패"),

    /*
     * 200080 : 회원 정보가 조회 되었습니다.
     */
    FOUND_MEMBER("200080", "회원 정보가 조회 되었습니다."),

    /*
     * 200090 : 존재하지 않는 회원 정보입니다.
     */
    NOT_FOUND_MEMBER("200090", "존재하지 않는 회원 정보입니다."),

    /*
     * 200100 : 비밀번호가 재설정 되었습니다.
     */
    RESET_PASSWORD_SUCESS("200100", "비밀번호가 재설정 되었습니다."),

    /*
     * 200101 : 비밀번호가 재설정에 실패 하였습니다.
     */
    RESET_PASSWORD_FAIL("200101", "비밀번호가 재설정에 실패 하였습니다."),

    /*
     * 200102 : 전화번호 인증후 다시 시도해 주시기 바랍니다.
     */
    PHONE_NUMBER_CERT_NOT_FOUND("200102", "전화번호 인증후 다시 시도해 주시기 바랍니다.");


    private final String code;
    private final String message;

}