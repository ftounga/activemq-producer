package com.activemq.producer.publisher.impl;

import com.activemq.producer.dto.PersonDto;
import com.activemq.producer.publisher.PersonPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.util.UUID;

@Service
@Slf4j
public class PersonPublisherImpl implements PersonPublisher {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    private String sendMessage(PersonDto dto){
        try{
            log.info("Attempting Send message to Topic: "+ queue);
            jmsTemplate.convertAndSend(queue, dto);
            return dto.getId();
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
        return "";
    }

    @Override
    public String publishPerson(PersonDto dto) {
        String id = UUID.randomUUID().toString();
        dto.setId(id);
        return sendMessage(dto);
    }
}
