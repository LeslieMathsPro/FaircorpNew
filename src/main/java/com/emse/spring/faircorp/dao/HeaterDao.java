package com.emse.spring.faircorp.dao;

        import java.util.List;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

        import com.emse.spring.faircorp.model.Heater;

public interface HeaterDao extends JpaRepository<Heater, Long> {
    @Query("select h from Heater h where h.name=:name")
    Heater findByName(@Param("name") String name);

    @Modifying
    @Query("delete from Heater h where h.room.id=?1")
    void deleteAllHeatersByRoom(Long id);

    @Query("select h from Heater h where h.room.id=?1")
    List<Heater> findByRoom(Long id);

}