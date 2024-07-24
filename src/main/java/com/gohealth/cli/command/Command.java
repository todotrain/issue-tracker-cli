package com.gohealth.cli.command;

import java.util.Map;

public record Command (String op, Map<String, String> params) {}
