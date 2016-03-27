package com.example.hoanganh.fragmentdemo.utils;

import com.example.hoanganh.fragmentdemo.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by HoangAnh on 3/21/2016.
 */
public class PersonArrays {

    public static List<Person> personList = new ArrayList<>();

    static {
        Person[] p = new Person[1000];
        for (int i = 0; i < p.length; i++){
            Random random = new Random();
            int r = random.nextInt(2);

            p[i] = new Person( String.format("Person %d",i), String.format("%d/%d", i, i), (r==1)?true:false);

            personList.add(p[i]);
        }
    }

}
