package com.emse.spring.faircorp.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.model.Heater;

@RestController // (1)
@RequestMapping("/api/heaters") // (2)
@Transactional // (3)

public class HeaterController {

    private HeaterDao heaterDao;

    public HeaterController(HeaterDao heaterDao) {
        this.heaterDao = heaterDao;
    }

    @GetMapping
    public List<HeaterDto> findAll() {
        System.out.println("test");
        System.out.println(heaterDao);
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());  // (6)
    }

    @PostMapping // (8)
    public HeaterDto create(@RequestBody HeaterDto dto) {
        // WindowDto must always contain the window room
        Heater heater = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(dto.getName(), dto.getHeaterStatus()));
        }
        else {
            heater = heaterDao.getById(dto.getId());  // (9)
            heater.setHeaterStatus(dto.getHeaterStatus());
        }
        return new HeaterDto(heater);
    }

    @GetMapping(path = "/{id}")
    public HeaterDto findById(@PathVariable Long id) {
        return heaterDao.findById(id).map(HeaterDto::new).orElse(null); // (7)
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        heaterDao.deleteById(id);
    }

}