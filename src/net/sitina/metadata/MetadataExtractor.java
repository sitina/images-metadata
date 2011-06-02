package net.sitina.metadata;

import java.io.File;

import org.apache.log4j.Logger;

public class MetadataExtractor {

	private static final String DEFAULT_PATH = "C:/Users/jirka/Pictures";
	
	private static Logger log = Logger.getLogger(MetadataExtractor.class);
	
	public static void main(String[] args) {
		PathMetadataExtractor extractor = new PathMetadataExtractor();
		String pathStr = DEFAULT_PATH;
		
		try {			
			
			if (args.length > 0) {
				pathStr = args[0];
				log.debug("Going to extract image metadata from path " + pathStr);
			}
			
			File path = new File(pathStr);
			extractor.extractAndStoreMetadata(path);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			log.error(e);
		}
	}
	
}
