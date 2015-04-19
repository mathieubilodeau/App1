package com.challenge.app1.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.challenge.app1.model.Account;
import com.challenge.app1.model.Event;

public class DataSingleton {

	static DataSingleton singleton = new DataSingleton();
	
	private ArrayList<Event> log_ = new ArrayList<Event>();
	private HashMap<String, Account> accounts_ = new HashMap<String, Account>();
	
	private DataSingleton() {

	}

	static public DataSingleton getInstance() {
		return singleton;
	}

	public ArrayList<Event> getLog() {
		return log_;
	}

	public HashMap<String, Account> getAccounts()
	{
		return accounts_;
	}
}
