package com.multi.shop.product.controller;

import com.multi.shop.member.model.dto.MemberDTO;
import com.multi.shop.product.model.dto.AttachmentDTO;
import com.multi.shop.product.model.dto.CompanyDTO;
import com.multi.shop.product.model.dto.ProductDTO;
import com.multi.shop.product.model.dto.SearchCriteria;
import com.multi.shop.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@SessionAttributes("loginMember")
@RequiredArgsConstructor // 생성자
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public String listProduct(Model model) {
        try {
            List<ProductDTO> productList = productService.selectList();
            model.addAttribute("list", productList);
            return "product/list";
        } catch (Exception e) {
            model.addAttribute("message", "제품 불러오기 실패");
            return "common/failed";
        }
    }

    @GetMapping("/selectone")
    public String detailProduct(@RequestParam("no") int pid, Model model) {

        try {
            List<ProductDTO> productList = productService.selectProduct(pid);
            System.out.println("productList: " + productList);

            if (productList != null) {
                model.addAttribute("productList", productList);
                return "product/pdetail";
            } else {
                model.addAttribute("message", "제품상세조회 실패");
                return "common/failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "제품상세조회 실패");
            return "common/failed";

        }
    }

    // 반환 url의 파일명이 같아서 return 값을 주지 않음
    @GetMapping("/insert")
    public void insertProduct() {
    }

//    파일 이름이 다를 경우
//    @GetMapping("/insert")
//    public String insertProduct(){
//        return "product/insert";
//    }

    @PostMapping("/insert")
    public String insertProduct(HttpServletRequest request,
                                @RequestParam("thumbnailImg1") MultipartFile thumbnailImg1,
                                HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            // 폼 데이터 추출
            String name = request.getParameter("name");
            String content = request.getParameter("content");
            String companyId = request.getParameter("company");
            int price = Integer.parseInt(request.getParameter("price"));

            // ProductDTO 생성 및 데이터 설정
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(name);
            productDTO.setContent(content);
            productDTO.setPrice(price);

            // 회사 정보 설정
            CompanyDTO company = new CompanyDTO();
            company.setId(companyId);
            productDTO.setCompany(company);

            // 파일 저장 루트 경로 설정
            String rootLocation = session.getServletContext().getRealPath("/");
            String saveDirectory = rootLocation + "/resources/upload/original/";
            String thumbnailDirectory = rootLocation + "/resources/upload/thumbnail/";

            // 저장 경로가 존재하지 않으면 생성
            File directory = new File(saveDirectory);
            File thumbnailDir = new File(thumbnailDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!thumbnailDir.exists()) {
                thumbnailDir.mkdirs();
            }

            // 파일들을 저장할 리스트
            List<AttachmentDTO> attachments = new ArrayList<>();

            // 각 파일 처리
            processFile(thumbnailImg1, attachments, saveDirectory, thumbnailDirectory, session);


            // ProductDTO에 파일 리스트를 설정
            productDTO.setAttachments(attachments);

            // 서비스 호출하여 제품 추가
            int result = productService.insertProduct(productDTO);

            if (result > 0) {
                redirectAttributes.addFlashAttribute("successCode", "insertProduct");
                return "redirect:/product/list";
            } else {
                redirectAttributes.addFlashAttribute("message", "제품 등록 실패!");
                return "redirect:/product/common/failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "제품 등록 중 오류 발생!");
            return "redirect:/product/common/failed";
        }
    }

    // 파일을 처리하는 메서드
    private void processFile(MultipartFile file, List<AttachmentDTO> attachments, String saveDirectory,
                             String thumbnailDirectory, HttpSession session) throws IOException {

        if (file != null && !file.isEmpty()) {
            String originFileName = file.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;
            File storeFile = new File(saveDirectory + originFileName);
            file.transferTo(storeFile);

            // 썸네일 생성
            Thumbnails.of(storeFile)
                    .size(350, 200)
                    .toFile(thumbnailDirectory + "thumbnail_" + savedFileName);

            // 파일 정보를 AttachmentDTO에 설정
            AttachmentDTO attachment = new AttachmentDTO();
            attachment.setOriginalName(originFileName);
            attachment.setSavedName(savedFileName);
            attachment.setSavePath(saveDirectory);
            attachment.setThumbnailPath("/resources/upload/thumbnail/thumbnail_" + savedFileName);
            attachment.setCreatePerson(((MemberDTO) session.getAttribute("loginMember")).getId());
            attachments.add(attachment);
        }
    }

    @GetMapping("/update")
    public String updateProduct(@RequestParam("productId") int pid, Model model) {
        try {
            List<ProductDTO> productDTOList = productService.selectProduct(pid);

            model.addAttribute("productList", productDTOList);

            return "product/updateform";
        } catch (Exception e) {
            e.printStackTrace();

            model.addAttribute("message", "제품 정보 불러오기 실패");
            return "common/failed";
        }
    }

    @RequestMapping("common/failed")
    public String showErrorpage() {
        return "common/failed";
    }

    @PostMapping("/update")
    public String updateProduct(HttpServletRequest request,
                                @RequestParam("thumbnailImg1") MultipartFile thumbnailImg1,
                                HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            // 폼 데이터 추출
            int id = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            String companyId = request.getParameter("company");
            String content = request.getParameter("content");


            // ProductDTO 생성 및 데이터 설정
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(name);
            productDTO.setPrice(price);
            productDTO.setId(id);
            productDTO.setContent(content);

            // 회사 정보 설정
            CompanyDTO company = new CompanyDTO();
            company.setId(companyId);
            productDTO.setCompany(company);

            // 파일 저장 루트 경로 설정
            String rootLocation = session.getServletContext().getRealPath("/");
            String saveDirectory = rootLocation + "/resources/upload/original/";
            String thumbnailDirectory = rootLocation + "/resources/upload/thumbnail/";

            // 저장 경로가 존재하지 않으면 생성
            File directory = new File(saveDirectory);
            File thumbnailDir = new File(thumbnailDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!thumbnailDir.exists()) {
                thumbnailDir.mkdirs();
            }

            // 파일들을 저장할 리스트
            List<AttachmentDTO> attachments = new ArrayList<>();

            // 각 파일 처리
            processFile(thumbnailImg1, attachments, saveDirectory, thumbnailDirectory, session);


            // ProductDTO에 파일 리스트를 설정
            productDTO.setAttachments(attachments);

            // 서비스 호출하여 제품 추가
            int result = productService.updateProduct(productDTO);

            if (result > 0) {
//                    redirectAttributes.addFlashAttribute("successCode", "updateProduct");
                return "redirect:/product/list";
            } else {
                redirectAttributes.addFlashAttribute("message", "제품 수정 실패!");
                return "redirect:/product/common/failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "제품 등록 중 오류 발생!");
            return "redirect:/product/common/failed";
        }
    }

    @GetMapping("search")
    public String searchProduct(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "company", required = false) String company,
                                @RequestParam(value = "minPrice", required = false, defaultValue = "0") int minPrice,
                                @RequestParam(value = "maxPrice", required = false, defaultValue = "0") int maxPrice,
                                Model model) {

        SearchCriteria criteria = new SearchCriteria();
        criteria.setName(name);
        criteria.setCompany(company);
        criteria.setMinPrice(minPrice);
        criteria.setMaxPrice(maxPrice);

        if (minPrice > maxPrice && maxPrice != 0) {
            model.addAttribute("message", "옳바른 가격을 설정하세요");
            return "common/errorPage";
        }
        try {
            List<ProductDTO> productDTOList = productService.selectSearchList(criteria);
            model.addAttribute("list", productDTOList);
            return "product/list";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "제품 검색 실패");
            return "common/errorpage";
        }

    }

}
