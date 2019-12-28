package ru.shakurov.webapp_javalab.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    Optional<T> findById(ID id);

    List<T> findAll();
}
