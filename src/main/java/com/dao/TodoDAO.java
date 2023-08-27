package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Todo;

@Component
public class TodoDAO {

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Transactional
    public int save(Todo t) {
        Integer i = (Integer) this.hibernateTemplate.save(t);
        return i;
    }

    @Transactional
    public void update(Todo t) {
        this.hibernateTemplate.update(t);
    }

    @Transactional
    public void delete(Todo t) {
        this.hibernateTemplate.delete(t);
    }

    public Todo getById(int id) {
        return this.hibernateTemplate.get(Todo.class, id);
    }

    public List<Todo> getAll() {
        List<Todo> todos = this.hibernateTemplate.loadAll(Todo.class);
        return todos;
    }
}
