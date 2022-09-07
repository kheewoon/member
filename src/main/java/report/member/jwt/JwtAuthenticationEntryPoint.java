package report.member.jwt;

import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import report.member.common.code.CommonEnum;
import report.member.common.code.JwtTokenCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {


        String exception = (String)request.getAttribute("exception");

        if(exception == null) {
            setResponse(response, JwtTokenCode.UNKNOWN_ERROR);
        }
        //잘못된 타입의 토큰인 경우
        else if(exception.equals(JwtTokenCode.WRONG_TYPE_TOKEN.getCode())) {
            setResponse(response, JwtTokenCode.WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if(exception.equals(JwtTokenCode.EXPIRED_TOKEN.getCode())) {
            setResponse(response, JwtTokenCode.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if(exception.equals(JwtTokenCode.UNSUPPORTED_TOKEN.getCode())) {
            setResponse(response, JwtTokenCode.UNSUPPORTED_TOKEN);
        }
        else {
            setResponse(response, JwtTokenCode.WRONG_TOKEN);
        }

        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, JwtTokenCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("code", exceptionCode.getCode());
        responseJson.put("message", exceptionCode.getMessage());
        responseJson.put("status", CommonEnum.STATUS_FAIL.getName());


        response.getWriter().print(responseJson);
    }
}
