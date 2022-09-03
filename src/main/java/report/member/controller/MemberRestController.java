package report.member.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import report.member.dto.MemberDto;
import report.member.common.code.MemberEnumCode;
import report.member.exception.MemberException;
import report.member.service.MemberService;
import report.member.common.response.SucessResponse;
import report.member.vo.MemberVo;


@RestController
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;

    /*
    * 전체 Category 조회
    * */
    @GetMapping("/categorys")
    @ApiOperation(value = "1.전체 Category 조회")
    public ResponseEntity<Object> categorys(@RequestBody MemberVo vo){
        JSONObject results = new JSONObject();

        var resultList = memberService.categoryList(vo);
        results.put("result", resultList);

        return new ResponseEntity<>(new SucessResponse(MemberEnumCode.CATEGORY_FIND_SUCESS, resultList), HttpStatus.OK);
    }

    /*
     * 상위 Category -> 하위 Category 조회
     * */
    @GetMapping("/categorys/{id}")
    @ApiOperation(value = "2.상위 Category 조회")
    public ResponseEntity<Object> find(@PathVariable Long id) throws MemberException {
        JSONObject results = new JSONObject();

        var resultList = memberService.find(id);
        results.put("result", resultList);

        return new ResponseEntity<>(new SucessResponse(MemberEnumCode.CATEGORY_FIND_SUCESS, resultList), HttpStatus.OK);
    }

    /*
     * Category 저장
     * */
    @PostMapping("/categorys")
    @ApiOperation(value = "3.Category 저장")
    public ResponseEntity<Object> save(@RequestBody MemberDto dto){

        return new ResponseEntity<>(new SucessResponse(MemberEnumCode.CATEGORY_SAVE_SUCESS, memberService.saveCategory(dto)), HttpStatus.CREATED);

    }

    /*
     * Category 수정
     * */
    @PatchMapping("/categorys/{id}")
    @ApiOperation(value = "4.Category 수정")
    public ResponseEntity<Object> modify(@PathVariable Long id, @RequestBody MemberDto dto){
        return new ResponseEntity<>(new SucessResponse(MemberEnumCode.CATEGORY_MODIFY_SUCESS, memberService.modifyCategory(id, dto)), HttpStatus.CREATED);

    }

    /*
     * Category 삭제
     * */
    @DeleteMapping("/categorys/{id}")
    @ApiOperation(value = "5.Category 삭제")
    ResponseEntity<Object> delete(@PathVariable Long id){
        return new ResponseEntity<>(memberService.deleteCategory(id), HttpStatus.CREATED);
    }

}
