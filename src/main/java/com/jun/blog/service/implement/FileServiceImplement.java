package com.jun.blog.service.implement;

import java.util.HashMap;
import java.util.Map;

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

    private final Cloudinary cloudinary;

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("null")
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
