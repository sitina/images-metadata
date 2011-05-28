package net.sitina.metadata.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sitina.metadata.api.MetadataException;
import net.sitina.metadata.pojo.ImageMetadata;

import org.apache.log4j.Logger;

import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.Rational;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.jpeg.JpegDirectory;

public class MetadataExtractor {
	
	private static Logger log = Logger.getLogger(MetadataExtractor.class);

	private ImageMetadata getFileMetadata(File file) throws MetadataException {
		log.debug("Processing file " + file.getAbsolutePath());
		
		ImageMetadata result = new ImageMetadata();
		
		try {		
			result.setPath(file.getAbsolutePath());
			result.setName(file.getName());
			result.setSize(file.length());
			
			Metadata metadata = ImageMetadataReader.readMetadata(file);
			
			Directory jpegDirectory = metadata.getDirectory(JpegDirectory.class);
			if (jpegDirectory != null) {
				result.setWidth(jpegDirectory.getLong(JpegDirectory.TAG_JPEG_IMAGE_WIDTH));
				result.setHeight(jpegDirectory.getLong(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT));
			}
			
			Directory exifSubIdDirectory = metadata.getDirectory(ExifSubIFDDirectory.class);
			if (exifSubIdDirectory != null) {
				result.setAperture(processRational(exifSubIdDirectory.getRational(ExifSubIFDDirectory.TAG_APERTURE)));
				result.setFocalLength(processRational(exifSubIdDirectory.getRational(ExifSubIFDDirectory.TAG_FOCAL_LENGTH)));
			}
			
			Directory exifIFD0Directory = metadata.getDirectory(ExifIFD0Directory.class);
			if (exifIFD0Directory != null) {
				result.setTaken(exifIFD0Directory.getDate(ExifIFD0Directory.TAG_DATETIME));
			}
		} catch (Exception e) {
			throw new MetadataException(e);
		}
		
		return result;
	}
	
	public List<ImageMetadata> getMetadata(File path) throws MetadataException {
		return getMetadata(path, true);
	}
	
	public List<ImageMetadata> getMetadata(File path, boolean recursive) throws MetadataException {
		ArrayList<ImageMetadata> result = new ArrayList<ImageMetadata>();
		
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			
			for (File file : files) {
				if (recursive && file.isDirectory()) {
					result.addAll(getMetadata(file, true));
					continue;
				}
				
				String fileName = file.getName();
				
				if (fileName.toLowerCase().endsWith("jpg") || fileName.toLowerCase().endsWith("jpeg")) {
					result.add(getFileMetadata(file));
				}
			}
			
		} else if (path.isFile()) {
			result.add(getFileMetadata(path));
		}
		
		return result;
	}
	
	private Float processRational(Rational value) {
		if (value != null) {
			float numerator = value.getNumerator();
			float denominator = value.getDenominator();
	
			return Float.valueOf(numerator / denominator);
		} else {
			return null;
		}
	}
	
}
