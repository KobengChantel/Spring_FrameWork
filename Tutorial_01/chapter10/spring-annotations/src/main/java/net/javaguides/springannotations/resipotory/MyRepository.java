package net.javaguides.springannotations.resipotory;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {
    public String hello(){
        return "Hello Repository!";
    }
}
