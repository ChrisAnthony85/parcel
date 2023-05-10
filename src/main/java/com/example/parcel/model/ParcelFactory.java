package com.example.parcel.model;

import org.springframework.stereotype.Component;

@Component
public class ParcelFactory {
    public IParcel getParcel(String parcelType, float weight, float height, float width, float length) {
        if(parcelType == null) {
            return null;
        }
        //if("SIMPLE".equalsIgnoreCase(parcelType.trim()))
        //we only have one parcel type and calculation for now
        return new SimpleParcel(weight, height, width, length);
    }
}
