package com.example.parcel.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SimpleParcelTest {

    @Test
    void getCostShouldReturnNegativeWhenWeightIsOverLimit() {
        SimpleParcel simpleParcel = new SimpleParcel(51, 1 ,1 ,1);
        assertEquals(-1, simpleParcel.getCost());
    }

    @Test
    void getCostShouldReturnExpectedWhenWeightIsHeavy() {
        SimpleParcel simpleParcel = new SimpleParcel(50, 1 ,1 ,1);
        assertEquals(1000, simpleParcel.getCost());
    }

    @Test
    void getCostShouldReturnSmallVolumeCostWhenVolumeIsSmall() {
        SimpleParcel simpleParcel = new SimpleParcel(10, 10 ,10 ,10);
        assertEquals(30, simpleParcel.getCost());
    }

    @Test
    void getCostShouldReturnMediumVolumeCostWhenVolumeIsMedium() {
        SimpleParcel simpleParcel = new SimpleParcel(10, 24 ,10 ,10);
        assertEquals(96, simpleParcel.getCost());
    }

    @Test
    void getCostShouldReturnLargeVolumeCostWhenVolumeIsLarge() {
        SimpleParcel simpleParcel = new SimpleParcel(10, 25 ,10 ,10);
        assertEquals(125, simpleParcel.getCost());
    }
}