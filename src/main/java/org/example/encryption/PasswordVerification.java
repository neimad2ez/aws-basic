package org.example.encryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordVerification {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("config.properties")) { //Read from config.properties
            properties.load(fis);
            String hashedPassword = properties.getProperty("secure_password"); //Get hashed value

            System.out.println("Put in correct password."); //Input from user
            String input = scan.nextLine();

            boolean isPasswordValid = passwordEncryptor.checkPassword(input, hashedPassword);
            //Checks hash agianst user input
            System.out.println(isPasswordValid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
