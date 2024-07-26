package com.gohealth.cli;

import java.util.List;

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

		

		Gson gson = new Gson();

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

				records.add(new String[]{newId, description, parentId, "Open", "2024-07-26T11:02", link});
				csvService.writeCsv(records, filePath);
			}

			System.out.println(gson.toJson(records));
		} catch (Exception e) {
			System.out.println("csv issue");
            System.out.println(e.getMessage());
        }

		System.out.println(gson.toJson(cmd));
	}

}
