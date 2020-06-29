package com.phonebook.service;

import com.phonebook.entity.Contact;

public class ContactServiceImpl implements ContactService {

	private Contact[] phone = new Contact[10];
	private Contact[] sim = new Contact[10];
	private int phoneCounter = 0;
	private int simCounter = 0;

	@Override
	public String insertContact(Contact contact, String memory) {
		System.out.println("hello");
		if (memory.equals("Phone") && phoneCounter < 10) {
			phone[phoneCounter++] = contact;
			sortContact(phone);
			return "Contact Saved";
		} else if (memory.equals("SIM") && simCounter < 10) {
			sim[simCounter++] = contact;
			sortContact(sim);
			return "Contact Saved";
		}
		return memory + " full";
	}

	@Override
	public String updateContact(Contact contact, String memory) {
		if (memory.equals("Phone")) {
			for (int i = 0; i < phoneCounter; i++) {
				if (phone[i].getName() == contact.getName()) {
					phone[i] = contact;
					return "Contact saved";
				}
			}
		} else if (memory.equals("SIM")) {
			for (int i = 0; i < simCounter; i++) {
				if (sim[i].getName() == contact.getName()) {
					sim[i] = contact;
					return "Contact saved";
				}
			}
		}
		return "Contact not found";
	}

	@Override
	public Contact searchContact(String name, String memory) {
		if (memory.equals("Phone")) {
			for (int i = 0; i < phoneCounter; i++) {
				if (phone[i].getName().equals(name))
					return phone[i];
			}
		} else if (memory.equals("SIM")) {
			for (int i = 0; i < simCounter; i++) {
				if (sim[i].getName().equals(name))
					return sim[i];
			}
		}
		return null;
	}

	@Override
	public Contact searchContact(long mobileNumber, String memory) {
		if (memory.equals("Phone")) {
			for (int i = 0; i < phoneCounter; i++) {
				if (phone[i].getPhoneNumber() == mobileNumber)
					return phone[i];
			}
		} else if (memory.equals("SIM")) {
			for (int i = 0; i < simCounter; i++) {
				if (sim[i].getPhoneNumber() == mobileNumber)
					return sim[i];
			}
		}
		return null;
	}

	public void sortContact(Contact[] storage) {
		int size = storage.length;
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				if (storage[i].getName().compareTo(storage[j].getName()) > 0) {
					Contact temp = storage[i];
					storage[i] = storage[j];
					storage[j] = temp;
				}
			}
		}
	}

	public void displayAll() {
		int i = 0, j = 0;
		while (i < phoneCounter && j < simCounter) {
			if (phone[i].getName().compareTo(sim[j].getName()) > 0)
				System.out.println(phone[i++].toString());
			else
				System.out.println(sim[j++].toString());
		}
		if (i < phoneCounter)
			for (int k = i; k < phoneCounter; k++)
				System.out.println(phone[k].toString());
		else if (j < simCounter)
			for (int k = j; k < simCounter; k++)
				System.out.println(sim[k].toString());
	}

	public String copy(Contact contact, String memory) {
		if (memory.equals("Phone")) {
			if (simCounter >= 10)
				sim[0] = contact;
			else
				sim[simCounter++] = contact;
		} else {
			if (phoneCounter >= 10)
				phone[0] = contact;
			else
				phone[phoneCounter++] = contact;
		}
		return "Contact Copied";
	}

	public String copyAll(String memory) {
		int i = 0, j = 0;
		if (memory.equals("Phone")) {
			while (simCounter < 10)
				sim[simCounter++] = phone[i++];
			while (i < phoneCounter)
				sim[j++] = phone[i++];
			sortContact(sim);
		} else {
			while (phoneCounter < 10)
				phone[phoneCounter++] = sim[i++];
			while (i < simCounter)
				phone[j++] = sim[i++];
			sortContact(phone);
		}
		return "Contacts Copied";
	}

}
