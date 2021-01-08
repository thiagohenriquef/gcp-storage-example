package com.gcp.model;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage.SignUrlOption;

@Service
public class BlobFileBO {

	public BucketFileDTO setBlobToDTO(Blob blob) {
		return new BucketFileDTO(blob.getName(), blob.getBlobId(), blob.signUrl(
				60, TimeUnit.MINUTES, SignUrlOption.withV4Signature()));
	}
}
