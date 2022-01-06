package com.activemq.producer.controller;

import com.activemq.producer.dto.CarDto;
import com.activemq.producer.dto.PersonDto;
import com.activemq.producer.publisher.CarPublisher;
import com.activemq.producer.publisher.PersonPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PublisherController {

    @Autowired
    private PersonPublisher personPublisher;

    @Autowired
    private CarPublisher carPublisher;

    @PostMapping(value = "/api/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendPersonDTO(@RequestBody  PersonDto dto){
        String id = personPublisher.publishPerson(dto);
        log.info("The thread "+Thread.currentThread().getName()+" push person with id: "+ dto.getId());
        return ResponseEntity.ok(id);
    }

    @PostMapping(value = "/api/car",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendPersonDTO(@RequestBody CarDto dto){
        String id = carPublisher.publishCar(dto);
        log.info("The thread "+Thread.currentThread().getName()+" push car with id: "+ dto.getId());
        return ResponseEntity.ok(id);
    }
}
