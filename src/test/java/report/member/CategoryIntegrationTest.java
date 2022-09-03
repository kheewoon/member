package report.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import report.member.dto.MemberDto;
import report.member.entity.MemberEntity;
import report.member.repository.MemberQueryRepository;
import report.member.repository.MemberRepository;
import report.member.service.MemberService;
import report.member.vo.MemberVo;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
    *
    * 테스트용 @EnableJpaAuditing configuration이 설정되어 있어
    * report.category.CategoryApplication.java -> @EnableJpaAuditing 주석처리후 통합테스트 실행
    *
* */


@Transactional
@SpringBootTest
public class CategoryIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberQueryRepository memberQueryRepository;



    @Nested
    @DisplayName("카테고리 조회 통합테스트")
    class CategoryFind{

        private MemberEntity parent;

        @BeforeEach
        void setup(){

        }

        @Test
        void 전체_Category_조회(){
            //given
            MemberVo vo = new MemberVo();

            //when
            var resultList = memberService.categoryList(vo);

            //then
            Assertions.assertThat(resultList.size()).isEqualTo(5);
        }

        @Test
        void 상위_Category_조회(){

           /* //given
            Long id = parent.getId();

            //when
            var result = memberService.find(id);

            //then
            //상위 카테고리 1개 검증
            assertNotNull(result);
            //상위 카테고리 -> 자식 카테고리 2개 검증
            Assertions.assertThat(result.getChildCategoryList().size()).isEqualTo(2);
            //3depth 자식 카테고리 2개 검증
            Assertions.assertThat(result.getChildCategoryList().get(0).getChildCategoryList().size()).isEqualTo(2);*/
        }
    }




}
