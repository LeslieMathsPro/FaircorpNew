package com.emse.spring.faircorp.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.WindowStatus;

@RestController // (1)
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
public class RoomController {

    private final WindowDao windowDao;
    private final RoomDao roomDao;
    private final HeaterDao heaterDao;

    public RoomController(WindowDao windowDao, RoomDao roomDao, HeaterDao heaterDao) { // (4)
        this.heaterDao = heaterDao;
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null); // (7)
    }

    @PostMapping // (8)
    public RoomDto create(@RequestBody RoomDto dto) {
        Room room = null;
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getName(),dto.getFloor()));
        }
        else {
            room = roomDao.getById(dto.getId());  // (9)
            room.setFloor(dto.getFloor());
        }
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteByRoom(id);
        heaterDao.deleteAllHeatersByRoom(id);
        roomDao.deleteById(id);
    }

    @PutMapping(path = "/{id}/switchWindows")
    public RoomDto switchWindows(@PathVariable Long roomId) {
        Room room = roomDao.findById(roomId).orElseThrow(IllegalArgumentException::new);
        room.getWindows().forEach(window->
                window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN)
        );
        return new RoomDto(room);
    }

    @PutMapping(path = "/{id}/switchHeaters")
    public RoomDto switchHeaters(@PathVariable Long roomId) {
        Room room = roomDao.findById(roomId).orElseThrow(IllegalArgumentException::new);
        room.getHeaters().forEach(heater->
                heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON)
        );
        return new RoomDto(room);
    }


}