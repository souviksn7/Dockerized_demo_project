package com.example.Task.service.serviceImpl;

import com.example.Task.entity.Person;
import com.example.Task.repo.PersonRepository;
import com.example.Task.service.KafkaService;
import com.example.Task.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    KafkaService kafkaService;


    @Override
    public Person addPerson(Person person) {
        Person temp_person = personRepository.save(person);
        String temp_person_string = "Name: " + person.getName() + ", Address: " + person.getAddress();
        kafkaService.addPerson(temp_person_string);
        return temp_person;
    }

    @Override
    public Person getPersonById(Long person_id) {
        return personRepository.findById(person_id).get();
    }

    @Override
    public Set<Person> getAllPerson() {
        return new LinkedHashSet<>(personRepository.findAll());
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Long person_id) {
        Person temp_person = personRepository.findById(person_id).get();
        if(temp_person != null)
            personRepository.deleteById(person_id);
        else
            System.out.println("Person not found");
    }
}
