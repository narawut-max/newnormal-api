package com.it;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.it.service.FilesStorageService;

@SpringBootApplication
public class SpringBootUploadFilesApplication implements CommandLineRunner {

	@Resource
	FilesStorageService filesStorageService;

	private void main(String[] args) {

		SpringApplication.run(SpringBootUploadFilesApplication.class, args);

	}

	@Override
	public void run(String... arg) throws Exception {
		filesStorageService.deleteAll();
		filesStorageService.init();
	}
}
