package report.member.dto;

import lombok.*;
import report.member.entity.MemberEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberApiDto {

    /*
    * 회원 아이디
    * */
    private String memberId;

    /*
    * 이름
    * */
    private String name;

    /*
    * 닉네임
    * */
    private String nickName;

    /*
    * 전화번호
    * */
    private String phoneNumber;

    /*
    * 이메일
    * */
    private String email;

    public static MemberApiDto dtoConvert(MemberEntity memberEntity){
        return MemberApiDto.builder()
                .memberId(memberEntity.getMemberId())
                .name(memberEntity.getName())
                .nickName(memberEntity.getNickName())
                .phoneNumber(memberEntity.getPhoneNumber())
                .email(memberEntity.getEmail())
                .build();
    }
}
