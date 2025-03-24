package com.multi.crud.controller;

import com.multi.crud.CCrudTfTddApplication;
import com.multi.crud.config.MyBatisConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {CCrudTfTddApplication.class, MyBatisConfig.class})
class MenuControllerTest {

    @Autowired
    private MenuController menuController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Disabled
    void testInit() {
        assertNotNull(menuController);
        assertNotNull(mockMvc);
    }

    @Test
    void findMenuList() throws Exception {
        mockMvc.perform(get("/menu/list")) // get방식으로 "/menu/list" url에 접근했을 때
                .andExpect(status().isOk()) // 상태코드가 200인지
                .andExpect(view().name("menu/list")) // 넘어갈 뷰이름이 맞는지(templates 아래)
                .andDo(print()); // 응답 내용을 출력
    }

    @Test
    void findAllCategory() throws Exception {
//        List<CategoryDTO> mockCategoryList = Collections.singletonList(new CategoryDTO(5, "테스트카테고리", 0));

        mockMvc.perform(get("/menu/category").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=UTF-8")) // 응답 json 형식 확인
                .andDo(print());
    }

    @Test
    void registerMenu() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "단위테스트메뉴");
        params.add("price", "40000");
        params.add("category.code", "5");
        params.add("orderableStatus", "Y");

        mockMvc.perform(post("/menu/register").params(params))
                .andExpect(status().is3xxRedirection()) // 리다이렉트 코드 확인
                .andExpect(redirectedUrl("/menu/list"))
                .andExpect(flash().attribute("successMessage", "신규메뉴등록에 성공하셨습니다."))
                .andDo(print());
    }
}