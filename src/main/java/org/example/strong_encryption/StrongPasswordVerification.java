package org.example.strong_encryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class StrongPasswordVerification {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config_2.properties")) { //Read from config.properties
            properties.load(fis);
            String hashedPassword = properties.getProperty("secure_password"); //Get hashed value

            System.out.println("Put in correct password."); //Input from user
            String input = scan.nextLine();

            boolean isPasswordValid = BCrypt.checkpw(input, hashedPassword);
            //Checks hash against user input
            System.out.println(isPasswordValid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
