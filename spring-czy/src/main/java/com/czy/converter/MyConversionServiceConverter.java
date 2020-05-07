package com.czy.converter;

import com.czy.model.Person;
import org.springframework.core.convert.converter.Converter;

public class MyConversionServiceConverter implements Converter<String, Person> {
    @Override
    public Person convert(String source) {
        if (source != null) {
            String[] vals = source.split("-");
            if (vals != null && vals.length == 4) {
                String name = vals[0];
                String password = vals[1];
                String age = vals[2];
                String email = vals[3];

                Person person = new Person(1, name, password, age, email);
                System.out.println(source + "--convert--"+ person);
                return person;
            }
        }
        return null;
    }
}
