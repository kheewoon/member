# 회원 관리 API : 권희운

## 사용 기술 스택 : java, spring boot, jpa, QueryDsl, Embedded H2 DB, Spring Security, JWT

## 빌드 방법
WORK_DIR :  member

jar 파일 빌드 (테스트코드 제외) \
./gradlew build -x test


## 서버 구동 방법
빌드된 jar 파일 실행 \
java -jar ./build/libs/member-0.0.1-SNAPSHOT.jar 


## 구현방법
1.유저 로그인시 SecurityContextHolder에 인증 정보 저장 \
2.SecurityContextHolder에 담겨있는 인증정보를 토대로 JWT 토큰 생성 \
3.JWT 토큰을 Authorization 헤더에 실어 API 요청 \


    


## API 사용 가이드
### Swagger Ui : http://localhost:8080/swagger-ui/#/member-rest-controller

### API Status Code
    /*
     * 200010 : 인증번호가 전송 되었습니다.
     */

    /*
     * 200020 : 인증번호에 실패 하였습니다.
     */

    /*
     * 201030 : 인증이 완료 되었습니다.
     */

    /*
     * 201040 : 인증에 실패 하였습니다.
     */

    /*
     * 200050 : 회원정보가 정상적으로 저장 되었습니다.
     */

    /*
     * 200060 : 회원정보 저장에 실패 하였습니다.
     */

    /*
     * 200070 : 로그인 인증토큰이 발급 되었습니다.
     */

    /*
     * 200070 : 로그인 실패
     */

    /*
     * 200080 : 회원 정보가 조회 되었습니다.
     */

    /*
     * 200090 : 존재하지 않는 회원 정보입니다.
     */

    /*
     * 200100 : 비밀번호가 재설정 되었습니다.
     */

    /*
     * 200101 : 비밀번호가 재설정에 실패 하였습니다.
     */

    /*
     * 200102 : 전화번호 인증후 다시 시도해 주시기 바랍니다.
     */
       

### 전화번호 인증
GET : http://localhost:8080/member/cert/{phoneNumber}
```c
body : {}
```

### 회원 가입
GET : http://localhost:8080/member/signup
```c
body : {
    "memberId": "gmldns46",
    "password": "qwer",
    "name" : "권희운",
    "nickName": "nickname",
    "phoneNumber" : "010-7372-1474",
    "email" : "gmldns46@naver.com"
} 
```

### 카테고리 저장 (부모객체 미 포함) 
POST : http://localhost:8080/categorys 

```c
body : { 
    "categoryNm" : "카테고리1"
} 
```


### 로그인(JWT 토큰 발급)
POST : http://localhost:8080/member/login

```c
body : {
    "memberId": "gmldns46",
    "password": "qwer"
}
```


### 내정보 조회
PATCH : http://localhost:8080/member/me

```c
Authorization header : Bearer JWT 토큰
body : {}
```


### 패스워드 재설정

PATCH : http://localhost:8080/member/reset-pwd

```c
Authorization header : Bearer JWT 토큰
body : { 
    "memberId" : "gmldns46",
    "password" : "변경할 패스워드"
}
```
