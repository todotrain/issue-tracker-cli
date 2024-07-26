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
			List<String[]> records = csvService.readAllLines("./data/issues.csv");
			System.out.println(gson.toJson(records));
		} catch (Exception e) {
            System.out.println(e.getMessage());
        }

		System.out.println(gson.toJson(cmd));
	}

}
