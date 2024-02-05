package com.jun.blog.service.implement;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jun.blog.service.FileService;

import io.jsonwebtoken.io.IOException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImplement implements FileService{
    
    @Value("${file_url}")
    private String fileUrl;

    private final Cloudinary cloudinary;

    // @Override
    // public String upload(MultipartFile file) {

    //    try {
    //       return cloudinary.uploader()
    //       .upload(file.getBytes(),
    //               Map.of("public_id", UUID.randomUUID().toString()))
    //       .get("url")
    //       .toString();

    //    } catch (Exception e) {
    //      throw new RuntimeException("Image uploading fail.");
    //    }

    // }

     // @Override
    // public Resource getImage(String fileName) {
       
    //     Resource resource = null;

    //     try {
    //         resource = new UrlResource(fileUrl + fileName);
            
    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //         return null;
    //     }
        
    //     return resource;
    // }

//     @Override
// public String upload(MultipartFile file) {
//     try {
//         String publicId = UUID.randomUUID().toString();
//         return cloudinary.url().secure(true).generate(publicId);

//     } catch (IOException e) {
//         e.printStackTrace();
//         throw new RuntimeException("Image uploading failed.", e);
//     }
// }

    @Override
    public String upload(MultipartFile file) {
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("secure", true);

            byte[] fileBytes = file.getBytes();
            Map<String, Object> uploadResult = cloudinary.uploader().upload(fileBytes, options);
            return (String) uploadResult.get("secure_url");

        } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Image uploading failed.", e);
        }
    }

    @Override
    public Resource getImage(String fileName) {
        try {
        String imageUrl = cloudinary.url().secure(true).generate(fileName);
        return new UrlResource(imageUrl);
        } catch (Exception exception) {
        exception.printStackTrace();
        return null;
        }
    }

}
