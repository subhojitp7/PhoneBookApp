package com.phonebook.service;

import com.phonebook.entity.Contact;

public interface ContactService {

	public String insertContact(Contact contact, String memory);

	public String updateContact(Contact contact, String memory);

	public Contact searchContact(String name, String memory);

	public Contact searchContact(long mobileNumber, String memory);

}
