package report.member.dto;

import lombok.*;
import report.member.entity.MemberEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberApiDto {


    private String memberId;

    private String name;

    private String nickName;

    private String phoneNumber;

    private String email;

    public static MemberApiDto dtoConvert(MemberEntity memberEntity){
        return MemberApiDto.builder()
                .memberId(memberEntity.getMemberId())
                .name(memberEntity.getName())
                .nickName(memberEntity.getNickName())
                .phoneNumber(memberEntity.getPhoneNumber())
                .email(memberEntity.getEmail())
                .build();
    }

    /*@Data
    @NoArgsConstructor
    public static class ParentCategoryApiDto{
        public ParentCategoryApiDto(long id, String categoryNm) {
            this.id = id;
            this.categoryNm = categoryNm;
        }

        private long id;
        private String categoryNm;
    }*/
}
