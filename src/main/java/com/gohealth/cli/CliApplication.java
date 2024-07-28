package com.gohealth.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gohealth.cli.command.CommandLineProcessor;
import com.gohealth.cli.issuetracker.service.IssueTrackingService;
import com.gohealth.cli.issuetracker.service.impl.CsvIssueTrackingService;
import com.gohealth.cli.command.Command;

@SpringBootApplication
public class CliApplication implements CommandLineRunner{

	@Autowired
	private CommandLineProcessor commandLineProcessor;

	public static void main(String[] args) {
		SpringApplication.run(CliApplication.class, args);
	}

	@Override
	public void run(String ...args) throws Exception{
		Command cmd = commandLineProcessor.getCommand(args);

		IssueTrackingService issueTrackingService = new CsvIssueTrackingService();
		if (cmd.operation().equals("add")){
			issueTrackingService.add(cmd.params());
		} else if (cmd.operation().equals("close")){
			issueTrackingService.close(cmd.params().getOrDefault("i", ""));
		}
	}

}
