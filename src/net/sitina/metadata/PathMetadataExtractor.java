package net.sitina.metadata;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import net.sitina.metadata.api.MetadataException;
import net.sitina.metadata.core.MetadataExtractor;
import net.sitina.metadata.dao.ImageDirectoryDao;
import net.sitina.metadata.dao.ImageMetadataDao;
import net.sitina.metadata.pojo.ImageDirectory;
import net.sitina.metadata.pojo.ImageMetadata;

public class PathMetadataExtractor {

	private ImageMetadataDao matadataDao = null;
	
	private ImageDirectoryDao directoryDao = null;
	
	private MetadataExtractor extractor = null;
	
	private static Logger log = Logger.getLogger(PathMetadataExtractor.class);
	
	public PathMetadataExtractor() {
		matadataDao = new ImageMetadataDao();
		directoryDao = new ImageDirectoryDao();
		extractor = new MetadataExtractor();
	}
	
	public void extractAndStoreMetadata(File path) {
		processDirectory(path);
		
		File[] files = path.listFiles();
		
		for (File file : files) {
			if (file.isDirectory()) {
				processDirectory(file);
			}
		}
	}
	
	private void processDirectory(File path) {
		log.debug("Going to process directory " + path.getAbsolutePath());
		
		ImageDirectory directory = new ImageDirectory();
		directory.setFilesCount(Long.valueOf(path.list().length));
		directory.setFullPath(path.getAbsolutePath());
		directory.setName(path.getName());
		
		directoryDao.save(directory);
		
		try {
			List<ImageMetadata> metadataList = extractor.getMetadata(path, false);
			for (ImageMetadata metadata : metadataList) {
				try {
					matadataDao.save(metadata);
				} catch (Exception e) {
					log.error(e);
				}
			}
		} catch (MetadataException e) {
			log.error(e);
		}
		
		directory.setProcessed(true);
		directoryDao.save(directory);
	}
	
}
