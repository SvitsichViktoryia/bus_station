package by.bsuir.busstation.utils;

import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtils {

	private final SessionFactory sessionFactory;

	public HibernateUtils(SessionFactory sessionFactory) {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Minsk"));
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
}
