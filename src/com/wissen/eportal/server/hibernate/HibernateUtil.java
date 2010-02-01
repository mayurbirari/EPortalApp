
package com.wissen.eportal.server.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
 
/**
 * @author Yogesh Bhave
 *
 */
public class HibernateUtil {
 
    private static final Logger logger = Logger
            .getLogger(HibernateUtil.class);
 
    private Session session;
 
    private Transaction transaction;
     
    private static final SessionFactory sessionFactory;
 
    static {
        try { 
         
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
     
    /**
     * Constructor 
     */
    public HibernateUtil() {
//         sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }
 
    /**
     * Commit transaction and close the session
     */
    public void commit() {
        try {
            if (transaction.isActive()) {
                transaction.commit();
            }
        } catch (Throwable e) {
            logger.fatal("Exception : ", e);
        }
        closeSession();
    }
 
    /**
     * Close Session
     */
    private void closeSession() {
        try {
            if (session.isOpen()) {
                session.close();
            }
        } catch (Throwable e) {
            logger.fatal("Exception : ", e);
        }
    }
 
    /**
     * Transction will be rollbacked and session will be closed.
     */
    public void rollback() {
        try {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } catch (Throwable e) {
            logger.fatal("Exception : ", e);
        }
        closeSession();
    }
 
    /**
     * @return the session
     */
    public Session getSession() {
        return session;
    }
 
    /**
     * @param session
     *            the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }
 
    /**
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }
 
    /**
     * @param transaction
     *            the transaction to set
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
 
}
