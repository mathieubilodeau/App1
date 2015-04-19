package com.challenge.app1.dao;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.challenge.app1.model.Account;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

@Repository
public class AccountDao {

//	@PersistenceContext
//	private EntityManager entityManager;
	
	public Account find(String id) {
//		return entityManager.find(Account.class, id);
		return getAccounts().get(id);
	}
	
//	@SuppressWarnings("unchecked")
	public Collection<Account> getAccountCollection() {
//		return entityManager.createQuery("select a from Account a").getResultList();
		return getAccounts().values();
	}
	
//	@Transactional
	public Account save(Account account) {
//		if (account.getId() == null) {
//			entityManager.persist(account);
//			return account;
//		} else {
//			return entityManager.merge(account);
//		}	
		getAccounts().put(account.getAccountIdentifier(), account);
		return account;
	}	
	
	public void delete(String id)
	{
		getAccounts().remove(id);
	}
	
	private HashMap<String, Account> getAccounts()
	{
		return DataSingleton.getInstance().getAccounts();
	}
}
