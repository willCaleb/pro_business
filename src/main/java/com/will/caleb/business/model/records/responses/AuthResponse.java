package com.will.caleb.business.model.records.responses;

public record AuthResponse(String username, String token) implements AbstractResponseDTO {}