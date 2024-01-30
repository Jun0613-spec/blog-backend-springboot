package com.jun.blog.service.implement;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jun.blog.service.FileService;
import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImplement implements FileService{
    
    @Value("${file.url}")
    private String fileUrl;

    private final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile file) {

       try {
          return cloudinary.uploader()
          .upload(file.getBytes(),
                  Map.of("public_id", UUID.randomUUID().toString()))
          .get("url")
          .toString();

       } catch (Exception e) {
         throw new RuntimeException("Image uploading fail.");
       }

    }

    @Override
    public Resource getImage(String fileName) {
       
        Resource resource = null;

        try {
            resource = new  UrlResource(fileUrl + fileName);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        
        return resource;
    }
    
}
