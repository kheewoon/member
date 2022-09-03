package report.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import report.member.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository <MemberEntity, Long> {
    Optional<MemberEntity> findByIdAndDeleteFlag(Long id, String deleteFlag);

    List<MemberEntity> findByDepthAndDeleteFlag(int depth, String deleteFalg);
}