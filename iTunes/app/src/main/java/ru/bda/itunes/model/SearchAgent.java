package ru.bda.itunes.model;

/**
 * Created by User on 16.09.2016.
 */
public class SearchAgent {
    private String mName;
    private String mDescription;
    private String mUrlImage;

    public SearchAgent() {}

    public SearchAgent(String mName, String mDescription, String mUrlImage) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mUrlImage = mUrlImage;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getUrlImage() {
        return mUrlImage;
    }

    public void setUrlImage(String mUrlImage) {
        this.mUrlImage = mUrlImage;
    }

    @Override
    public String toString() {
        return "SearchAgent{" +
                "mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mUrlImage='" + mUrlImage + '\'' +
                '}';
    }
}
