package com.gohealth.cli.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CommandLineProcessorTest {
    CommandLineProcessor commandLineProcessor = new CommandLineProcessor();
    
    @Test
    public void testNoParameters() {
        String[] arrayArgs = {};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
    
    @Test
    public void testAddHappyPathWithoutParentId() {
        String[] arrayArgs = {"-a", "-d", "test description", "-l", "testLink.com"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("add", cmd.operation());
        assertEquals(false, cmd.params().isEmpty());
        assertEquals("test description", cmd.params().getOrDefault("d", ""));
        assertEquals("", cmd.params().getOrDefault("p", ""));
        assertEquals("testLink.com", cmd.params().getOrDefault("l", ""));
    }
    
    @Test
    public void testAddHappyPathWithParentId() {
        String[] arrayArgs = {"-a", "-p", "123456", "-d", "test description", "-l", "testLink.com"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("add", cmd.operation());
        assertEquals("test description", cmd.params().getOrDefault("d", ""));
        assertEquals("123456", cmd.params().getOrDefault("p", ""));
        assertEquals("testLink.com", cmd.params().getOrDefault("l", ""));
    }
    
    @Test
    public void testAddWithoutArguments() {
        String[] arrayArgs = {"-a"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
    
    @Test
    public void testAddWithoutDescription() {
        String[] arrayArgs = {"-a", "-p", "123456", "-l", "testLink.com"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
    }
    
    @Test
    public void testAddWithoutLink() {
        String[] arrayArgs = {"-a", "-p", "123456", "-d", "test description"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
    }
    
    @Test
    public void testAddWithEmptyDescription() {
        String[] arrayArgs = {"-a", "-p", "123456", "-d", "-l", "testLink.com"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
    
    @Test
    public void testAddWithEmptyLink() {
        String[] arrayArgs = {"-a", "-p", "123456", "-d", "test description", "-l"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
    
    @Test
    public void testAddWithEmptyParent() {
        String[] arrayArgs = {"-a", "-p", "-d", "test description", "-l", "testLink.com"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }

    @Test
    public void testWithoutOperationParameter() {
        String[] arrayArgs = {"-p", "123456", "-d", "test description", "-l", "testLink.com"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
    
    @Test
    public void testClose() {
        String[] arrayArgs = {"-c", "-i", "3"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("close", cmd.operation());
        assertEquals("3", cmd.params().getOrDefault("i", ""));
    }

    @Test
    public void testCloseWithoutArguments() {
        String[] arrayArgs = {"-c"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
    
    @Test
    public void testMultipleOperationsGiven() {
        String[] arrayArgs = {"-c", "-i", "1", "-a", "-p", "123456", "-d", "test description", "-l", "testLink.com"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
    
    @Test
    public void testInvalidParametersGiven() {
        String[] arrayArgs = {"-a", "-p", "123456", "-d", "test description", "-l", "testLink.com", "-z", "bfao"};
        Command cmd = commandLineProcessor.getCommand(arrayArgs);
        assertEquals("error", cmd.operation());
        assertEquals(true, cmd.params().isEmpty());
    }
}