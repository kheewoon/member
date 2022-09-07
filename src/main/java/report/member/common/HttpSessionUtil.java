package report.member.common;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import report.member.common.code.MemberEnumCode;
import report.member.exception.MemberException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@UtilityClass
public class HttpSessionUtil {

    public void setPhoneNumberCertAtSession(HttpServletRequest request, String phoneNumber){
        HttpSession httpSession = request.getSession();
        //전화번호 인증 완료여부 세션 세팅
        httpSession.setAttribute("phoneNumberCertAt", "Y");
        //전화번호 세션 세팅
        httpSession.setAttribute("phoneNumber", phoneNumber);
        httpSession.setMaxInactiveInterval(5*60);
    }
    
    //세션에서 전화번호 인증 여부 값을 가져온다.
    public boolean getPhoneNumberCertAtSession(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        return (null != httpSession.getAttribute("phoneNumberCertAt") && "Y".equals(httpSession.getAttribute("phoneNumberCertAt").toString()));
    }
    
    //세션에서 전화번호 값을 가져온다.
    public String getPhoneNumberSession(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        if(null == httpSession.getAttribute("phoneNumber") || httpSession.getAttribute("phoneNumber").toString().isEmpty()){
            throw new MemberException(MemberEnumCode.PHONE_NUMBER_CERT_NOT_FOUND);
        }
        return httpSession.getAttribute("phoneNumber").toString().replaceAll("-","");
    }
    
    //세션 초기화
    public void sessioninvalidate(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
    }

}
