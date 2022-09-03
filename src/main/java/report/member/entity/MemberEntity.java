package report.member.entity;

import lombok.*;
import org.hibernate.annotations.*;
import report.member.dto.MemberDto;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "password", nullable = false, length = 14)
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



    /*@PrePersist
    public void setField(){
        this.depth = (this.depth > 0) ? this.depth : 1;
        this.deleteFlag = (null == this.deleteFlag || this.deleteFlag.isEmpty()) ? "N":this.deleteFlag;
    }*/


    public static MemberEntity entityConvert(MemberDto memberDto){
        return MemberEntity.builder()
                .id(memberDto.getId())
                .build();
    }

}