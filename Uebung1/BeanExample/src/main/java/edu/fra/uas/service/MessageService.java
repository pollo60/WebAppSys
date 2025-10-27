package edu.fra.uas.service;

import org.springframework.stereotype.Component;

@Component  // component ist eine weitere Möglichkeit, eine Bean zu definieren
public class MessageService {

    private String message;
    private int counter;

    public String getMessage(){
        return message;

    }

    public void setMessage(String message){
        this.message = message;
    }

    public int getCounter() {
        return counter;
    }

    public void increment() {
        counter++;
    }

}
