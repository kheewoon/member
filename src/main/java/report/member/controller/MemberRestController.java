package report.member.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import report.member.common.code.CommonEnum;
import report.member.dto.MemberDto;
import report.member.common.code.MemberEnumCode;
import report.member.dto.TokenDto;
import report.member.exception.MemberException;
import report.member.jwt.JwtFilter;
import report.member.jwt.TokenProvider;
import report.member.service.MemberService;
import report.member.common.response.SucessResponse;
import report.member.vo.MemberVo;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /*
    * 회원 가입
    * */
    @PostMapping("/signup")
    @ApiOperation(value = "1.회원가입")
    public ResponseEntity<Object> signup(@RequestBody MemberDto memberDto){

        return new ResponseEntity<>(memberService.memberSave(memberDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody MemberDto memberDto){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberDto.getMemberId(), memberDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/me")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 정보 조회 API")
    })
    @ApiOperation(value = "유저 정보 조회 API", notes = "<strong>JWT 토큰</strong>을 Authorization 헤더로 입력받아 유저 정보를 조회한다.")
    public ResponseEntity<Object> me(){
        return new ResponseEntity<>(new SucessResponse(CommonEnum.STATUS_SUCCESS.getName(), MemberEnumCode.FOUND_MEMBER, memberService.findMember()), HttpStatus.OK);
    }

}
