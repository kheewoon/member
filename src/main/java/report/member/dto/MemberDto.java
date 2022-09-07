package report.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import report.member.entity.MemberEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    
    /*
    * 회원아이디
    * */
    @ApiModelProperty(example = "gmldns46")
    @NotBlank(message = "회원아이디는 필수값 입니다.")
    private String memberId;
    
    /*
    * 비밀번호
    * */
    @ApiModelProperty(example = "qwer")
    @NotBlank(message = "비밀번호는 필수값 입니다.")
    private String password;
    
    /*
    * 이름
    * */
    @ApiModelProperty(example = "권희운")
    @NotBlank(message = "이름은 필수값 입니다.")
    private String name;

    /*
    * 닉네임
    * */
    @ApiModelProperty(example = "닉네임1")
    @NotBlank(message = "닉네임은 필수값 입니다.")
    private String nickName;
    
    /*
    * 이메일
    * */
    @ApiModelProperty(example = "gmldns46")
    @NotBlank(message = "이메일은 필수값 입니다.")
    private String email;


    //비밀번호 단방향 암호화
    public void passwordEncode(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = (null != this.password && !this.password.isEmpty()) ? passwordEncoder.encode(this.password) : "";
    }

    public static MemberDto dtoConvert(MemberEntity memberEntity){

        if(null != memberEntity){
            return MemberDto.builder()
                    //.id(memberEntity.getId())
                    .memberId(memberEntity.getMemberId())
                    .password(memberEntity.getPassword())
                    .name(memberEntity.getName())
                    .nickName(memberEntity.getNickName())
                    .email(memberEntity.getEmail())
                    .build();
        }
        return new MemberDto();
    }

}
