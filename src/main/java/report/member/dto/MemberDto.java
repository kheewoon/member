package report.member.dto;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import report.member.entity.MemberEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.persistence.Column;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
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

    public void passwordEncode(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = (null != this.password && !this.password.isEmpty()) ? passwordEncoder.encode(this.password) : "";
    }

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
