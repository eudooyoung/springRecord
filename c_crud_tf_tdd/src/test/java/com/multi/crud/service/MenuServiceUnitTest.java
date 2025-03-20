package com.multi.crud.service;

import com.multi.crud.model.dao.MenuMapper;
import com.multi.crud.model.dto.MenuDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //JUnit 5에서 Mockito 확장을 사용하여 Mock 객체를 초기화하고 주입
class MenuServiceUnitTest {

    @Mock
    private MenuMapper menuMapper;

    @InjectMocks // menuService 에 @Mock 으로 생성한 객체를 주입
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
    @DisplayName("메뉴 등록 성공 테스트")
    void testRegisterMenuSuccess() throws Exception {
        //given
        MenuDTO newMenu = MenuDTO.builder().name("New Menu").price(10000).build();
        when(menuMapper.registerMenu(newMenu)).thenReturn(1);

        //when
        boolean result = menuService.registerMenu(newMenu);

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("메뉴 등록 실패 시 예외 발생 테스트")
    void testRegisterMenuFailure() {
        //given
        MenuDTO newMenu = MenuDTO.builder().name("New Menu").price(10000).build(); // jdbc 연결 안되어 있어서 간단히 작성
        when(menuMapper.registerMenu(newMenu)).thenReturn(0);

        //when
//        boolean result = menuService.registerMenu(newMenu);

        //then
        assertThatThrownBy(() -> menuService.registerMenu(newMenu))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("메뉴 등록 실패");
    }
}