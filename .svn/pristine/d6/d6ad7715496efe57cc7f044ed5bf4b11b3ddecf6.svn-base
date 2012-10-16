package com.ubs.gtp.security;

import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubs.gtp.data.domain.dao.AdvisorDao;
import com.ubs.gtp.data.domain.security.Login;
import com.ubs.gtp.security.objects.Advisor;
import com.ubs.gtp.security.objects.AdvisorLogin;
import com.ubs.gtp.security.objects.ClientLogin;
import com.ubs.gtp.security.objects.Session;
import com.ubs.gtp.security.util.HibernateUtil;

/**
 * 
 * The Session Manager provides method to create, remove and validate sessions.
 * 
 * @author Willy Lai, Felix Pflanzl
 * @since 0.1
 */
@Service
public class SessionManager {

	private final static long SESSION_EXPIRE_TIME = 600000; // 600000ms => 10min

	@Autowired
	private AdvisorDao advisors;

	public SessionManager() {

	}

	/**
	 * Returns Login object of the corresponding session. Returns null in case
	 * session is invalid.
	 */
	public Login getLogin(String sessionToken) {
		Login login = null;
		org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
		hibernateSession.beginTransaction();
		Session session = (Session) hibernateSession.get(Session.class, sessionToken);
		if (session == null) {
			login = null;
		} else {
			if ("Client".equals(session.getUsertype())) {
				login = (ClientLogin) hibernateSession.get(ClientLogin.class, session.getUsername());
			} else if ("Advisor".equals(session.getUsertype())) {
				login = (AdvisorLogin) hibernateSession.get(AdvisorLogin.class, session.getUsername());
				// in case nothing is found in ADVISOR_LOGIN, which will be the case since we are going via AD currently.
				if (login == null) {
					Iterator<Advisor> it = hibernateSession.createQuery("FROM Advisor WHERE samAccountName = ?").setString(0, session.getUsername()).iterate();
					if (it.hasNext()) {
						Advisor advisor = it.next();
						login = new AdvisorLogin();
						((AdvisorLogin) login).setId(advisor.getAdvisorId());
					}
				}
			}
		}
		hibernateSession.getTransaction().commit();
		return login;
	}

	/**
	 * Create a new user session.
	 * 
	 * @param login
	 * @return New session token
	 */
	public String createSession(Login login) {
		removeExpiredSessions();
		org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
		hibernateSession.beginTransaction();
		Session session = new Session(UUID.randomUUID().toString(), login.getUsername(), login.getType(), new Date());
		hibernateSession.save(session);
		String sessionToken = session.getSessionToken();
		hibernateSession.getTransaction().commit();
		return sessionToken;
	}

	/**
	 * Get an existing Session.
	 * 
	 * @param sessionToken
	 * @return Session object
	 */
	public Session getSession(String sessionToken) {
		org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
		hibernateSession.beginTransaction();
		Session session = (Session) hibernateSession.get(Session.class, sessionToken);
		hibernateSession.getTransaction().commit();
		return session;
	}

	/**
	 * Validate an existing session. That means checking if session token exists
	 * and has not been expired yet.
	 * 
	 * @param sessionToken
	 * @return Returns true if session token is valid
	 */
	public boolean validateSession(String sessionToken) {
		Session session = getSession(sessionToken);
		if (session == null) {
			return false;
		} else {
			org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
			hibernateSession.beginTransaction();
			// check if session has not expired yet
			if ((new Date().getTime() - session.getLastAccessedTime().getTime()) > SESSION_EXPIRE_TIME) {
				hibernateSession.delete(session);
				hibernateSession.getTransaction().commit();
				return false;
			}
			session.setLastAccessedTime(new Date());
			hibernateSession.update(session);
			hibernateSession.getTransaction().commit();
			return true;
		}
	}

	/**
	 * Remove an existing session (logout).
	 * 
	 * @param sessionToken
	 * @return Returns true if session token was valid (otherwise the session
	 *         has already been cleaned up)
	 */
	public boolean removeSession(String sessionToken) {
		Session session = getSession(sessionToken);
		if (session == null) {
			return false;
		} else {
			org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
			hibernateSession.beginTransaction();
			hibernateSession.delete(session);
			hibernateSession.getTransaction().commit();
			return true;
		}
	}

	/**
	 * Clean-up the session table by removing all expired sessions.
	 */
	private void removeExpiredSessions() {
		Session s = null;
		Date expiryTime = new Date();
		expiryTime.setTime((new Date()).getTime() - SESSION_EXPIRE_TIME);
		org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
		hibernateSession.beginTransaction();
		@SuppressWarnings("rawtypes")
		Iterator it = hibernateSession.createQuery("FROM Session WHERE lastAccessedTime < ?").setTimestamp(0, expiryTime).iterate();
		while (it.hasNext()) {
			s = (Session) it.next();
			hibernateSession.delete(s);
		}
		hibernateSession.getTransaction().commit();
	}
}
