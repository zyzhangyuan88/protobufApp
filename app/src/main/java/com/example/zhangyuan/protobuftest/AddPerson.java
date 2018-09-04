package com.example.zhangyuan.protobuftest;

public class AddPerson {
    static AddressBookProtos.Person createPerson(String personName) {
        AddressBookProtos.Person.Builder person = AddressBookProtos.Person.newBuilder();

        int id = 13958235;
        person.setId(id);

        String name = personName;
        person.setName(name);

        String email = "zhangsan@gmail.com";
        person.setEmail(email);

        AddressBookProtos.Person.PhoneNumber.Builder phoneNumber = AddressBookProtos.Person.PhoneNumber.newBuilder();
        phoneNumber.setType(AddressBookProtos.Person.PhoneType.HOME);
        phoneNumber.setNumber("0157-23443276");

        person.addPhone(phoneNumber.build());

        phoneNumber = AddressBookProtos.Person.PhoneNumber.newBuilder();
        phoneNumber.setType(AddressBookProtos.Person.PhoneType.MOBILE);
        phoneNumber.setNumber("136183667387");

        person.addPhone(phoneNumber.build());

        return person.build();
    }
}


