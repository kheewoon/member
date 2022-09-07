package report.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import report.member.common.HttpSessionUtil;
import report.member.common.code.CommonEnum;
import report.member.dto.MemberApiDto;
import report.member.dto.MemberDto;
import report.member.common.code.MemberEnumCode;
import report.member.dto.MemberContDto;
import report.member.entity.MemberEntity;
import report.member.exception.MemberException;
import report.member.repository.MemberQueryRepository;
import report.member.repository.MemberRepository;
import report.member.common.response.CommonResponse;
import report.member.repository.PhoneNumberAclRepository;
import report.member.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final MemberQueryRepository memberQueryRepository;

    private final PhoneNumberAclRepository phoneNumberAclRepository;

    /**
     * 전화번호 인증 및 인증 완료 세션 생성
     */
    @Transactional
    public CommonResponse phoneNumberCert(String phoneNumber, HttpServletRequest request){

        phoneNumber = phoneNumber.replaceAll("-","");

        //인증 가능한 전화번호 조회
        var findPhoneNumberAclEntity = phoneNumberAclRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new MemberException(MemberEnumCode.AUTH_CODE_SEND_FAIL));

        //전화번호 인증 완료 세션 생성
        HttpSessionUtil.setPhoneNumberCertAtSession(request, phoneNumber);

        return new CommonResponse(CommonEnum.STATUS_SUCCESS.getName(), MemberEnumCode.AUTH_CODE_SEND_SUCESS);
    }

    /**
     * 회원 가입
     */
    @Transactional
    public CommonResponse memberSave(MemberDto memberDto) throws MemberException {

        //중복 가입 여부 조회
        if(duplicationMember(memberDto)){
            var savedMemberEntity = memberRepository.save(
                    MemberEntity.entityConvert(memberDto)
            );
            
            //회원정보 저장 실패
            if(null == savedMemberEntity){
                return new CommonResponse(CommonEnum.STATUS_FAIL.getName(), MemberEnumCode.MEMBER_SAVE_FAIL);
            }

            //회원정보 저장 성공
            return new CommonResponse(CommonEnum.STATUS_SUCCESS.getName(), MemberEnumCode.MEMBER_SAVE_SUCESS, MemberApiDto.dtoConvert(
                    savedMemberEntity
            ));
        }else{
            //중복가입
            return new CommonResponse(CommonEnum.STATUS_FAIL.getName(), MemberEnumCode.MEMBER_DUPLICATION);
        }
    }

    /**
     * 회원 정보 조회
     * */
    public MemberApiDto searchMember(){
        return MemberApiDto.dtoConvert(
                findMember()
        );
    }

    /**
     * SecurityContextHolder에서 저장된 memberId로 유저 정보 조회
     * */
    @Transactional
    public MemberEntity findMember(){
        return SecurityUtil.getCurrentUserId()
                .flatMap(userId -> memberRepository.findByMemberIdAndPhoneNumber(userId, HttpSessionUtil.getPhoneNumberSession()))
                .orElseThrow(() -> new MemberException(MemberEnumCode.NOT_FOUND_MEMBER)
                );
    }

    /**
     * 패스워드 재설정
     */
    @Transactional
    public Long resetPassword(MemberContDto memberContDto){

        //회원 아이디 기반 회원 정보 조회 없을시 exception 처리
        var findMemberEntity = memberRepository.findByMemberIdAndPhoneNumber(memberContDto.getMemberId(), HttpSessionUtil.getPhoneNumberSession()).orElseThrow(() -> new MemberException(MemberEnumCode.NOT_FOUND_MEMBER));

        //패스워드 암호화
        memberContDto.passwordEncode();
        return memberQueryRepository.resetPassword(MemberDto.builder().memberId(findMemberEntity.getMemberId()).password(memberContDto.getPassword()).build());
    }


    /**
     * 유저 중복 체크를 위한 유저 정보 조회
     * */
    @Transactional
    public boolean duplicationMember(MemberDto memberDto){
        var findMemberEntity = memberRepository.findByMemberIdOrPhoneNumber(memberDto.getMemberId(), HttpSessionUtil.getPhoneNumberSession());

        return findMemberEntity.isEmpty();
    }


}
