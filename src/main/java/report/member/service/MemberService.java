package report.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import report.member.common.code.CommonEnum;
import report.member.common.response.ErrorResponse;
import report.member.dto.MemberApiDto;
import report.member.dto.MemberDto;
import report.member.common.code.MemberEnumCode;
import report.member.entity.MemberEntity;
import report.member.exception.MemberException;
import report.member.repository.MemberQueryRepository;
import report.member.repository.MemberRepository;
import report.member.common.response.SucessResponse;
import report.member.util.SecurityUtil;
import report.member.vo.MemberVo;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService <T> {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final MemberQueryRepository memberQueryRepository;

    /**
     * 회원 가입
     */
    public T memberSave(MemberDto memberDto) throws MemberException {

        if(duplicationMember(memberDto)){
            var savedMemberEntity = memberRepository.save(
                    MemberEntity.entityConvert(memberDto)
            );

            if(null == savedMemberEntity){
                return (T) new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), MemberEnumCode.MEMBER_SAVE_FAIL);
            }


            return (T) new SucessResponse(CommonEnum.STATUS_SUCCESS.getName(), MemberEnumCode.MEMBER_SAVE_SUCESS, MemberApiDto.dtoConvert(
                    savedMemberEntity
            ));
        }

        return (T) new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), MemberEnumCode.MEMBER_SAVE_FAIL);

    }

    /**
     * SecurityContextHolder에서 저장된 memberId로 유저 정보 조회
     * */
    @Transactional
    public MemberApiDto findMember(){
        return MemberApiDto.dtoConvert(
                SecurityUtil.getCurrentUserId()
                        .flatMap(memberRepository::findByMemberId)
                        .orElseThrow(() -> new MemberException(MemberEnumCode.NOT_FOUND_MEMBER)
                        )
        );
    }

    /**
     * 유저 중복 체크를 위한 유저 정보 조회
     * */
    @Transactional
    public boolean duplicationMember(MemberDto memberDto){
        var findMemberEntity = memberRepository.findByMemberId(memberDto.getMemberId());

        return findMemberEntity.isEmpty();
    }


}
