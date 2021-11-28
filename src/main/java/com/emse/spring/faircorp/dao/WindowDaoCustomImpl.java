
package com.emse.spring.faircorp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

public class WindowDaoCustomImpl implements WindowDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String jpql = "select w from Window w where w.room.id = :id and w.windowStatus= :status";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .setParameter("status", WindowStatus.OPEN)
                .getResultList();
    }

    @Override
    public int deleteAllWindowsInARoom(Long id) {
        String jpql = "delete from Window w where w.room.id = :id";
        return em.createQuery(jpql).setParameter("id",id).executeUpdate();
    }

    @Override
    public List<Window> findByRoomId(Long RoomId) {
        String jpql = "select w from Window w where w.room.id = :id";
        return em.createQuery(jpql, Window.class).setParameter("id", RoomId).getResultList();
    }
}