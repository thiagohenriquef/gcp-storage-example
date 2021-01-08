package com.gcp.model;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage.SignUrlOption;

public class BucketFileDTO {
	private String name;
	private BlobId blobId;
	private String mediaLink;
	private URL signedUrl;

	public BucketFileDTO(String name, BlobId blobId, String mediaLink,
			URL signedUrl) {
		this.name = name;
		this.blobId = blobId;
		this.mediaLink = mediaLink;
		this.signedUrl = signedUrl;
	}

	public BucketFileDTO(String name, BlobId blobId, URL signedUrl) {
		this.name = name;
		this.blobId = blobId;
		this.signedUrl = signedUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BlobId getBlobId() {
		return blobId;
	}

	public void setBlobId(BlobId blobId) {
		this.blobId = blobId;
	}

	public String getMediaLink() {
		return mediaLink;
	}

	public void setMediaLink(String mediaLink) {
		this.mediaLink = mediaLink;
	}

	public URL getSignedUrl() {
		return signedUrl;
	}

	public void setSignedUrl(URL signedUrl) {
		this.signedUrl = signedUrl;
	}

}
