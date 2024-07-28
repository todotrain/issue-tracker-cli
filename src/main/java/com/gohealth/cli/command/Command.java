package com.gohealth.cli.command;

import java.util.Map;

public record Command (String operation, Map<String, String> params) {}
