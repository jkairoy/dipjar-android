package pojos;

import com.google.gson.annotations.SerializedName;

public class SwipeResponse {

    @SerializedName("CardData")
    public String CardData;
    @SerializedName("UserId")
    public String UserId;
    @SerializedName("FirstName")
    public String FirstName;
    @SerializedName("LastName")
    public String LastName;
    @SerializedName("ProfilePicture")
    public String ProfilePicture;
    @SerializedName("Password")
    public String Password;
    @SerializedName("ResponseCode")
    public String ResponseCode;
    @SerializedName("ResponseMessage")
    public String ResponseMessage;

    public SwipeResponse(String CardData) {
        this.CardData = CardData;
    }

    public String getUserId() {
        return UserId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getProfilePicture() {
        return ProfilePicture;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }
}