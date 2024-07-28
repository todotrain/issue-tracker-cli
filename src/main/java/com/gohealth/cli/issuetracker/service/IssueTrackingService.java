package com.gohealth.cli.issuetracker.service;

import java.util.Map;

public interface IssueTrackingService {
    public void add(Map<String, String> params);
    public void close(String issueId);
}
