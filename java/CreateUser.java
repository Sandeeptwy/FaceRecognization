import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CreateUserRequest;
import com.amazonaws.services.rekognition.model.CreateUserResult;


public class CreateUser {

    public static void main(String[] args) {
        CreateUser createUser = new CreateUser();
        createUser.createUser();
    }

    public void createUser() {
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        //Replace collectionId and userId with the name of the user that you want to create in that target collection.

        String collectionId = "CricketPlayers";
        String userId = "shewag";
        System.out.println("Creating new user: " +
                userId);

        CreateUserRequest request = new CreateUserRequest()
                .withCollectionId(collectionId)
                .withUserId(userId);

        rekognitionClient.createUser(request);
    }

}