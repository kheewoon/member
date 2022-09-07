package report.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import report.member.common.HttpSessionUtil;
import report.member.common.code.MemberEnumCode;
import report.member.entity.MemberEntity;
import report.member.exception.MemberException;
import report.member.repository.MemberRepository;

import java.util.Arrays;

@Component("memberDetailsService")
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberRepository.findByMemberIdAndPhoneNumber(memberId, HttpSessionUtil.getPhoneNumberSession())
                .map(member -> createUser(memberId, member))
                .orElseThrow(() -> new MemberException(MemberEnumCode.NOT_FOUND_MEMBER));
    }

    private User createUser(String username, MemberEntity memberEntity) {
        if (null == memberEntity) {
            throw new MemberException(MemberEnumCode.NOT_FOUND_MEMBER);
        }

        return new User(memberEntity.getMemberId(),
                memberEntity.getPassword(),
                Arrays.asList());
    }
}
