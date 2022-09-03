package report.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import report.member.dto.MemberApiDto;
import report.member.dto.MemberDto;
import report.member.common.code.MemberEnumCode;
import report.member.exception.MemberException;
import report.member.repository.MemberQueryRepository;
import report.member.repository.MemberRepository;
import report.member.common.response.SucessResponse;
import report.member.vo.MemberVo;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberQueryRepository memberQueryRepository;

    /**
     * 전체 Category 조회
     *   ㄴ하위 Category 조회
     */
    public List<MemberApiDto> categoryList(MemberVo vo) throws MemberException {

        return Arrays.asList(new MemberApiDto());
    }

    /**
     * 상위 Category 조회
     *   ㄴ하위 Category 조회
     */
    public MemberApiDto find(Long searchCategoryId) throws MemberException {

        return new MemberApiDto();
    }

    /**
     * Category 저장
     */
    public MemberApiDto saveCategory(MemberDto dto){

        return new MemberApiDto();

        //저장후 데이터 조회후 리턴
        //return memberQueryRepository.findCategoryOne(memberRepository.save(categoryEntity).getId()).orElseThrow(() -> new MemberException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    /**
     * Category 수정
     */
    @Transactional
    public MemberApiDto modifyCategory(Long id, MemberDto dto){

       return new MemberApiDto();
    }

    /**
     * Category 삭제
     * 연관관계 매핑되어 있는 자식 Category 일괄 삭제
     */
    @Transactional
    public SucessResponse deleteCategory(Long id){


        return new SucessResponse(MemberEnumCode.CATEGORY_DELETE_FAIL.getCode(), MemberEnumCode.CATEGORY_DELETE_FAIL.getMessage());
    }


}
