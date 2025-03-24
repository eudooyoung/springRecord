package com.multi.crud.controller;

import com.multi.crud.model.dto.CategoryDTO;
import com.multi.crud.model.dto.MenuDTO;
import com.multi.crud.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Controller
@RequestMapping("/menu") // url 받아오기
@Slf4j
public class MenuController {

    private final MenuService menuService;
    private final MessageSource messageSource;

    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

//    @GetMapping("/list") // get방식, mapping된 url
//    public ModelAndView findMenuList(ModelAndView mv) { // ModelAndView: 모델과 뷰를 같이 관리해줌
//
//        List<MenuDTO> menuList = menuService.findAllMenu();  // sevice 에서 받아온 정보
//        menuList.stream().forEach(System.out::println);
//
//        mv.addObject("menuList", menuList); // view 쪽에 넘겨줄 데이터의 변수 이름과 데이터 작성
//        mv.setViewName("menu/list"); // DispatcherServlet의 ViewResolver를 통해 처리됨
//
//        return mv;
//    }

    @GetMapping("/list") // /menu/list... 리다이렉트 되는 경로와 매핑 경로가 동일함.
    public void getMenuListPage() {
    }

    @GetMapping("/pagination")
    @ResponseBody // 데이터 전환, 레스트방식
    public Map<String, Object> getPageMenus(@RequestParam(name = "page", defaultValue = "1") int page) {
        log.info("page번호: {}", page);
        int pageSize = 10;
        List<MenuDTO> menus = menuService.getMenuList(page, pageSize);
        int totalMenus = menuService.getMenuCount();
        int totalPage = (int) Math.ceil((double) totalMenus / pageSize);

        Map<String, Object> response = new HashMap<>();

        response.put("menuList", menus);
        response.put("currentPage", page);
        response.put("totalPages", totalPage);

        return response;
    }

    @GetMapping(value = "/category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findAllCategory() {
        return menuService.findAllCategory();
    }

    @GetMapping("/register")
    public void registerPage() {

    }

    @PostMapping("/register")
    public ModelAndView registerMenu(ModelAndView mv, MenuDTO menuDTO/*@ModelAttribute 생략*/, RedirectAttributes rttr,
                                     Locale locale) throws Exception {
        menuService.registerMenu(menuDTO);
        mv.setViewName("redirect:/menu/list");
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registerMenu", null, locale));// 메세지 소스 사용
//        mv.addObject("successMessage", messageSource.getMessage("registerMenu", null, locale));
        return mv;
    }


}
