package report.member;

import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import report.member.config.QueryDslConfiguration;
import report.member.dto.MemberApiDto;
import report.member.dto.MemberDto;
import report.member.entity.MemberEntity;
import report.member.repository.MemberRepository;
import report.member.vo.MemberVo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase
@Import(QueryDslConfiguration.class)
@Slf4j
public class MemberQueryRepositoryTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    private MemberRepository memberRepository;

    private MemberEntity memberEntity;

    private MemberEntity parentMemberEntity;

    @BeforeEach
    void setup(){

    }



    @Test
    void 카테고리_수정(){

       /* //부모 카테고리 저장
        var parentResult = memberRepository.save(parentMemberEntity);

        //부모 카테고리 entity 세팅
        memberEntity.setParent(parentResult);
        //카테고리 저장
        var result = memberRepository.save(memberEntity);

        //카테고리 조회
        var findCategory = findCategoryOne(result.getId());

        //부모 카테고리 변경
        updateCategory(parentResult.getId(), MemberDto.builder().id(parentResult.getId()).categoryNm("부모 카테고리명 변경1").build());

        //자식 카테고리 변경
        updateCategory(result.getId(), MemberDto.builder().id(result.getId()).categoryNm("카테고리명 변경1").build());

        //변경후 카테고리 조회
        var updatedCategory = findCategoryOne(result.getId());


        //카테고리 저장후 entity, 부모 entity null 여부 테스트
        assertNotNull(result);
        assertNotNull(result.getParentCategory().getCategoryNm());
        //저장된 카테고리 조회 entity null 여부 테스트
        assertNotNull(findCategory);
        //파라미터 entity , 등록 후 반환된 entity 값 비교
        Assertions.assertThat(memberEntity.getCategoryNm()).isEqualTo(result.getCategoryNm());
        //등록 후 반환된 entity, 등록 후 조회한 entity 값 비교
        Assertions.assertThat(result.getCategoryNm()).isEqualTo(findCategory.getCategoryNm());
        //카테고리 수정후 값 검증
        Assertions.assertThat(updatedCategory.getCategoryNm()).isEqualTo("카테고리명 변경1");
        Assertions.assertThat(updatedCategory.getParentCategory().getCategoryNm()).isEqualTo("부모 카테고리명 변경1");*/
    }


}
