package com.activemq.producer.publisher;

import com.activemq.producer.dto.PersonDto;

public interface PersonPublisher {

    public String publishPerson(PersonDto dto);
}
