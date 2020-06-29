package com.phonebook.controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.phonebook.entity.Contact;
import com.phonebook.service.ContactServiceImpl;

public class MainApp {
	
	static ContactServiceImpl contactService;

	public static boolean isEmailValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public static boolean isPhoneValid(String phone) {
		Pattern pat = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		Matcher m = pat.matcher(phone);
		return (m.find() && m.group().equals(phone));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int option, i;
		String name = null, num, email, storage, to;
		long number = 0;
		Contact contact = new Contact("Subh", 8100517912l, "Subh@gm.com");
		System.out.println(contactService.insertContact(contact, "Phone"));
		/*while (true) {
			System.out.println("1.Insert  2.Update  3.Search  4.Display  5.Copy  6.Copy All  0.Exit");
			option = sc.nextInt();
			switch (option) {
			case 0:
				break;
			case 1:
				System.out.print("Name:  ");
				name = sc.next();
				System.out.print("Phone: ");
				num = sc.next();
				while (!isPhoneValid(num)) {
					System.out.println("Invalid Number! Try Again");
					num = sc.next();
				}
				number = Long.parseLong(num);
				System.out.print("Email: ");
				email = sc.next();
				while (!isEmailValid(email)) {
					System.out.println("Invalid Email! Try Again");
					num = sc.next();
				}
				contact = new Contact(name, number, email);
				System.out.println("Save to: 1.Phone Memory  2.SIM Memory");
				System.out.print("Your Choise: ");
				i = sc.nextInt();
				while (i != 1 || i != 2) {
					System.out.print("Invalid option! Try again: ");
					i = sc.nextInt();
				}
				if (i == 1)
					storage = "Phone";
				else
					storage = "SIM";
				contact = new Contact(name, number, email);
				System.out.println(contactService.insertContact(contact, storage));
			case 2:
				System.out.print("Name:  ");
				name = sc.nextLine();
				System.out.print("Phone: ");
				num = sc.next();
				while (!isPhoneValid(num)) {
					System.out.println("Invalid Number! Try Again");
					num = sc.next();
				}
				number = Long.parseLong(num);
				System.out.print("Email: ");
				email = sc.nextLine();
				while (!isEmailValid(email)) {
					System.out.println("Invalid Email! Try Again");
					num = sc.next();
				}
				contact = new Contact(name, number, email);
				System.out.println("From: 1.Phone Memory  2.SIM Memory");
				System.out.print("Your Choise: ");
				i = sc.nextInt();
				while (i != 1 || i != 2) {
					System.out.print("Invalid option! Try again: ");
					i = sc.nextInt();
				}
				if (i == 1)
					storage = "Phone";
				else
					storage = "SIM";
				System.out.println(contactService.updateContact(contact, storage));
			case 3:
				System.out.println("By: 1.Name  2.Phone Number");
				System.out.print("Your Choise: ");
				int l = sc.nextInt();
				while (l != 1 || l != 2) {
					System.out.print("Invalid option! Try again: ");
					l = sc.nextInt();
				}
				if (l == 1) {
					System.out.print("Name:  ");
					name = sc.nextLine();
				} else {
					System.out.println("Phone Number:  ");
					String phoneNumber = sc.next();
					while (!isPhoneValid(phoneNumber)) {
						System.out.println("Invalid Number! Try Again");
						phoneNumber = sc.next();
					}
					number = Long.parseLong(phoneNumber);
				}
				System.out.println("From: 1.Phone Memory  2.SIM Memory");
				System.out.print("Your Choise: ");
				i = sc.nextInt();
				while (i != 1 || i != 2) {
					System.out.print("Invalid option! Try again: ");
					i = sc.nextInt();
				}
				if (i == 1)
					storage = "Phone";
				else
					storage = "SIM";
				if (l == 1)
					contact = contactService.searchContact(name, storage);
				else
					contact = contactService.searchContact(number, storage);
				if (contact != null)
					System.out.println(contact.toString());
				else
					System.out.println("Contact not found");
			case 4:
				contactService.displayAll();
			case 5:
				System.out.println("By: 1.Name  2.Phone Number");
				System.out.print("Your Choise: ");
				l = sc.nextInt();
				while (l != 1 || l != 2) {
					System.out.print("Invalid option! Try again: ");
					l = sc.nextInt();
				}
				if (l == 1) {
					System.out.print("Name:  ");
					name = sc.nextLine();
				} else {
					System.out.println("Phone Number:  ");
					String phoneNumber = sc.next();
					while (!isPhoneValid(phoneNumber)) {
						System.out.println("Invalid Number! Try Again");
						phoneNumber = sc.next();
					}
					number = Long.parseLong(phoneNumber);
				}
				System.out.println("From: 1.Phone Memory  2.SIM Memory");
				System.out.print("Your Choise: ");
				i = sc.nextInt();
				while (i != 1 || i != 2) {
					System.out.print("Invalid option! Try again: ");
					i = sc.nextInt();
				}
				if (i == 1) {
					storage = "Phone";
					to = "SIM";
				} else {
					storage = "SIM";
					to = "Phone";
				}
				if (l == 1)
					contact = contactService.searchContact(name, storage);
				else
					contact = contactService.searchContact(number, storage);
				if (contact != null)
					System.out.println(contactService.copy(contact, to));
				else
					System.out.println("Contact not found");
			case 6:
				System.out.println("From: 1.Phone Memory  2.SIM Memory");
				System.out.print("Your Choise: ");
				i = sc.nextInt();
				while (i != 1 || i != 2) {
					System.out.print("Invalid option! Try again: ");
					i = sc.nextInt();
				}
				if (i == 1)
					storage = "Phone";
				else
					storage = "SIM";
				System.out.println(contactService.copyAll(storage));
			default:
				System.out.println("Try Again");
			}
		}*/
	}

}
