package com.multi.shop.board.controller;

import com.multi.shop.board.model.dto.BoardDTO;
import com.multi.shop.board.service.BoardService;
import com.multi.shop.member.model.dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
@SessionAttributes("loginMember")
@RequiredArgsConstructor // @Autowired 자동으로 적용?
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model) {

        try {
            List<BoardDTO> boardList = boardService.selectAll();

            for (BoardDTO dto : boardList) {
                System.out.println(dto);
            }

            if (!boardList.isEmpty()) {
                model.addAttribute("list", boardList);
                return "board/list";
            } else {
                model.addAttribute("message", "게시글 불러오기 실패");
                return "common/failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "게시글 불러오기 실패");
            return "common/failed";

        }
    }

    @GetMapping("/selectone")
    public String boardDetail(@RequestParam("no") int no, Model model) {

        try {
            BoardDTO boardDTO = boardService.selectBoard(no);
            System.out.println(boardDTO);

            if (boardDTO != null) {
                model.addAttribute("b", boardDTO);
                return "board/bdetail";
            } else {
                model.addAttribute("message", "게시글 상세조회 실패");
                return "common/failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "게시글 상세조회 실패");
            return "common/failed";
        }

    }

    @GetMapping("insert")
    public String insertBoard() {
        return "board/insertform";
    }

    @PostMapping("insert")
    public String insertBoard(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {

        try {

            BoardDTO boardDTO = new BoardDTO();
            int categoryCode = Integer.parseInt(request.getParameter("category"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String writer = ((MemberDTO) model.getAttribute("loginMember")).getId();

            boardDTO.setCategoryCode(categoryCode);
            boardDTO.setTitle(title);
            boardDTO.setContent(content);
            boardDTO.setWriter(writer);

            int result = boardService.insertBoard(boardDTO);

            if (result > 0) {
                return "redirect:/board/list";
            } else {
                redirectAttributes.addFlashAttribute("message", "게시글 등록 실패");
                return "redirect:/product/common/failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "게시글 등록 실패");
            return "redirect:/product/common/failed";
        }
    }

    @GetMapping("update")
    public String boardUpdate(@RequestParam("no") int no, Model model) {
        try {
            BoardDTO boardDTO = boardService.selectBoard(no);
            model.addAttribute("b", boardDTO);

            return "board/updateform";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "게시글 불러오기 실패");

            return "common/failed";
        }
    }

    @PostMapping("update")
    public String boardUpdate(HttpServletRequest request,
                              RedirectAttributes redirectAttributes) {
        try {
            BoardDTO boardDTO = new BoardDTO();
            int no = Integer.parseInt(request.getParameter("no"));
            int categoryCode = Integer.parseInt(request.getParameter("category"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            boardDTO.setNo(no);
            boardDTO.setCategoryCode(categoryCode);
            boardDTO.setTitle(title);
            boardDTO.setContent(content);

            int result = boardService.updateBoard(boardDTO);

            if (result > 0) {
                return "redirect:/board/list";
            } else {
                redirectAttributes.addFlashAttribute("message", "게시글 수정 실패");
                return "redirect:/product/common/failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "게시글 수정 실패");
            return "redirect:/product/common/failed";
        }
    }

    @GetMapping("delete")
    public String deleteBoard(@RequestParam("no") int no, Model model) {
        try {
            int result = boardService.deleteBoard(no);
            if (result > 0) {
                model.addAttribute("successCode", "deleteBoard");
                return "common/success";
            } else {
                model.addAttribute("message", "게시글 삭제 실패");
                return "common/failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "게시글 삭제 실패");
            return "common/failed";
        }
    }

}
