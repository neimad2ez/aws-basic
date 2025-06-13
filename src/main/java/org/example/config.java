package org.example;

import java.util.Optional;

public class config {
    private final String dbPassword;

    public config() {
        this.dbPassword = Optional.ofNullable(System.getenv("DB_PASSWORD"))
                .orElseThrow(() -> new IllegalStateException(("Environment variable DB_PASSWORD not set!")));
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void connectToDatabase() {
        int len = this.dbPassword.length();
        System.out.println("--- Using Environment Variable ---");
        System.out.println("Attempting to connect to the database...");
        System.out.println("Successfully used password ending with: " + this.dbPassword.substring(len - 3, len) + "...");
        System.out.println("------------------------------------------\n");
    }
}
