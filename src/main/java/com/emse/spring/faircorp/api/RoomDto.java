package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;




public class RoomDto {

    public RoomDto() {
    }

    public RoomDto(Room room) {
        super();
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.current_temperature = room.getCurrentTemperature();
        this.target_temperature = room.getTargetTemperature();
        this.heaterIdList = room.getHeaters().stream().map(heater -> heater.getId()).collect(Collectors.toSet());
        this.windowIdList = room.getWindows().stream().map(window -> window.getId()).collect(Collectors.toSet());
    }


    private Long id;
    private String name;
    private Integer floor;
    private Double current_temperature;
    private Double target_temperature;

    private Set<Long> heaterIdList;
    private Set<Long> windowIdList;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getFloor() {
        return floor;
    }
    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    public Double getCurrent_temperature() {
        return current_temperature;
    }
    public void setCurrent_temperature(Double current_temperature) {
        this.current_temperature = current_temperature;
    }
    public Double getTarget_temperature() {
        return target_temperature;
    }
    public void setTarget_temperature(Double target_temperature) {
        this.target_temperature = target_temperature;
    }
    public Set<Long> getHeaterIdList() { return heaterIdList; }
    public void setHeaterIdList(Set<Long> heaterIdList) {
        this.heaterIdList = heaterIdList;
    }
    public Set<Long> getWindowIdList() {
        return windowIdList;
    }
    public void setWindowIdList(Set<Long> windowIdList) {
        this.windowIdList = windowIdList;
    }

}