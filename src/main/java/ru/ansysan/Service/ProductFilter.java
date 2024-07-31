package ru.ansysan.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ansysan.config.FilterConfig;
import ru.ansysan.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductFilter {

    @Autowired
    private FilterConfig filterConfig;

    public List<Product> Filter(List<Product> products) {
        if (!filterConfig.isFilterEnabled()) {
            return products; // Если фильтр отключен, возвращаем все продукты
        }

        return products.stream()
                .filter(product -> product.getColor() != null && product.getAvailable())
                .collect(Collectors.toList());
    }
}
