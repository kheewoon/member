package report.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import report.member.entity.PhoneNumberAclEntity;

import java.util.Optional;

@Repository
public interface PhoneNumberAclRepository extends JpaRepository <PhoneNumberAclEntity, Long> {
    Optional<PhoneNumberAclEntity> findByPhoneNumber(String phoneNumber);
}
