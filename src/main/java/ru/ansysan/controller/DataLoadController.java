package ru.ansysan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ansysan.Service.DataLoaderService;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/load-data")
public class DataLoadController {

    @Autowired
    private DataLoaderService dataLoadService;

//  Эндпоинт для загрузки продуктов
    @PostMapping("/load")
    public ResponseEntity<String> loadData(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        try {
            dataLoadService.loadFilteredDataToElasticsearch(active, startDate);
            return ResponseEntity.ok("Data loaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading data");
        }
    }
}
