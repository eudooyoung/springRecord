package com.multi.crud.model.dao;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest // 통합 테스트
@ContextConfiguration(classes = {CCrudTfTddApplication.class, MyBatisConfig.class}) // 테스트에 필요한 컨텍스트 가져오기
class MenuMapperTest {

    @Autowired // 의존성 주입
    private MenuMapper menuMapper;

    @Test
    @Disabled // 전체 테스트에서 제외
    void 매퍼_의존성주입_테스트() {
        assertNotNull(menuMapper);
        assertThat(menuMapper).isNotNull();
    }

    @Test
    @DisplayName("전체메뉴_조회용_테스트")
    void findAllMenu() {
        // given: 어떤 값이 주어졌을 때
        // when: 어떤 시점에, 어떤 동작을 했을 때
        List<MenuDTO> menulist = menuMapper.findAllMenu();

        // then: 그 결과가 기댓값과 일치 하는지 확인, 검증
        assertNotNull(menulist);
        assertThat(menulist).isNotNull();

//        menulist.stream().forEach(menu -> System.out.println(menu));
        menulist.stream().forEach(System.out::println);
    }

    @Test
    void 메뉴코드_메뉴단건조회용_테스트(){
        //given
        int code = 4;
        //when
        MenuDTO menuDTO = menuMapper.findMenuByCode(code);
        //then
        assertEquals(code, menuDTO.getCode());
        assertThat(menuDTO.getCode()).isEqualTo(code);
    }

    @Test
    void 카테고리조회용_테스트(){
        //given
        //when
        List<CategoryDTO> categoryList = menuMapper.findAllCategory();
        //then
        assertNotNull(categoryList);
        assertThat(categoryList).isNotNull();

        categoryList.stream().forEach(System.out::println);
    }

    @Test
    @Transactional  // 테스트 환경에서 자동 롤백
    void 신규메뉴추가_테스트(){
//        //given
//        MenuDTO newMenu = new MenuDTO();
//        List<CategoryDTO> categoryList = menuMapper.findAllCategory();
//        CategoryDTO categoryDTO = categoryList.get(3);
//        newMenu.setName("test");
//        newMenu.setName("5000");
//        newMenu.setCategory(categoryDTO);
//        newMenu.setOrderableStatus("Y");
//
//        //when
//        int result = menuMapper.registMenu(newMenu);
//        //then
//        assertEquals(result, 1);
//        assertThat(result).isGreaterThan(0);
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
        int result = menuMapper.registerMenu(menu);
        //then
        assertEquals(1, result);
        assertThat(result).isGreaterThan(0);
    }
}