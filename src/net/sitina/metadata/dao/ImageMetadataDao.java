package net.sitina.metadata.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.sitina.metadata.pojo.ImageMetadata;
import net.sitina.metadata.util.HibernateUtil;

public class ImageMetadataDao {

	private static SessionFactory sessionFactory = null;
	
	private static Logger log = Logger.getLogger(ImageMetadataDao.class);
	
	public ImageMetadataDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void save(ImageMetadata imageMetadata) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
		
			if (imageMetadata.getId() != null) {
				session.merge(imageMetadata);
			} else {
				session.save(imageMetadata);
			}
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e);
		}
	}
	
	public ImageMetadata load(Long id) {
		if (id != null) {
			Session session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			// return (ImageMetadata)session.load(ImageMetadata.class, id); // attached instance
			ImageMetadata result = (ImageMetadata)session.get(ImageMetadata.class, id);
			transaction.commit();
			return result;
		} else {
			return null;
		}
	}
	
}