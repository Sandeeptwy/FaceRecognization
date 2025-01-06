import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Paths;

public class ImageUploader {
    public static void main(String[] args) {

        ImageUploader imageUploader = new ImageUploader();
        imageUploader.imageUpload();
    }

    public void imageUpload() {

        String accessKey = "";
        String secretKey = "";

        String bucketName = "face-recognition-geek"; // Replace with your bucket name
        String keyName = "shewag.jpg"; // Path in S3 (object key)
        String filePath = "/Users/sandeeptiwari/Downloads/shewag.jpg"; // Local file path

        // Create AWS S3 client
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        S3Client s3Client = S3Client.builder()
                .region(Region.AP_SOUTH_1) // Replace with your S3 bucket's region
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        try {
            // Create a PutObjectRequest
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .build();

            // Upload the file
            s3Client.putObject(putObjectRequest, Paths.get(filePath));

            System.out.println("File uploaded successfully to S3!");
        } catch (Exception e) {
            System.err.println("Failed to upload file: " + e.getMessage());
        } finally {
            s3Client.close();
        }
    }
}
