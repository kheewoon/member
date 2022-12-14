package report.member.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import report.member.common.HttpSessionUtil;
import report.member.common.code.CommonEnum;
import report.member.common.response.ErrorResponse;
import report.member.dto.MemberDto;
import report.member.common.code.MemberEnumCode;
import report.member.dto.MemberContDto;
import report.member.dto.TokenDto;
import report.member.jwt.JwtFilter;
import report.member.jwt.TokenProvider;
import report.member.service.MemberService;
import report.member.common.response.CommonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;


    @GetMapping("/cert/{phoneNumber}")
    @ApiOperation(value = "1.전화번호 인증", notes = "전화번호 인증이 가능한 전화번호 조회후 해당하는 전화번호 일시 세션에 전화번호 인증 완료 정보 저장.")
    @ApiResponses({
            @ApiResponse(code = 200010, message = "SUCESS : 전화번호 인증에 성공 하였습니다."),
            @ApiResponse(code = 200020, message = "FAIL : 전화번호 인증에 실패 하였습니다.")
    })
    @ApiImplicitParam(name = "phoneNumber", value = "전화번호", required = true, dataType = "string", paramType = "path", defaultValue = "None")
    public ResponseEntity<Object> cert(@PathVariable String phoneNumber, HttpServletRequest request){
        return new ResponseEntity<>(memberService.phoneNumberCert(phoneNumber, request), HttpStatus.OK);
    }


    @PostMapping("/signup")
    @ApiOperation(value = "2.회원가입", notes = "전화번호 인증이 완료된 이용자만 회원 가입 가능.")
    @ApiResponses({
            @ApiResponse(code = 200050, message = "SUCESS : 회원정보가 정상적으로 등록 되었습니다."),
            @ApiResponse(code = 200060, message = "FAIL : 회원정보 저장에 실패 하였습니다.")
    })
    public ResponseEntity<Object> signup(@Valid @RequestBody MemberDto memberDto){

        return new ResponseEntity<>(memberService.memberSave(memberDto), HttpStatus.OK);
    }


    @PostMapping("/login")
    @ApiOperation(value = "3.로그인 (JWT 토큰 발급)", notes = "로그인 완료시 <strong>JWT 토큰</strong>을 발급 받는다.")
    @ApiResponses({
            @ApiResponse(code = 200080, message = "SUCESS : 회원 정보가 조회 되었습니다.")
    })
    public ResponseEntity<Object> login(@Valid @RequestBody MemberContDto memberContDto){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberContDto.getMemberId(), memberContDto.getPassword());

        //회원 아이디, 비밀번호 검증
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //검증된 회원 정보 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //JWT 토큰 생성
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new CommonResponse(CommonEnum.STATUS_SUCCESS.getName(), MemberEnumCode.LOGIN_SUCESS,new TokenDto(jwt)), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/me")
    @ApiOperation(value = "4.회원 정보 조회", notes = "<strong>JWT 토큰</strong>을 Authorization 헤더로 입력받아 유저 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200080, message = "SUCESS : 회원 정보가 조회 되었습니다."),
            @ApiResponse(code = 200090, message = "FAIL : 존재하지 않는 회원 정보입니다.")
    })
    public ResponseEntity<Object> me(){
        return new ResponseEntity<>(new CommonResponse(CommonEnum.STATUS_SUCCESS.getName(), MemberEnumCode.FOUND_MEMBER, memberService.searchMember()), HttpStatus.OK);
    }

    @PostMapping("/reset-pwd")
    @ApiOperation(value = "5.패스워드 재설정", notes = "<strong>Http Session</strong>에 저장된 휴대폰번호와 JSON 데이터로 넘겨준 MEMBER_ID의 조합으로 회원정보 조회후 패스워드 재설정")
    @ApiResponses({
            @ApiResponse(code = 200100, message = "SUCESS : 비밀번호가 재설정 되었습니다."),
            @ApiResponse(code = 200101, message = "FAIL : 비밀번호가 재설정에 실패 하였습니다."),
    })
    public ResponseEntity<Object> resetPwd(@Valid @RequestBody MemberContDto memberContDto){

        if(memberService.resetPassword(memberContDto).intValue() > 0){
            return new ResponseEntity<>(
                    new CommonResponse(CommonEnum.STATUS_SUCCESS.getName(), MemberEnumCode.RESET_PASSWORD_SUCESS.getCode(), MemberEnumCode.RESET_PASSWORD_SUCESS.getMessage()),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), MemberEnumCode.RESET_PASSWORD_FAIL),
                HttpStatus.OK);
    }



    @ApiOperation(value = "6.모든 세션 초기화", notes = "<strong>Http Session 초기화</strong>")
    @GetMapping("/session-invalidate")
    public void sessionInvalidate(HttpServletRequest request){
        HttpSessionUtil.sessioninvalidate();
    }

}
