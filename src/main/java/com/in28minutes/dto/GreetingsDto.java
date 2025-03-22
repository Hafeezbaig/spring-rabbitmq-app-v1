package com.in28minutes.dto;

import lombok.Data;

/**
 * Lombok @Data automatically generates:
 * - Getters and setters
 * - toString(), equals(), and hashCode()
 * - A required constructor
 * Official docs:
 * <a href="https://projectlombok.org/features/Data">@Data - Lombok</a>
 */
@Data
public class GreetingsDto {
    private String message;
}
