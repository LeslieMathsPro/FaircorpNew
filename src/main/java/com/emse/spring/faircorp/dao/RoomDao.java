package com.emse.spring.faircorp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emse.spring.faircorp.model.Room;

public interface RoomDao extends JpaRepository<Room,Long>,RoomDaoCustom{

}