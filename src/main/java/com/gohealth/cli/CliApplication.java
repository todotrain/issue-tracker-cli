package com.gohealth.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gohealth.cli.command.CommandLineProcessor;
import com.gohealth.cli.service.CsvService;
import com.google.gson.Gson;
import com.gohealth.cli.command.Command;

@SpringBootApplication
public class CliApplication implements CommandLineRunner{

	@Autowired
	private CommandLineProcessor commandLineProcessor;

	@Autowired
	private CsvService csvService;

	public static void main(String[] args) {
		SpringApplication.run(CliApplication.class, args);
	}

	@Override
	public void run(String ...args) throws Exception{
		Command cmd = commandLineProcessor.getCommand(args);
		
		try {
			String filePath = "./data/issues.csv";
			List<String[]> records = csvService.readAllLines(filePath);
			
			if (cmd.operation().equals("add")){
				String parentId = cmd.params().getOrDefault("p", "");
				String description = cmd.params().getOrDefault("d", "");
				String link = cmd.params().getOrDefault("l", "");
				
				String newId = "1";
				if (records.size() > 1) {
					newId = String.valueOf(Integer.parseInt(records.get(records.size()-1)[0])+1);
				}

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").withZone(java.time.ZoneOffset.UTC);
				String now = LocalDateTime.now().format(formatter);
				records.add(new String[]{newId, description, parentId, "Open", now, link});

				System.out.println("Created issue with ID: " + newId);
				csvService.writeCsv(records, filePath);
			} else if (cmd.operation().equals("close")){
				String issueId = cmd.params().getOrDefault("i", "");

				Optional<String[]> optionalRecord = records.stream().filter(record -> record[0].equals(issueId)).findFirst();
				if (optionalRecord.isPresent()){
					optionalRecord.get()[3] = "Closed";
					System.out.println("Closed issue: " + issueId);
				} else {
					System.out.println("could not find issueId: " + issueId);
				}

				csvService.writeCsv(records, filePath);
			}
		} catch (Exception e) {
			System.out.println("csv issue");
            System.out.println(e.getMessage());
        }
	}

}
