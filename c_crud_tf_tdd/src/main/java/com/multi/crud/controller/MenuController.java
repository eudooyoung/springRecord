package com.multi.crud.controller;

import com.multi.crud.model.dto.CategoryDTO;
import com.multi.crud.model.dto.MenuDTO;
import com.multi.crud.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/menu") // url 받아오기
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public ModelAndView findMenuList(ModelAndView mv) {

        List<MenuDTO> menuList = menuService.findAllMenu();
        menuList.stream().forEach(System.out::println);

        mv.addObject("menuList", menuList);
        mv.setViewName("menu/list");

        return mv;
    }

    @GetMapping(value = "/category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findAllCategory() {
        return menuService.findAllCategory();
    }

    @PostMapping("/register")
    public ModelAndView registerMenu(ModelAndView mv, MenuDTO menuDTO/*@ModelAttribute 생략*/, RedirectAttributes rttr) throws Exception {
        menuService.registerMenu(menuDTO);
        mv.setViewName("redirect:/menu/list");
        rttr.addFlashAttribute("successMessage", "신규 메뉴 등록 성공");
        return mv;
    }
}
