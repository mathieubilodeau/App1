package com.challenge.app1.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import com.challenge.app1.model.Event;

@Repository
public class LogDao {

//	@PersistenceContext
//	private EntityManager entityManager;
	
	
//	@SuppressWarnings("unchecked")
	public Collection<Event> getEvents() {
//		return entityManager.createQuery("select a from Account a").getResultList();
		return getLog();
	}
	
//	@Transactional
	public Event save(Event event) {
//		if (account.getId() == null) {
//			entityManager.persist(account);
//			return account;
//		} else {
//			return entityManager.merge(account);
//		}	
		getLog().add(event);
		return event;
	}	
	
	private List<Event> getLog()
	{
		return DataSingleton.getInstance().getLog();
	}
}
