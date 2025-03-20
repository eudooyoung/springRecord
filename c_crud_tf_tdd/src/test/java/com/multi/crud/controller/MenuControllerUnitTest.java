package com.multi.crud.controller;

import com.multi.crud.model.dto.CategoryDTO;
import com.multi.crud.model.dto.MenuDTO;
import com.multi.crud.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MenuControllerUnitTest {

    private MockMvc mockMvc; // 컨트롤러 테스트시 사용

    @Mock // 받아올 모조품
    private MenuService menuService;

    @InjectMocks // 사용할 모조품
    private MenuController menuController;

    private List<MenuDTO> allMockMenus;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
        mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();

        allMockMenus = new ArrayList<>();

        for (int i = 1; i <= 13; i++) {
            allMockMenus.add(MenuDTO.builder()
                    .name("Menu" + i)
                    .price(i * 1000)
                    .category(new CategoryDTO(5, "디저트", 1))
                    .orderableStatus("Y")
                    .build());
        }
    }

    @Test
    void findMenuList() throws Exception {
        //given
        given(menuService.findAllMenu()).willReturn(allMockMenus);

        mockMvc.perform(get("/menu/list"))
                .andExpect(status().isOk()) // 상태코드가 200
                .andExpect(forwardedUrl("menu/list")) // 포워딩 될 뷰이름 일치되게
                .andDo(print()); // 응답 내용을 출력
    }

    @Test
    void findAllCategory() throws Exception {
        List<CategoryDTO> mockCategoryList = Collections.singletonList(new CategoryDTO(5, "테스트카테고리", 0));
        given(menuService.findAllCategory()).willReturn(mockCategoryList);

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
                .andExpect(status().is3xxRedirection()) // 리다이렉트 확인
                .andExpect(redirectedUrl("/menu/list"))
                .andDo(print());
    }
}