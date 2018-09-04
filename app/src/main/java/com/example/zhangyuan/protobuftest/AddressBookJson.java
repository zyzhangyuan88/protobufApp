package com.example.zhangyuan.protobuftest;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class AddressBookJson {
    private enum PhoneType {
        MOBILE,
        HOME,
        WORK
    }

    private static final class Phone {
        private String number;
        private PhoneType type;

        public Phone() {

        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }

        public void setType(PhoneType phoneType) {
            this.type = phoneType;
        }

        public PhoneType getType() {
            return type;
        }
    }
    private static final class Person {
        private String name;
        private int id;
        private String email;

        private List<Phone> phones;

        public Person() {
            phones = new ArrayList<>();
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void addPhone(Phone phone) {
            phones.add(phone);
        }

        public List<Phone> getPhones() {
            return phones;
        }
    }

    private static final class AddressBook {
        private List<Person> persons;

        public AddressBook() {
            persons = new ArrayList<>();
        }

        public void addPerson(Person person) {
            persons.add(person);
        }

        public List<Person> getPersons() {
            return persons;
        }
    }

    public static String encodeTest(String[] names) {
        AddressBook addressBook = new AddressBook();
        for (int i = 0; i < names.length; ++ i) {
            Person person = new Person();
            person.setName(names[i]);
            person.setEmail("zhangsan@gmail.com");
            person.setId(13958235);

            Phone phone = new Phone();
            phone.setNumber("0157-23443276");
            phone.setType(PhoneType.HOME);
            person.addPhone(phone);

            phone = new Phone();
            phone.setNumber("136183667387");
            phone.setType(PhoneType.MOBILE);
            person.addPhone(phone);

            addressBook.addPerson(person);
        }
        String jsonString = JSON.toJSONString(addressBook);
        return jsonString;
    }

    public static String encodeTest(String[] names, int times) {
        for (int i = 0; i < times - 1; ++ i) {
            encodeTest(names);
        }
        return encodeTest(names);
    }

    public static AddressBook decodeTest(String jsonStr, int times) {
        AddressBook addressBook = null;
        for (int i = 0; i < times; ++ i) {
            addressBook = JSON.parseObject(jsonStr, AddressBook.class);
        }
        return addressBook;
    }
}
