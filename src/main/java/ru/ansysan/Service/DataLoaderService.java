package ru.ansysan.Service;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ansysan.entity.Product;
import ru.ansysan.entity.ProductSku;
import ru.ansysan.entity.Sku;
import ru.ansysan.repository.ProductRepository;
import ru.ansysan.repository.SkuRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class DataLoaderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SkuRepository skuRepository;

    private RestHighLevelClient elasticsearchClient;

    public void loadFilteredDataToElasticsearch(Boolean activeFilter, LocalDate startDateFilter) throws IOException {
        List<Product> products = productRepository.findAll();
        List<Sku> skus = skuRepository.findAll();

        for (Product product : products) {
            // Применяем фильтры
            if ((activeFilter != null && !product.isActive()) ||
                    (startDateFilter != null && !product.getStartDate().isAfter(startDateFilter))) {
                continue; // Пропускаем продукт, если он не проходит фильтры
            }

            for (Sku sku : skus) {
                if (sku.getId().equals(product.getId())) {
                    ProductSku productSku = new ProductSku();
                    productSku.setProductId(product.getId());
                    productSku.setProductName(product.getName());
                    productSku.setProductDescription(product.getDescription());
                    productSku.setProductPrice(product.getPrice());
                    productSku.setProductActive(product.isActive());
                    productSku.setProductStartDate(product.getStartDate());
                    productSku.setSkuCode(sku.getSkuCode());
                    productSku.setSkuStock(sku.getStockQuantity());

                    IndexRequest request = new IndexRequest("products_skus")
                            .id(String.valueOf(product.getId()) + "_" + sku.getId())
                            .source(XContentType.JSON, "productId", productSku.getProductId(),
                                    "productName", productSku.getProductName(),
                                    "productDescription", productSku.getProductDescription(),
                                    "productPrice", productSku.getProductPrice(),
                                    "productActive", productSku.getProductActive(),
                                    "productStartDate", productSku.getProductStartDate(),
                                    "skuCode", productSku.getSkuCode(),
                                    "skuStock", productSku.getSkuStock());

                    elasticsearchClient.index(request, RequestOptions.DEFAULT);
                }
            }
        }
    }
}