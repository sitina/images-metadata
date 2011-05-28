package net.sitina.metadata.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ImageMetadata")
public class ImageMetadata implements Serializable {

	@Override
	public String toString() {
		return "ImageMetadata [id=" + id + ", name=" + name + ", path=" + path
				+ ", taken=" + taken + ", width=" + width + ", height="
				+ height + ", aperture=" + aperture + ", focalLength="
				+ focalLength + ", size=" + size + "]";
	}

	private static final long serialVersionUID = -7836522475317133622L;

	private Long id;
	
	private String name;
	
	private String path;
	
	private Date taken;
	
	private Long width;
	
	private Long height;
	
	private Float aperture;
	
	private Float focalLength;
	
	private Long size;

	public String getName() {
		return name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getTaken() {
		return taken;
	}

	public void setTaken(Date taken) {
		this.taken = taken;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public Float getAperture() {
		return aperture;
	}

	public void setAperture(Float aperture) {
		this.aperture = aperture;
	}

	public Float getFocalLength() {
		return focalLength;
	}

	public void setFocalLength(Float focalLength) {
		this.focalLength = focalLength;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
}
