package com.example.Task.service;

import com.example.Task.entity.Person;

import java.util.Set;

public interface PersonService {
    Person addPerson(Person person);
    Person getPersonById(Long person_id);
    Set<Person> getAllPerson();
    Person updatePerson(Person person);
    void deletePerson(Long person_id);
}
