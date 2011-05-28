package net.sitina.metadata.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	private static Logger log = Logger.getLogger(HibernateUtil.class);
	
	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();			
		} catch (Throwable e) {
			log.error(e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}