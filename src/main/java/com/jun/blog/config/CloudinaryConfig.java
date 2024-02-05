package com.jun.blog.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {
    
    @Value("${cloud_name}")
    private String CLOUD_NAME;

    @Value("${api_key}")
    private String API_KEY;

    @Value("${api_secret}")
    private String API_SECRET;

    @Bean
    protected Cloudinary cloudinary() {
       
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        config.put("secure", "true");
        return new Cloudinary(config);
    }
    
}
