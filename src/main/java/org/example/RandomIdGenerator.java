package org.example;

import java.util.UUID;

public class RandomIdGenerator {
    public String generateId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}

