package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @GetMapping
    public String showNoticeList(@RequestParam(defaultValue = "1") int page,@RequestParam("sch_type") String searchType, @RequestParam("sch_str") String searchString)
    {
        return "notice/notice_list";
    }

    @GetMapping("/{id}")
    public String detailNoticeForm(@PathVariable long id, @RequestParam(defaultValue = "1") int page ,@RequestParam("sch_type") String searchType, @RequestParam("sch_str")String searchString)
    {
        return "notice/notice_view";
    }

    @DeleteMapping("/{id}")
    public String deleteNotice(@PathVariable long id, @RequestParam(defaultValue = "1") int page, @RequestParam("sch_type") String searchType, @RequestParam("sch_str")String searchString)
    {
        return"redirect:/notice?page="+page+"&sch_type="+searchType + "&sch_str="+searchString;

    }

    @GetMapping("/{id}")
    public String updateNoticeForm()
    {

        return "notice/notice_update";
    }

    @PutMapping("/{id}")
    public String updateNotice()
    {
        return "redirect:/notice?";
    }

    @GetMapping("/write")
    public String writeNoticeForm()
    {
        return "notice/notice_write";
    }

    @PostMapping("/write")
    public String writeNotice()
    {
        return "redirect:/notice";
    }
}
