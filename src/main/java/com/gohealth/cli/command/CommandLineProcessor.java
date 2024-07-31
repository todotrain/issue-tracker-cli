package com.gohealth.cli.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.stereotype.Component;

@Component
public class CommandLineProcessor {

    private static final String COMMAND_ALIAS = "cli.jar";

    public Command getCommand (String... args) {
        String op = "none";
        Map<String, String> params = new HashMap<>();

        Options options = setOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            if (args.length == 0) {
                throw new ParseException("No arguments provided");
            }
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            printCustomHelp(options);
            System.out.println(e.getMessage());
            return new Command("error", params);
        }
        
        if (cmd.hasOption("a") && cmd.hasOption("c")){
            printCustomHelp(options);
            System.out.println("Please pick either add or close (-add or -close)");
            return new Command("error", params);
        }
        
        if (cmd.hasOption("a")){
            return handleAddOperation(cmd);
        }
        
        if (cmd.hasOption("c")){
            return handleCloseOperation(cmd);
        }

        op = "error";
        printCustomHelp(options);
        System.out.println("Must pick at least one operation (-add or -close)");
        return new Command(op, params);
    }

    
    private Command handleAddOperation(CommandLine cmd){
        Map<String, String> params = new HashMap<>();

        if (cmd.hasOption("p")) {
            params.put("p", cmd.getOptionValue("p"));
        }

        if (cmd.hasOption("d")) {
            params.put("d", cmd.getOptionValue("d"));
        } else {
            System.out.println("Must provide a description for the issue");
            return new Command("error", params);
        }

        if (cmd.hasOption("l")) {
            params.put("l", cmd.getOptionValue("l"));
        } else {
            System.out.println("Must provide a link for the issue");
            return new Command("error", params);
        }
        
        return new Command("add", params);
    }

    private Command handleCloseOperation(CommandLine cmd){
        Map<String, String> params = new HashMap<>();

        if (cmd.hasOption("i")) {
            params.put("i", cmd.getOptionValue("i"));
        } else {
            System.out.println("Must provide an issue ID to close");
            return new Command("error", params);
        }

        return new Command("close", params);
    }

    private Options setOptions(){
        Options options = new Options();

        Option addOption = new Option("a", "add", false, "add record operation");
        addOption.setRequired(false);
        options.addOption(addOption);

        Option closeOption = new Option("c", "close", false, "close record operation");
        closeOption.setRequired(false);
        options.addOption(closeOption);

        Option parentOption = new Option("p", "parent", true, "Parent issue ID");
        parentOption.setRequired(false);
        options.addOption(parentOption);

        Option descriptionOption = new Option("d", "desc", true, "Description");
        descriptionOption.setRequired(false);
        options.addOption(descriptionOption);

        Option linkOption = new Option("l", "link", true, "URL to the log");
        linkOption.setRequired(false);
        options.addOption(linkOption);

        Option issueIdOption = new Option("i", "id", true, "Issue to be closed");
        issueIdOption.setRequired(false);
        options.addOption(issueIdOption);
        
        return options;
    }
    
    private void printCustomHelp(Options options) {
        
        System.out.println("Usage:");
        System.out.println("  " + COMMAND_ALIAS + " -a [-p <parent>] [-d <desc>] [-l <link>]");
        System.out.println("  " + COMMAND_ALIAS + " -c [-i <issue_id>]");
        System.out.println();
        
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(COMMAND_ALIAS, options); 
    }
}
