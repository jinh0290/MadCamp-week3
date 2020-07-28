package org.tensorflow.lite.examples.detection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyData {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("feature")
    @Expose
    private float[][] feature;

    public MyData(String name, float[][] feature) {
        this.name = name;
        this.feature = feature;
    }

    public String getName() {
        return name;
    }

    public float[][] getFeature() {
        return feature;
    }
}

