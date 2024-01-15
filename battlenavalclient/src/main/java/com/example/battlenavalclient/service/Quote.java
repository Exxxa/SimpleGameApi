package com.example.battlenavalclient.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String type, Value value) { }
