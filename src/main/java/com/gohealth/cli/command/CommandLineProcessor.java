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

    private static final String COMMAND_ALIAS = "cli-0.0.1-SNAPSHOT.jar"; //todo update name once we figure out an alias

    public Command getCommand (String... args) throws Exception {
        String op = "none";
        Map<String, String> params = new HashMap<>();

        Options options = setOptions();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        if (args.length == 0){
            System.out.println("No argument provided");
            formatter.printHelp(COMMAND_ALIAS, options); //todo replace with more accurate instructions
            return new Command("error", null);
        }

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(COMMAND_ALIAS, options);
            return new Command("error", null);
        }
        
        if (cmd.hasOption("p")) {
            op = "add";
            params.put("p", cmd.getOptionValue("p"));
        }

        if (cmd.hasOption("d")) {
            op = "add";
            params.put("d", cmd.getOptionValue("d"));
        }

        if (cmd.hasOption("l")) {
            op = "add";
            params.put("l", cmd.getOptionValue("l"));
        }

        if (cmd.hasOption("c")) {
            if (op.equals("add")){
                System.out.println("Close parameter provided with excess parameters");
                formatter.printHelp(COMMAND_ALIAS, options); //todo replace with more accurate instructions
                return new Command("error", null);
            }

            params.put("c", cmd.getOptionValue("c"));
            op = "close";
        }

        return new Command(op, params);
    }

    //todo consider adding a param without argument just for the op
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
