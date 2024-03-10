package ru.damir.stock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.damir.stock.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
    Optional<Category> findByName(String categoryName);
}
