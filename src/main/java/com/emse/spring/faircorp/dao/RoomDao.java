package com.emse.spring.faircorp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.emse.spring.faircorp.model.Room;

public interface RoomDao extends JpaRepository<Room, Long> {
    @Query("select r from Room r where r.name=:name")
    Room findByName(@Param("name") String name);

    @Query("select r from Room r where r.id=:id")
    public Room getOne(@Param("id") Long id);

    @Query("select w from Window w where w.room=:room")
    public Room getWindows(@Param("room") Long room);

}