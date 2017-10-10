package com.example.rikharthu.myparameters;

public class BodyParams {

    public String gender;
    public String bodySize;
    public float footSize;

    public BodyParams() {
    }

    public BodyParams(String gender, String bodySize, float footSize) {
        this.gender = gender;
        this.bodySize = bodySize;
        this.footSize = footSize;
    }

    @Override
    public String toString() {
        return "BodyParams{" +
                "gender='" + gender + '\'' +
                ", bodySize='" + bodySize + '\'' +
                ", footSize=" + footSize +
                '}';
    }
}
