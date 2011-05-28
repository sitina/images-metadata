package net.sitina.metadata.test;


import java.io.File;

import junit.framework.TestCase;
import net.sitina.metadata.core.MetadataExtractor;
import net.sitina.metadata.pojo.ImageMetadata;

import org.junit.Test;

public class SimpleMetadataTest extends TestCase {

	@Test
	public void testBasicMetadata() {
		try {
			MetadataExtractor me = new MetadataExtractor();
			ImageMetadata m = me.getMetadata(new File("myImage.jpg")).get(0);
			assertNotNull(m);
		} catch (Exception e) {
			fail();
		}
	}
	
}
