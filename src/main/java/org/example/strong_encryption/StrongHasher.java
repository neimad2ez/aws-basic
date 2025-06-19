package org.example.strong_encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.mindrot.jbcrypt.BCrypt;

public class StrongHasher {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("config_2.properties")) { //Retrieves config.properties
            Properties properties = new Properties();

            properties.load(fis);
            String username = properties.getProperty("username"); //Retrieve values "xyz"
            String password = properties.getProperty("password");

            String bcryptHash = BCrypt.hashpw(password, BCrypt.gensalt(5)); //5 different iterations

            properties.setProperty("username", username); //Doesn't maintain order for config
            properties.setProperty("password", password);
            properties.setProperty("secure_password", bcryptHash);

            try(FileOutputStream output = new FileOutputStream("config_2.properties")) {
                properties.store(output, null); //Store new values in config_2.properties
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
