package com.gohealth.cli.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineStartupRunner implements CommandLineRunner {
    @Override
    public void run (String... args) throws Exception {
        if (args.length > 0){
            System.out.println("hi " + args[0]);
        } else {
            System.out.println("gotta give me your name");
        }
    }
}
