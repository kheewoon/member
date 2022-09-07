package report.member.advice;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import report.member.common.code.CommonEnum;
import report.member.common.code.MemberEnumCode;
import report.member.common.code.SecurityCode;
import report.member.exception.ErrorException;
import report.member.exception.MemberException;
import report.member.common.code.ErrorCode;
import report.member.common.response.ErrorResponse;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        log.error("CommonException: {}", ex.getMessage());

        return new ResponseEntity<>(new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.OK);
    }

    @ExceptionHandler({
            BadCredentialsException.class
    })
    public ResponseEntity<ErrorResponse> BadCredentialsException(final BadCredentialsException ex) {
        log.error("CommonException: {}", ex.getMessage());

        return new ResponseEntity<>(new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), HttpStatus.UNAUTHORIZED.value(), SecurityCode.BAD_CREDENTIALS.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler({
            InternalAuthenticationServiceException.class
    })
    public ResponseEntity<ErrorResponse> InternalAuthenticationServiceException(final InternalAuthenticationServiceException ex) {
        log.error("CommonException: {}", ex.getMessage());

        return new ResponseEntity<>(new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), HttpStatus.UNAUTHORIZED.value(), SecurityCode.BAD_CREDENTIALS.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler({
            MemberException.class
    })
    public ResponseEntity<ErrorResponse> MemberException(final MemberException ex) {
        log.error("CommonException: {}", ex.getMemberEnumCode().getCode());

        return new ResponseEntity<>(new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), ex.getMemberEnumCode()), HttpStatus.OK);
    }

    // 400
    @ExceptionHandler({
            ErrorException.class
    })
    public ResponseEntity<ErrorResponse> BadRequestException(final ErrorException ex) {
        log.error("CommonException: {}", ex.getErrorCode());

        return new ResponseEntity<>(new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), ErrorCode.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    // 401
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity handleAccessDeniedException(final AccessDeniedException ex) {
        log.warn("error", ex);
        return new ResponseEntity<>(new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);

    }

    // 405
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity handleBadCredentialsException(final HttpRequestMethodNotSupportedException ex) {
        log.warn("error", ex);
        return new ResponseEntity<>(new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), ErrorCode.METHOD_NOT_ALLOWED), HttpStatus.METHOD_NOT_ALLOWED);
    }

    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity handleAll(final Exception ex) {
        log.info(ex.getClass().getName());
        log.error("error", ex);
        return new ResponseEntity<>(new ErrorResponse(CommonEnum.STATUS_FAIL.getName(), ErrorCode.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}