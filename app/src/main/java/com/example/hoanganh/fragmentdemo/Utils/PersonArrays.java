package com.example.hoanganh.fragmentdemo.utils;

import com.example.hoanganh.fragmentdemo.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangAnh on 3/21/2016.
 */
public class PersonArrays {

    public static List<Person> personList = new ArrayList<>();

    static {
        Person p1 = new Person("Mai Hoàng Anh", "16/08/1994", true);
        Person p2 = new Person("Hà Nam Tiến", "23/05/1994", true);
        Person p3 = new Person("Trần Văn A", "23/05/1994", true);
        Person p4 = new Person("Hà TRung Kiên", "23/05/1994", true);
        Person p5 = new Person("Đỗ VĂn Tuấn", "23/05/1994", true);
        Person p6 = new Person("Hà Nam", "23/05/1994", true);
        Person p7 = new Person("Thái Bình", "23/05/1994", true);
        Person p8 = new Person("Hà Nội", "23/05/1994", true);
        Person p9 = new Person("Hà Tây", "23/05/1994", true);

        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);
        personList.add(p6);
        personList.add(p7);
        personList.add(p8);
        personList.add(p9);

    }

}
