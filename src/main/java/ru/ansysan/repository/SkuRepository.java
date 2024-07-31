package ru.ansysan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ansysan.entity.Sku;

public interface SkuRepository extends JpaRepository<Sku, Integer> {
}
