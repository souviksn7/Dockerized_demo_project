package com.example.Task.controller;

import com.example.Task.entity.Person;
import com.example.Task.service.PersonService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@CrossOrigin("*")
public class PersonController {
    @Autowired
    PersonService personService;
    @PostMapping("/")
    ResponseEntity<Person> addPerson(@RequestBody Person person){
        return ResponseEntity.ok(this.personService.addPerson(person));
    }

    @GetMapping("/{person_id}")
    ResponseEntity<Person> getPersonById(@PathVariable("person_id") Long person_id){
        return ResponseEntity.ok(this.personService.getPersonById(person_id));
    }

    @GetMapping("/all")
    ResponseEntity<?> getAllPerson(){
        return ResponseEntity.ok(personService.getAllPerson());
    }

    @PutMapping("/")
    ResponseEntity<Person> updatePerson(@RequestBody Person person){
        return ResponseEntity.ok(this.personService.updatePerson(person));
    }

    @DeleteMapping("/{person_id}")
    ResponseEntity<String> deletePerson(@PathVariable("person_id") Long person_id){
        Person temp_person = this.personService.getPersonById(person_id);
        if(temp_person != null){
            this.personService.deletePerson(person_id);
            return ResponseEntity.ok("Person deleted successfully");
        }
        return ResponseEntity.ok("Person with the above person_id not found");
    }
}
