package net.sitina.metadata.test;


import java.io.File;
import java.util.List;

import junit.framework.TestCase;
import net.sitina.metadata.core.MetadataExtractor;
import net.sitina.metadata.pojo.ImageMetadata;
import net.sitina.metadata.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

public class PathTest extends TestCase {

	private SessionFactory sf = null;
	
	private static final String PATH = "d:/Fotky/2010-11-12-RimPatek";
	
	@Before
	public void setUp() throws Exception {
		sf = HibernateUtil.getSessionFactory();
	}
	
	@Test
	public void testPathLoad() {
		try {
			Session session = sf.getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			MetadataExtractor me = new MetadataExtractor();
			List<ImageMetadata> mList = me.getMetadata(new File(PATH));

			for (ImageMetadata m : mList) {
				session.save(m);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
