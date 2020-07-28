package org.tensorflow.lite.examples.detection;

import java.util.ArrayList;

public class InitData {
    private String result;
    private int error;
    private ArrayList<MyData> data;

    public String getResult() {
        return result;
    }

    public int getError() {
        return error;
    }

    public ArrayList<MyData> getData() {
        return data;
    }
}
