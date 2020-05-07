package com.czy.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.czy.model.Person;

@Component
public class MyConversionServiceConverter implements Converter<String, Person> {
    @Override
    public Person convert(String source) {
        if (source != null) {
            String[] vals = source.split("-");
            if (vals.length == 4) {
                String name = vals[0];
                String password = vals[1];
                String age = vals[2];
                String email = vals[3];

                Person person = new Person();
                person.setId(1);
                person.setName(name);
                person.setPassword(password);
                person.setAge(age);
                person.setEmail(email);
                System.out.println(source + "--convert--" + person);
                return person;
            }
        }
        return null;
    }
}
