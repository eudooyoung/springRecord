package com.multi.crud.model.dao;

import com.multi.crud.model.dto.CategoryDTO;
import com.multi.crud.model.dto.MenuDTO;

import java.util.List;

public interface MenuMapper { // ctrl + shift + t: 테스트 클래스 생성

    List<MenuDTO> findAllMenu();


    MenuDTO findMenuByCode(int code);

    List<CategoryDTO> findAllCategory();

    int registerMenu(MenuDTO newMenu);
}
