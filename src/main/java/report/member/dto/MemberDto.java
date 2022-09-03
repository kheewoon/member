package report.member.dto;

import lombok.*;
import org.hibernate.annotations.Comment;
import report.member.entity.MemberEntity;

import javax.persistence.Column;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String memberId;

    private String password;

    private String name;

    private String nickName;

    private String phoneNumber;

    private String email;

    public static MemberDto dtoConvert(MemberEntity memberEntity){

        if(null != memberEntity){
            return MemberDto.builder()
                    .id(memberEntity.getId())
                    .memberId(memberEntity.getMemberId())
                    .password(memberEntity.getPassword())
                    .name(memberEntity.getName())
                    .nickName(memberEntity.getNickName())
                    .phoneNumber(memberEntity.getPhoneNumber())
                    .email(memberEntity.getEmail())
                    .build();
        }
        return new MemberDto();
    }

}
