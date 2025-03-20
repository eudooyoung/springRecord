package com.multi.crud.service;

import com.multi.crud.CCrudTfTddApplication;
import com.multi.crud.config.MyBatisConfig;
import com.multi.crud.model.dto.CategoryDTO;
import com.multi.crud.model.dto.MenuDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {CCrudTfTddApplication.class, MyBatisConfig.class})
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    @Disabled
        // 전체 테스트에서 제외
    void 매퍼_의존성주입_테스트() {
        assertNotNull(menuService);
        assertThat(menuService).isNotNull();
    }

    @Test
    @DisplayName("전체메뉴_조회용_테스트")
    void findAllMenu() {
        // when: 어떤 시점에, 어떤 동작을 했을 때
        List<MenuDTO> menulist = menuService.findAllMenu();

        // then
        assertThat(menulist).isNotNull();
    }

    @Test
    void 카테고리조회용_테스트() {
        //when
        List<CategoryDTO> categoryList = menuService.findAllCategory();
        //then
        assertThat(categoryList).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("메뉴 등록 성공 테스트")
    void testRegisterMenuSuccess() throws Exception {
        //given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCode(6);
        MenuDTO menu = MenuDTO.builder()
                .name("test일식2")
                .price(1000)
                .category(categoryDTO)
                .orderableStatus("Y")
                .build();

        //when
        boolean result = menuService.registerMenu(menu);

        //then
        assertThat(result).isTrue();
    }

}
