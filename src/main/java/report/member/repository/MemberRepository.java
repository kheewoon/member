package report.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import report.member.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository <MemberEntity, Long> {
    Optional<MemberEntity> findByMemberId(String memberId);

    Optional<MemberEntity> findByMemberIdAndPhoneNumber(String memberId, String phoneNumber);
}
