

public class EndToEndUserRegistration {

    static ImageUploader imageUploader = new ImageUploader(); //Upload Image to S3
    static CreateCollection createCollection = new CreateCollection(); //Create collection
    static CreateUser createUser = new CreateUser(); //Create userId for registered users
    static AddFacesToCollection addFacesToCollection = new AddFacesToCollection(); //Crop faces from Selfie and user Id to that face ID

    //To Search for a user by image run SearchUsersByImage
    public static void main(String[] args) {
        imageUploader.imageUpload();
        createCollection.createCollection();
        createUser.createUser();
        addFacesToCollection.addFacesFromImageToCollection();
    }
}
