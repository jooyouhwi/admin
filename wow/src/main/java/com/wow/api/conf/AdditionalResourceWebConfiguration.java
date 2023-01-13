package com.wow.api.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

	  @Override
	  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    
	    registry.addResourceHandler("/uploads/ckeditor/**").addResourceLocations("file:uploads/ckeditor/");
	    //마이오피스 게시판 관련
	    registry.addResourceHandler("/uploads/myoffice/temp/**").addResourceLocations("file:uploads/myoffice/temp/"); // 에디터 이미지 임시저장 폴더
	   // registry.addResourceHandler("/uploads/myoffice/B01/**").addResourceLocations("file:uploads/myoffice/B01/"); // 팝업
	    registry.addResourceHandler("/uploads/myoffice/contents/**").addResourceLocations("file:uploads/myoffice/contents/"); // 에디터 이미지 임시저장 폴더
	  }
}