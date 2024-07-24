package com.gohealth.cli.command;

import org.junit.jupiter.api.Test;


public class CommandLineProcessorTest {
    CommandLineProcessor commandLineProcessor = new CommandLineProcessor();
    
    //todo finish implementing
    @Test
    public void testNoArguments() throws Exception{
        String[] arrayArgs = {};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
    }
}