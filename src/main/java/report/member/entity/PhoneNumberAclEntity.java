package report.member.entity;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import report.member.dto.MemberDto;

import javax.persistence.*;

@Entity
@Table(name = "phone_number_acl")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class PhoneNumberAclEntity extends BaseEntity {

    @Id @Column(name = "id", nullable = false)
    @GeneratedValue
    @Comment("id")
    private Long id;

    @Column(name = "phone_number", nullable = false, length = 20)
    @Comment("전화번호")
    private String phoneNumber;



    @PrePersist
    public void setField(){
        //전화번호 하이픈 제거
        this.phoneNumber = (null != this.phoneNumber && !this.phoneNumber.isEmpty()) ? this.phoneNumber.replaceAll("-",""):"";
    }


    public static PhoneNumberAclEntity entityConvert(MemberDto memberDto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return PhoneNumberAclEntity.builder()
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }

}
