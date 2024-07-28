package com.gohealth.cli.issuetracker.service.impl;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.gohealth.cli.issuetracker.service.IssueTrackingService;

public class CsvIssueTrackingService implements IssueTrackingService {
    private static final String FILE_PATH = "./data/issues.csv";

    public List<String[]> readAllLines() {
        try (CSVReader csvReader = new CSVReader(new FileReader(FILE_PATH))) {
            return csvReader.readAll();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
    
    public void writeCsv(List<String[]> data) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(FILE_PATH))) {
            csvWriter.writeAll(data);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Map<String, String> params) {
        List<String[]> records = readAllLines();

        String parentId = params.getOrDefault("p", "");
        String description = params.getOrDefault("d", "");
        String link = params.getOrDefault("l", "");
        
        String newId = "1";
        if (records.size() > 1) {
            newId = String.valueOf(Integer.parseInt(records.get(records.size()-1)[0])+1);
        }

        DateTimeFormatter formatter = 
            DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm")
                .withZone(java.time.ZoneOffset.UTC);
                
        String now = LocalDateTime.now().format(formatter);
        records.add(new String[]{newId, description, parentId, "Open", now, link});

        System.out.println("Created issue with ID: " + newId);
        writeCsv(records);
    }

    @Override
    public void close(String issueId) {
        List<String[]> records = readAllLines();

        Optional<String[]> optionalRecord = 
            records.stream()
                .filter(record -> record[0].equals(issueId))
                .findFirst();

        if (optionalRecord.isPresent()){
            optionalRecord.get()[3] = "Closed";
            System.out.println("Closed issue: " + issueId);
        } else {
            System.out.println("could not find issueId: " + issueId);
        }

        writeCsv(records);
    }
}

