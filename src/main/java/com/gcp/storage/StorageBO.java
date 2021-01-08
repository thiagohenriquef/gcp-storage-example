package com.gcp.storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class StorageBO {
	@Value("${bucketname}")
	String bucketName;

	private static final String SERVICE_ACCOUNT_JSON_PATH = "/opt/gcp/credentials.json";

	private static Storage storage = null;
	static {
		try {
			storage = StorageOptions.newBuilder()
					.setCredentials(ServiceAccountCredentials.fromStream(
							new FileInputStream(SERVICE_ACCOUNT_JSON_PATH)))
					.build().getService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Blob> listFiles() {
		Bucket bucket = storage.get(bucketName);
		List<Blob> blobs = new ArrayList<Blob>();

		for (Blob blob : bucket.list().iterateAll()) {
			blobs.add(blob);
		}

		return blobs;
	}

	public Blob getFile(String blobName) {
		Blob blob = storage.get(bucketName, blobName);
		if (Objects.nonNull(blob) && blob.exists()) {
			return blob;
		}
		return null;
	}

	public void uploadFile(MultipartFile file) throws IOException {
		BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		storage.create(blobInfo, file.getBytes());
	}

	public void delete(String blobId) {
		storage.delete(bucketName, blobId);
	}
}
