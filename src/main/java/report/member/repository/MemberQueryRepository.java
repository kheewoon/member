package report.member.repository;

import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import report.member.dto.MemberApiDto;
import report.member.dto.MemberDto;
import report.member.entity.MemberEntity;
import report.member.entity.QMemberEntity;
import report.member.vo.MemberVo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;


}
