package ru.ansysan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ansysan.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}
