package com.multi.crud.service;

import com.multi.crud.model.dao.MenuMapper;
import com.multi.crud.model.dto.CategoryDTO;
import com.multi.crud.model.dto.MenuDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuDTO> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    public boolean registerMenu(MenuDTO menuDTO) throws Exception {
        int result = menuMapper.registerMenu(menuDTO);
        if (result <= 0) { // 에러 발생
            throw new Exception("메뉴 등록 실패");
        }
        return (result > 0) ? true : false;
    }

    public List<CategoryDTO> findAllCategory() {
        return menuMapper.findAllCategory();
    }


    public List<MenuDTO> getMenuList(int page, int pageSize) {

        int offset = (page - 1) * pageSize;

        return menuMapper.selectMenuList(offset, pageSize);
    }

    public int getMenuCount() {
        return menuMapper.countMenus();
    }
}
