package com.example.parcel.model;

public class SimpleParcel implements IParcel{

    private final float weight;
    private final double volume;


    public SimpleParcel(float weight, float height, float width, float length) {
        this.weight = weight;
        this.volume = height * width * length;
    }


    @Override
    public double getCost() {
        if (weight > 50) {
            System.out.println("Overweight!");
            return -1;
        }
        if (weight > 10) {
            System.out.println("Heavy parcel. ");
            return 20 * weight;
        }
        if (volume < 1500) {
            System.out.println("Small parcel. ");
            return 0.03 * volume;
        }
        if (volume < 2500) {
            System.out.println("Medium parcel. ");
            return 0.04 * volume;
        }

        System.out.println("Large Parcel. ");
        return 0.05 * volume;
    }
}
