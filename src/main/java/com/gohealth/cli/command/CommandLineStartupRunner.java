package com.gohealth.cli.command;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineStartupRunner implements CommandLineRunner {
    @Override
    public void run (String... args) throws Exception {

        Options options = setOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        try {
            if (args.length == 0){
                throw new ParseException("No arguments provided");
            }

            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("cli-0.0.1-SNAPSHOT.jar", options); //todo update name once we figure out an alias
            return;
        }

        if (cmd.hasOption("p")) {
            System.out.println("Parent Issue ID: " + cmd.getOptionValue("p"));
        }

        if (cmd.hasOption("d")) {
            System.out.println("Description: " + cmd.getOptionValue("d"));
        }

        if (cmd.hasOption("l")) {
            System.out.println("URL: " + cmd.getOptionValue("l"));
        }

        //todo make sure to make sure this option isn't used in conjunction with other options
        if (cmd.hasOption("c")) {
            System.out.println("Issue to be closed: " + cmd.getOptionValue("c"));
        }
    }

    public Options setOptions(){
        Options options = new Options();

        Option parentOption = new Option("p", "parent", true, "Parent issue ID");
        parentOption.setRequired(false);
        options.addOption(parentOption);

        Option descriptionOption = new Option("d", "desc", true, "Description");
        descriptionOption.setRequired(false);
        options.addOption(descriptionOption);

        Option linkOption = new Option("l", "link", true, "URL to the log");
        linkOption.setRequired(false);
        options.addOption(linkOption);

        Option closeOption = new Option("c", "close", true, "Issue to be closed");
        closeOption.setRequired(false);
        options.addOption(closeOption);
        
        return options;
    }
}
