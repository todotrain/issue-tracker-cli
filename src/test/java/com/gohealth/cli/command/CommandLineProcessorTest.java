package com.gohealth.cli.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CommandLineProcessorTest {
    CommandLineProcessor commandLineProcessor = new CommandLineProcessor();
    
    @Test
    public void testNoArguments() throws Exception {
        String[] arrayArgs = {};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
    
    @Test
    public void testAddHappyPath() throws Exception {
        String[] arrayArgs = {"-a", "-d", "test description", "-l", "testLink.com"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("add", cmd.operation());
        assertEquals(false, cmd.params().isEmpty());
    }
    
    @Test
    public void testAddHappyPathWithParentId() throws Exception {
        String[] arrayArgs = {"-a", "-p", "123456", "-d", "test description", "-l", "testLink.com"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("add", cmd.operation());
        assertEquals(false, cmd.params().isEmpty());
    }

    @Test
    public void testAddWithoutArguments() throws Exception {
        String[] arrayArgs = {"-a"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
    

}