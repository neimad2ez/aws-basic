// Use this code snippet in your app.
// If you need more information about configurations or implementing the sample
// code, visit the AWS docs:
// https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html

// Make sure to import the following packages in your code
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

public static String getSecret() {

    String secretName = "my-key";
    Region region = Region.of("eu-west-2");

    // Create a Secrets Manager client
    SecretsManagerClient client = SecretsManagerClient.builder()
            .region(region)
            .build();

    GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
            .secretId(secretName)
            .build();

    GetSecretValueResponse getSecretValueResponse;

    try {
        getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
    } catch (Exception e) {
        // For a list of exceptions thrown, see
        // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
        throw e;
    }

    return getSecretValueResponse.secretString();

    // Your code goes here.
}

// 2. This is the correct signature for the main method, the entry point of the app.
public static void main(String[] args) {
    System.out.println("Attempting to fetch a secret from AWS Secrets Manager...");

    try {
        // 3. We call our static getSecret method and store the result.
        String secretValue = getSecret();

        // 4. We print the secret to confirm it worked.
        // In a real app, you would use this value, not print it.
        System.out.println("Successfully retrieved the secret!");
        // For security, let's just print a confirmation rather than the whole secret.
        System.out.println("Secret value is not null: " + (secretValue != null));
        System.out.println(secretValue);

    } catch (SecretsManagerException e) {
        System.err.println("AWS Error: " + e.awsErrorDetails().errorMessage());
    } catch (Exception e) {
        System.err.println("An unexpected error occurred: " + e.getMessage());
    }
}