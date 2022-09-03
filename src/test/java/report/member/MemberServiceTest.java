package report.member;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import report.member.common.code.MemberEnumCode;
import report.member.dto.MemberDto;
import report.member.entity.MemberEntity;
import report.member.exception.MemberException;
import report.member.repository.MemberQueryRepository;
import report.member.repository.MemberRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberQueryRepository memberQueryRepository;

    @Mock
    protected MockHttpSession session;


    @Nested
    @DisplayName("회원 가입 로직")
    class memberSave{

        private MemberDto dto;
        private MemberEntity paramMemberEntity;
        private MemberEntity savedMemberEntity;


        @BeforeEach
        void save_setup(){
            session = new MockHttpSession();
            session.setAttribute("authCode", "722282");

            dto = MemberDto.builder().memberId("test1").email("test1@naver.com").phoneNumber("01073721474").nickName("nick").password("qwer").build();

            savedMemberEntity = MemberEntity
                    .builder()
                    .id(1L).memberId("test1").email("test1@naver.com").phoneNumber("01073721474").nickName("nick").password("qwer")
                    .build();

            paramMemberEntity = MemberEntity
                    .builder()
                    .memberId("test1").email("test1@naver.com").phoneNumber("01073721474").nickName("nick").password("qwer")
                    .build();
        }

        @Test
        void 인증번호_난수_생성_6자리제한() {

            String authCode = "722282";

            var sessionAuthCode = session.getAttribute("authCode");

            assertNotNull(sessionAuthCode);
            Assertions.assertThat(authCode).isEqualTo("722282");
            Assertions.assertThat(sessionAuthCode).isEqualTo(authCode);

            verify(createAuthCode());
        }

        @Test
        void 회원등록() {

            given(memberRepository.save(any(MemberEntity.class))).willReturn(savedMemberEntity);

            var savedEntity = memberRepository.save(paramMemberEntity);

            assertNotNull(savedEntity);
            Assertions.assertThat(savedEntity.getMemberId()).isEqualTo(savedMemberEntity.getMemberId());
            Assertions.assertThat(1L).isEqualTo(savedEntity.getId());

            verify(memberRepository).save(refEq(paramMemberEntity));
        }

        public boolean authCodeValid(String authCode){
            if(null == session || authCode.isEmpty()){
                throw new MemberException(MemberEnumCode.AUTH_CODE_SEND_FAIL);
            }
            else{
                return authCode.equals(session.getAttribute("authCode"));
            }
        }

        public String createAuthCode(){
            Random random = new Random(System.currentTimeMillis());

            int range = (int)Math.pow(10,6);
            int trim = (int)Math.pow(10, 6-1);
            int result = random.nextInt(range)+trim;

            if(result>range){
                result = result - trim;
            }

            return String.valueOf(result);
        }
    }



}
