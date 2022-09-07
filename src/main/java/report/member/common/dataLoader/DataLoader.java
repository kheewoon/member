package report.member.common.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import report.member.entity.PhoneNumberAclEntity;
import report.member.repository.PhoneNumberAclRepository;

import java.util.Arrays;

@Component
public class DataLoader {

    private PhoneNumberAclRepository phoneNumberAclRepository;

    @Autowired
    public DataLoader(PhoneNumberAclRepository phoneNumberAclRepository){
        this.phoneNumberAclRepository = phoneNumberAclRepository;
        LoadUsers();
    }

    private void LoadUsers(){
        phoneNumberAclRepository.saveAll(
                Arrays.asList(
                        PhoneNumberAclEntity.builder().phoneNumber("010-7372-1471").build(),
                        PhoneNumberAclEntity.builder().phoneNumber("010-7372-1472").build(),
                        PhoneNumberAclEntity.builder().phoneNumber("010-7372-1473").build(),
                        PhoneNumberAclEntity.builder().phoneNumber("010-7372-1474").build(),
                        PhoneNumberAclEntity.builder().phoneNumber("010-4949-5959").build(),
                        PhoneNumberAclEntity.builder().phoneNumber("010-7777-4235").build(),
                        PhoneNumberAclEntity.builder().phoneNumber("010-9999-6326").build(),
                        PhoneNumberAclEntity.builder().phoneNumber("010-8888-7326").build()
                )
        );
    }

}
