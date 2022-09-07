package report.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 회원 로그인 OR 회원 패스워드 재설정용 DTO
 */
public class MemberContDto {

    @ApiModelProperty(example = "gmldns46")
    @NotBlank(message = "회원아이디는 필수값 입니다.")
    private String memberId;

    @ApiModelProperty(example = "qwer")
    @NotBlank(message = "비밀번호는 필수값 입니다.")
    private String password;

    //비밀번호 단방향 암호화
    public void passwordEncode(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = (null != this.password && !this.password.isEmpty()) ? passwordEncoder.encode(this.password) : "";
    }

}
