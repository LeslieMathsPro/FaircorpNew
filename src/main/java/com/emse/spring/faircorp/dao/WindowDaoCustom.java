package com.emse.spring.faircorp.dao;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.emse.spring.faircorp.model.Window;

public interface WindowDaoCustom {
    List<Window> findRoomOpenWindows(@Param("id") Long id);

    int deleteAllWindowsInARoom(Long RoomId);

    List<Window> findByRoomId(Long RoomId);
}