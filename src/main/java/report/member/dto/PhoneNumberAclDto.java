package report.member.dto;

import lombok.*;
import report.member.entity.MemberEntity;
import report.member.entity.PhoneNumberAclEntity;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberAclDto {

    private Long id;

    private String phoneNumber;

    public static PhoneNumberAclDto dtoConvert(PhoneNumberAclEntity phoneNumberAclEntity){

        if(null != phoneNumberAclEntity){
            return PhoneNumberAclDto.builder()
                    .id(phoneNumberAclEntity.getId())
                    .phoneNumber(phoneNumberAclEntity.getPhoneNumber())
                    .build();
        }
        return new PhoneNumberAclDto();
    }

}
