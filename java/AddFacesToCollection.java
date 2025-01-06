import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

import java.util.Arrays;
import java.util.List;

public class AddFacesToCollection {
    public static final String collectionId = "CricketPlayers";
    public static final String bucket = "face-recognition-geek";
    public static final String photo = "shewag.jpg"; //S3

    static final String userId = "shewag";


    public static void main(String[] args) throws Exception {
        AddFacesToCollection addFacesToCollection = new AddFacesToCollection();
        addFacesToCollection.addFacesFromImageToCollection();
    }

    public void addFacesFromImageToCollection() {
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        Image image = new Image()
                .withS3Object(new S3Object()
                        .withBucket(bucket)
                        .withName(photo));

        IndexFacesRequest indexFacesRequest = new IndexFacesRequest()
                .withImage(image)
                .withQualityFilter(QualityFilter.AUTO)
                .withMaxFaces(1)
                .withCollectionId(collectionId)
                .withExternalImageId(photo)
                .withDetectionAttributes("DEFAULT");

        IndexFacesResult indexFacesResult = rekognitionClient.indexFaces(indexFacesRequest);

        System.out.println("Results for " + photo);
        System.out.println("Faces indexed:");
        List<FaceRecord> faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord : faceRecords) {
            System.out.println("  Face ID: " + faceRecord.getFace().getFaceId());
            System.out.println("  confidence: " + faceRecord.getFace().getConfidence());
            System.out.println("  Location:" + faceRecord.getFaceDetail().getBoundingBox().toString());
            asociatesFacesWithUserID(faceRecord.getFace().getFaceId());
        }

        List<UnindexedFace> unindexedFaces = indexFacesResult.getUnindexedFaces();
        System.out.println("Faces not indexed:");
        for (UnindexedFace unindexedFace : unindexedFaces) {
            System.out.println("  Location:" + unindexedFace.getFaceDetail().getBoundingBox().toString());
            System.out.println("  Reasons:");
            for (String reason : unindexedFace.getReasons()) {
                System.out.println("   " + reason);
            }
        }

    }


    public static void asociatesFacesWithUserID(String faceid) {
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        String faceId1 = faceid;
        List<String> faceIds = Arrays.asList(faceId1);

        float userMatchThreshold = 80f;
        System.out.println("Associating faces to the existing user: " +
                userId);

        AssociateFacesRequest request = new AssociateFacesRequest()
                .withCollectionId(collectionId)
                .withUserId(userId)
                .withFaceIds(faceIds)
                .withUserMatchThreshold(userMatchThreshold);

        AssociateFacesResult result = rekognitionClient.associateFaces(request);

        System.out.println("Successful face associations: " + result.getAssociatedFaces().size());
        System.out.println("Unsuccessful face associations: " + result.getUnsuccessfulFaceAssociations().size());
    }
}
