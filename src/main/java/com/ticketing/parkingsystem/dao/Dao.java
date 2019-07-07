package com.ticketing.parkingsystem.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

   Optional<T> getById(String id);

   List<T> getAll();

   T upsert(T t);

   boolean delete(T t);

}
