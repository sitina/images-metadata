package net.sitina.metadata.dao;

import net.sitina.metadata.pojo.ImageDirectory;
import net.sitina.metadata.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ImageDirectoryDao {
	
	private static SessionFactory sessionFactory = null;
	
	private static Logger log = Logger.getLogger(ImageDirectoryDao.class);
	
	public ImageDirectoryDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void save(ImageDirectory imageDirectory) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		try {
		
			if (imageDirectory.getId() != null) {
				session.merge(imageDirectory);
			} else {
				session.save(imageDirectory);
			}
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			log.error(e);
		}
	}

}
