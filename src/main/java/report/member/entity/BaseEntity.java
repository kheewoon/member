package report.member.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    
    /*
    * 생성시간
    * */
    @CreatedDate
    private LocalDateTime createDate;
    
    /*
    * 마지막 수정 시간
    * */
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
