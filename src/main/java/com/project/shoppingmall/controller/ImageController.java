package com.project.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/image")
public class ImageController {
    
    @Value("${image.path}")
    private String path;
    
    @GetMapping("/{id}")
    public void loadImage(@PathVariable("id") Long id, HttpServletResponse response) {
        // DB에서 id 로 저장된 이미지 정보 조회
        // 저장된 이미지가 없으면 Exception!!!
        // 응답 헤더에 다운로드 정보 담기
        // 이미지 path가 /로 끝나면, / 제거.
        // 이미지 읽어들여서 응답하기.
    }
    
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("d:/image/detail");
        Files.list(path).map(p -> p.toFile()).forEach(f -> {
            System.out.println(f.getName()+" : "+f.length());
        });
        
    }
}
