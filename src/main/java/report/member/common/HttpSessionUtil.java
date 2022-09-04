package report.member.common;

import lombok.experimental.UtilityClass;
import report.member.common.code.MemberEnumCode;
import report.member.exception.MemberException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@UtilityClass
public class HttpSessionUtil {

    public void setPhoneNumberCertAtSession(HttpServletRequest request, String phoneNumber){
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("phoneNumberCertAt", "Y");
        httpSession.setAttribute("phoneNumber", phoneNumber);
        httpSession.setMaxInactiveInterval(5*60);
    }

    public boolean getPhoneNumberCertAtSession(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        return (null != httpSession.getAttribute("phoneNumberCertAt") && "Y".equals(httpSession.getAttribute("phoneNumberCertAt").toString()));
    }

    public String getPhoneNumberSession(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        if(null == httpSession.getAttribute("phoneNumber")){
            throw new MemberException(MemberEnumCode.PHONE_NUMBER_CERT_NOT_FOUND);
        }
        return httpSession.getAttribute("phoneNumber").toString();
    }

    public void sessioninvalidate(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
    }

}
