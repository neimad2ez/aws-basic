import java.io.*;
import java.util.Properties;
import org.jasypt.util.text.*;
import org.jasypt.util.password.*;

public class Hasher {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("config.properties")) { //Retrieves config.properties
            Properties properties = new Properties();

            properties.load(fis);
            String username = properties.getProperty("username"); //Retrieve values "xyz"
            String password = properties.getProperty("password");

            StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

            String hashedPassword = passwordEncryptor.encryptPassword(password); //Hashes password

            properties.setProperty("username", username); //Doesn't maintain order for config
            properties.setProperty("password", password);
            properties.setProperty("secure_password", hashedPassword);

            try(FileOutputStream output = new FileOutputStream("config.properties")) {
                properties.store(output, null); //Store new values in config.properties
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
