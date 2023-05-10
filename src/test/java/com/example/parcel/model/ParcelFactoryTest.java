package com.example.parcel.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ParcelFactoryTest {

    @Autowired
    private ParcelFactory parcelFactory;

    @Test
    void simpleShouldReturnSimpleParcel() {
        IParcel parcel = parcelFactory.getParcel("SIMPLE", 1,1,1,1);
        assertTrue(parcel instanceof SimpleParcel);
    }

    @Test
    void stringInputShouldReturnSimpleParcel() {
        IParcel parcel = parcelFactory.getParcel("ANY", 1,1,1,1);
        assertTrue(parcel instanceof SimpleParcel);
    }

    @Test
    void shouldReturnNull() {
        IParcel parcel = parcelFactory.getParcel(null, 1,1,1,1);
        assertNull(parcel);
    }
}