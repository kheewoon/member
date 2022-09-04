package report.member.entity;

import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import report.member.dto.MemberDto;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class MemberEntity extends BaseEntity {

    @Id @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("회원 id")
    private Long id;

    @Column(name = "member_id", nullable = false, length = 10)
    @Comment("아이디")
    private String memberId;

    @Column(name = "password", nullable = false, length = 255)
    @Comment("패스워드")
    private String password;

    @Column(name = "name", nullable = false, length = 10)
    @Comment("이름")
    private String name;

    @Column(name = "nick_name", nullable = false, length = 10)
    @Comment("닉네임")
    private String nickName;

    @Column(name = "phone_number", nullable = false, length = 20)
    @Comment("전화번호")
   private String phoneNumber;

    @Column(name = "email", nullable = false, length = 50)
    @Comment("이메일")
    private String email;



    @PrePersist
    public void setField(){
        //전화번호 하이픈 제거
        this.phoneNumber = (null != this.phoneNumber && !this.phoneNumber.isEmpty()) ? this.phoneNumber.replaceAll("-",""):"";
    }


    public static MemberEntity entityConvert(MemberDto memberDto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return MemberEntity.builder()
                .memberId(memberDto.getMemberId())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .nickName(memberDto.getNickName())
                .phoneNumber(memberDto.getPhoneNumber())
                .email(memberDto.getEmail())
                .build();
    }

}
