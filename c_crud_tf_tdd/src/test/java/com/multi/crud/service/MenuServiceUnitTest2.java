package com.multi.crud.service;

import com.multi.crud.model.dao.MenuMapper;
import com.multi.crud.model.dto.MenuDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest // Applicationcontext 불러오기
public class MenuServiceUnitTest2 {

    @MockitoBean // mapper를 모조객체로 대체
    private MenuMapper menuMapper;

    @Autowired
    private MenuService menuService;

    @Test
    @DisplayName("전체 메뉴 조회 테스트")
    void findAllMenu() {
        //given
        List<MenuDTO> mockMenuList = Arrays.asList(
                MenuDTO.builder().name("Apple").price(5000).build(),
                MenuDTO.builder().name("Banana").price(3000).build()
        );
        when(menuMapper.findAllMenu()).thenReturn(mockMenuList);

        //when
        List<MenuDTO> menus = menuService.findAllMenu();

        //then
        assertThat(menus).isNotNull().hasSize(2);
    }

    @Test
    @DisplayName("메뉴 등록 실패 시 예외 발생 테스트")
    void testRegisterMenuFailure() {
        //given
        MenuDTO newMenu = MenuDTO.builder().name("New Menu").price(10000).build();
        when(menuMapper.registerMenu(newMenu)).thenReturn(0);

        //when
        //        boolean result = menuService.registerMenu(newMenu);

        //then
        assertThatThrownBy(() -> menuService.registerMenu(newMenu))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("메뉴 등록 실패");

        System.out.println(Mockito.mockingDetails(menuMapper).isMock());

        verify(menuMapper).registerMenu(newMenu);
        verify(menuMapper, never()).findAllMenu();
    }
}
