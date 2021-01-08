package com.gcp.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gcp.model.BlobFileBO;
import com.gcp.model.BucketFileDTO;
import com.gcp.storage.StorageBO;
import com.google.cloud.storage.Blob;

@RestController
public class FilesController<T> {

	@Autowired
	private StorageBO storageBO;

	@Autowired
	private BlobFileBO business;

	@GetMapping("/list-files")
	public List<BucketFileDTO> listFiles() {
		return storageBO.listFiles().stream()
				.map(item -> business.setBlobToDTO(item))
				.collect(Collectors.toList());
	}

	@GetMapping("/get-file/{blobName}")
	public ResponseEntity<BucketFileDTO> getFile(@PathVariable String blobName) {
		Blob blob = storageBO.getFile(blobName);
		if (blob == null) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<BucketFileDTO>(
				business.setBlobToDTO(blob), HttpStatus.OK);
	}

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(
			@RequestParam("file") MultipartFile file) throws IOException {
		storageBO.uploadFile(file);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{blobName}")
	public void deleteFile(@PathVariable(value = "blobName") String blobName) {
		storageBO.delete(blobName);
	}
}
