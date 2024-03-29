package com.jun.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

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

        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", CLOUD_NAME,
                      "api_key", API_KEY,
                      "api_secret", API_SECRET,
                      "secure", true
        ));
    }
    
}
