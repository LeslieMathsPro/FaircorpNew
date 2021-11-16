package com.emse.spring.faircorp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.emse.spring.faircorp.model.Room;

public interface RoomDaoCustom extends JpaRepository<Room, Long> {
    @Query("select r from Room r where r.name=:name")
    Room findByName(@Param("name") String name);
}