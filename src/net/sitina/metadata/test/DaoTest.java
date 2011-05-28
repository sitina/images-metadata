package net.sitina.metadata.test;


import java.io.File;

import junit.framework.TestCase;

import net.sitina.metadata.api.MetadataException;
import net.sitina.metadata.core.MetadataExtractor;
import net.sitina.metadata.dao.ImageMetadataDao;
import net.sitina.metadata.pojo.ImageMetadata;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class DaoTest extends TestCase {

	private ImageMetadataDao dao = null;
	
	private static final String IMAGE_NAME = "myImage.jpg";
	
	private static Logger log = Logger.getLogger(DaoTest.class);
	
	@Before
	public void setUp() throws Exception {
		dao = new ImageMetadataDao();
	}
	
	@Test
	public void testSave() {
		try {
			MetadataExtractor me = new MetadataExtractor();
			ImageMetadata m = me.getMetadata(new File(IMAGE_NAME)).get(0);
			dao.save(m);
		} catch (MetadataException e) {
			fail();
		}
	}
	
	@Test
	public void testLoad() {
		ImageMetadata metadata = dao.load(Long.valueOf(1));
		assertNotNull(metadata);
		log.debug(metadata.toString());
		
		metadata.setSize(Long.valueOf(1));
		dao.save(metadata);
	}

}
