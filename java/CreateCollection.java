
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;

import java.util.HashMap;
import java.util.Map;


public class CreateCollection {

    public static void main(String[] args) throws Exception {
        CreateCollection createCollection = new CreateCollection();
        createCollection.createCollection();
    }
        public void createCollection() {
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();


        String collectionId = "CricketPlayers";
        System.out.println("Creating collection: " +
                collectionId);

        Map<String, String> map = new HashMap<>();
        map.put("Game", "Cricket");


        CreateCollectionRequest request = new CreateCollectionRequest()
                .withCollectionId(collectionId)
                .withTags(map);

        CreateCollectionResult createCollectionResult = rekognitionClient.createCollection(request);
        System.out.println("CollectionArn : " +
                createCollectionResult.getCollectionArn());
        System.out.println("Status code : " +
                createCollectionResult.getStatusCode().toString());

    }

}


