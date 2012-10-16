package com.ubs.gtp.security.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ubs.gtp.data.domain.security.UsernamePasswordHashLogin;

/**
 * 
 * Convenience class to get an instance of the Hibernate Session Factory.
 * 
 * @author Willy Lai
 * @since 0.1
 */

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
