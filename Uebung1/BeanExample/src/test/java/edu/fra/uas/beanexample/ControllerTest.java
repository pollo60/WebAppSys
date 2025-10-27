package edu.fra.uas.beanexample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.fra.uas.controller.BeanController;
import edu.fra.uas.service.MessageService;

@SpringBootTest
public class ControllerTest {
    
    @Autowired
    private BeanController beanController;

    @Autowired
    private MessageService messageService;

    @Test
    void testController() {
        assertThat(beanController.putMessage("Das ist ein Test")).isEqualTo(" put messgae: Das ist ein Test");
    }

    @Test
    void testIncrement() {
        int initialCounter = messageService.getCounter();
        System.out.println("Initial Counter: " + initialCounter);
        
        messageService.increment();
        System.out.println("Counter after 1st increment: " + messageService.getCounter());
        assertThat(messageService.getCounter()).isEqualTo(initialCounter + 1);
        
        messageService.increment();
        System.out.println("Counter after 2nd increment: " + messageService.getCounter());
        assertThat(messageService.getCounter()).isEqualTo(initialCounter + 2);
    }

}
