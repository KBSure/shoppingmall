package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Image;
import com.project.shoppingmall.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/image")
public class ImageController {
    
    @Value("${image.path}")
    private String path;
    
    @Autowired
    private ImageService imageService;
    
    @GetMapping("/{id}")
    @ResponseBody
    public void loadImage(@PathVariable("id") Long id, HttpServletResponse response) {
        Image image = imageService.getImage(id);
    
        String fileName = image.getName();
        String imageName = fileName.substring(fileName.lastIndexOf("/")+1);
        
        setImageDownloadHeaders(response, imageName, image.getMimeType(), image.getSize());
        
        try {
            if(path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }
            File file = new File(path + fileName);
            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        }
        catch (IOException e) {
            log.error("ImageDownload Error!!", e);
            throw new RuntimeException("ImageDownload Error");
        }
    }
    
    private void setImageDownloadHeaders(HttpServletResponse response, String fileName, String mimeType, int fileSize) {
        response.setHeader("Content-Disposition", "inline; filename="+fileName);
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", mimeType);
        response.setHeader("Content-Length", String.valueOf(fileSize));
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    }
    
}
