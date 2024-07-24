package com.gohealth.cli.command;

import java.util.Map;

//todo implement interface instead?
public record Command (String operation, Map<String, String> params) {}
