package net.sitina.metadata.test;


import java.io.File;

import junit.framework.TestCase;

import net.sitina.metadata.core.MetadataExtractor;
import net.sitina.metadata.pojo.ImageMetadata;
import net.sitina.metadata.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest extends TestCase {

	private SessionFactory sf = null;
	
	private static final String IMAGE_NAME = "myImage.jpg";
	
	@Before
	public void setUp() throws Exception {
		sf = HibernateUtil.getSessionFactory();
	}
	
	@Test
	public void testSave() {
		try {
			Session session = sf.getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			MetadataExtractor me = new MetadataExtractor();
			ImageMetadata m = me.getMetadata(new File(IMAGE_NAME)).get(0);

			session.save(m);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testLoad() {
		try {
			Session session = sf.getCurrentSession();
			ImageMetadata a = (ImageMetadata)session.get(ImageMetadata.class, Long.valueOf(1));
			assertNotNull(a);
		} catch (Exception e) {
			fail();
		}
	}

}
