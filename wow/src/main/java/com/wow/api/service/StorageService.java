package com.wow.api.service;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	/*
	 * public void storeFolder(Path path);
	 * 
	 * public boolean delete (String fileName, String type); public Path load(String
	 * fileName, String type);
	 */
	
	public String store (MultipartFile file, String type);
	public Path getRoot(String type);
	public void storeFolder(Path path);
}