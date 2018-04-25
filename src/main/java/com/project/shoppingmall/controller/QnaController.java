package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("qna")
public class QnaController {

    @GetMapping
    public String qnaList() {
        
        return "qna/qna_list";
    }
    
    // qna 상세페이지
    @GetMapping("/{id}")
    public String qnaView(@PathVariable Long id) {
    
        return "qna/qna_view";
    }
    
    // qna 수정페이지
    @GetMapping("/update/{id}")
    public String qnaUpdateForm(@PathVariable Long id) {
        
        return "qna/qna_update";
    }
    
    // qna 수정
    @PutMapping("/{id}")
    public String updateQna(@PathVariable Long id) {
        
        return "redirect:/qna/"+id;
    }
    
    // qna 삭제
    @DeleteMapping("/{id}")
    public String deleteQna(@PathVariable Long id) {
    
        
        return "redirect:/qna";
    }
    
    // qna 작성 페이지
    @GetMapping("/write")
    public String qnaWriteForm() {
    
        return "qna/qna_write";
    }
    
    @PostMapping
    public String wirteQna() {
        
        //TODO 등록한 글로 이동 해야댐.
        return "redirect:/qna";
    }
    
}
