package net.sitina.metadata.test;

import java.io.File;

import net.sitina.metadata.PathMetadataExtractor;
import junit.framework.TestCase;

public class PathMetadataExtractorTest extends TestCase {

	private static final File PATH1 = new File("D:/Fotky");
	
	private static final File PATH2 = new File("C:/Users/jirka/Pictures");
	
	public void testProcessPath() {
		PathMetadataExtractor e = new PathMetadataExtractor();
		e.extractAndStoreMetadata(PATH1);
		e.extractAndStoreMetadata(PATH2);
	}
	
}