package net.sitina.metadata.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ImageDirectory")
public class ImageDirectory implements Serializable {

	private static final long serialVersionUID = -174304889056794332L;

	private Long id;
	
	private String name;
	
	private String fullPath;
	
	private Long filesCount;
	
	private boolean processed = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public Long getFilesCount() {
		return filesCount;
	}

	public void setFilesCount(Long filesCount) {
		this.filesCount = filesCount;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
}
