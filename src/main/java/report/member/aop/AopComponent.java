package report.member.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import report.member.common.HttpSessionUtil;
import report.member.common.code.MemberEnumCode;
import report.member.exception.MemberException;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AopComponent {

    //회원가입 컨트롤러
    @Pointcut("execution(* report.member.controller.MemberRestController.signup(..))")
    private void signup() {}

    //비밀번호 재설정 컨트롤러
    @Pointcut("execution(* report.member.controller.MemberRestController.resetPwd(..))")
    private void resetPwd() {}

    //회원가입, 비밀번호 재설정 컨트롤러 접근시 전화번호 인증여부 SESSION 체크
    @Before("signup() || resetPwd()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        if(!HttpSessionUtil.getPhoneNumberCertAtSession()){
            throw new MemberException(MemberEnumCode.PHONE_NUMBER_CERT_NOT_FOUND);
        }
    }
}
