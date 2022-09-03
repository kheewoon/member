package report.member.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum JwtTokenCode {

    /*
     * 401000 : 잘못된 JWT 서명입니다.
     */
    UNKNOWN_ERROR("401000", "잘못된 JWT 서명입니다."),
    /*

    /*
     * 401001 : 잘못된 JWT 서명입니다.
     */
    WRONG_TYPE_TOKEN("401001", "잘못된 JWT 서명입니다."),
    /*
     * 401002: 잘못된 요청
     */
    EXPIRED_TOKEN("401002", "만료된 JWT 토큰입니다."),
    /*
     * 401003: 잘못된 요청
     */
    UNSUPPORTED_TOKEN("401003", "지원되지 않는 JWT 토큰입니다."),
    /*
     * 401004 : 잘못된 요청
     */
    WRONG_TOKEN("401004", "JWT 토큰이 잘못되었습니다.");


    private final String code;
    private final String message;
}
