package com.activemq.producer.publisher.impl;

import com.activemq.producer.dto.CarDto;
import com.activemq.producer.publisher.CarPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Topic;
import java.util.UUID;

@Service
@Slf4j
public class CarPublisherImpl implements CarPublisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Topic topic;

    private String sendMessage(CarDto dto){
        try{
            log.info("Attempting Send message to Topic: "+ topic);
            jmsTemplate.convertAndSend(topic, dto);
            return dto.getId();
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
        return "";
    }

    @Override
    public String publishCar(CarDto dto) {
        String id = UUID.randomUUID().toString();
        dto.setId(id);
        return sendMessage(dto);
    }
}
