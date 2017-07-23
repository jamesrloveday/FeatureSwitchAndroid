package features.application.com.featureswitchandroid;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {

    @SerializedName("featuresList")
    @Expose
    private List<String> featuresList = null;

    public List<String> getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(List<String> featuresList) {
        this.featuresList = featuresList;
    }

}
