package com.john.springbatchnew.processor;

import com.john.springbatchnew.entity.PersonEntity;
import com.john.springbatchnew.model.Person;
import org.springframework.batch.item.ItemProcessor;


public class PersonBatchProcessor implements ItemProcessor<Person, PersonEntity> {

    @Override
    public PersonEntity process(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(person.getFirstName().toUpperCase());
        personEntity.setLastName(person.getLastName().toUpperCase());
        return personEntity;
    }
}
