package com.activemq.producer.publisher;

import com.activemq.producer.dto.CarDto;

public interface CarPublisher {

    public String publishCar(CarDto dto);
}
