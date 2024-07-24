package com.gohealth.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gohealth.cli.command.CommandLineProcessor;

@SpringBootApplication
public class CliApplication implements CommandLineRunner{

	@Autowired
	private CommandLineProcessor commandLineStartupRunner;

	public static void main(String[] args) {
		SpringApplication.run(CliApplication.class, args);
	}

	@Override
	public void run(String ...args) throws Exception{
		commandLineStartupRunner.process(args);
	}

}
