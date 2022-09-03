package report.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import report.member.controller.MemberRestController;
import report.member.dto.MemberApiDto;
import report.member.service.MemberService;
import report.member.vo.MemberVo;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberRestController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class MemberRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @BeforeEach
    void setup(){

    }

    @Test
    @DisplayName("카테고리 전체 조회")
    void 카테고리_전체_조회() throws Exception {
        MemberVo vo = new MemberVo();

        given(memberService.categoryList(any())).willReturn(Arrays.asList(new MemberApiDto()));

        String expectByUsername = "$.result[0].categoryNm";

        mockMvc.perform(
                get("/categorys")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(expectByUsername).exists())

                .andDo(print());

        verify(memberService).categoryList(refEq(vo));
    }


}
