package com.gohealth.cli.command;

import java.util.Map;

//todo implement interface instead?
public record Command (String op, Map<String, String> params) {}
