package DAO;

import models.Log;

import java.util.List;

public interface LogDAO {
    void save(Log log);

    Log findById(Long id);

    List<Log> findAll();
}
