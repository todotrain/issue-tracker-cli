package com.gohealth.cli.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

@Service
public class CsvService {
    public List<String[]> readAllLines(String filePath) throws Exception {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            return csvReader.readAll();
        }
    }

    public void writeCsv(List<String[]> data, String filePath) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
            csvWriter.writeAll(data);
        }
    }

    
}
