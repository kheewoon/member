package report.member.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonEnum {

    STATUS_SUCCESS("success", "성공"),
    STATUS_FAIL("fail", "실패"),
    ;


    private final String name;
    private final String text;
}
