package com.ledger;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.ledger.entity.Customer;
import com.ledger.entity.CustomerLoan;
import com.ledger.entity.CustomerTransaction;
import com.ledger.entity.Inventory;
import com.ledger.entity.Product;

public class HibernateUtil {
	   private static StandardServiceRegistry registry;
	   private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            try {
	                Configuration configuration = new Configuration();

	                // Hibernate settings equivalent to hibernate.cfg.xml's properties
	                Properties settings = new Properties();
	                settings.put(Environment.DRIVER, "org.postgresql.Driver");
	                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
	                settings.put(Environment.USER, "postgres");
	                settings.put(Environment.PASS, "demo");
	                settings.put(Environment.DEFAULT_SCHEMA, "storemanagement");
	                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
	                

	                settings.put(Environment.SHOW_SQL, "true");

	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

	                settings.put(Environment.HBM2DDL_AUTO, "create");

	                configuration.setProperties(settings);

	                configuration.addAnnotatedClass(Customer.class);
	                configuration.addAnnotatedClass(CustomerLoan.class);
	                configuration.addAnnotatedClass(CustomerTransaction.class);
	                configuration.addAnnotatedClass(Product.class);
	                configuration.addAnnotatedClass(Inventory.class);

	                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .applySettings(configuration.getProperties()).build();

	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return sessionFactory;
	    }
	    
	/*
	 * public static SessionFactory getSessionFactory() { if (sessionFactory ==
	 * null) { try {
	 * 
	 * // Create registry builder StandardServiceRegistryBuilder registryBuilder =
	 * new StandardServiceRegistryBuilder();
	 * 
	 * // Hibernate settings equivalent to hibernate.cfg.xml's properties
	 * Map<String, String> settings = new HashMap<String, String>();
	 * settings.put(Environment.DRIVER, "org.postgresql.Driver");
	 * settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
	 * settings.put(Environment.USER, "postgres"); settings.put(Environment.PASS,
	 * "demo"); settings.put(Environment.DIALECT,
	 * "org.hibernate.dialect.PostgreSQL9Dialect");
	 * 
	 * // Apply settings registryBuilder.applySettings(settings);
	 * 
	 * // Create registry registry = registryBuilder.build();
	 * 
	 * // Create MetadataSources MetadataSources sources = new
	 * MetadataSources(registry);
	 * 
	 * // Create Metadata Metadata metadata = sources.getMetadataBuilder().build();
	 * 
	 * // Create SessionFactory sessionFactory =
	 * metadata.getSessionFactoryBuilder().build();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); if (registry != null) {
	 * StandardServiceRegistryBuilder.destroy(registry); } } } return
	 * sessionFactory; }
	 */

	   public static void shutdown() {
	      if (registry != null) {
	         StandardServiceRegistryBuilder.destroy(registry);
	      }
	   }
	}