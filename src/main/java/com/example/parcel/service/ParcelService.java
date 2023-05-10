package com.example.parcel.service;

import com.example.parcel.model.IParcel;
import com.example.parcel.model.ParcelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.text.DecimalFormat;

@Service
public class ParcelService {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    //df.format(input)

    private final ParcelFactory parcelFactory;
    private final VoucherService voucherService;

    @Autowired
    public ParcelService(ParcelFactory parcelFactory,
                         VoucherService voucherService){
        this.parcelFactory = parcelFactory;
        this.voucherService = voucherService;
    }
    public String getCost(String weightStr, String heightStr, String widthStr, String lengthStr,
                          String voucherCode) {
        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);
            float width = Float.parseFloat(widthStr);
            float length = Float.parseFloat(lengthStr);

            if (weight <= 0 || height <= 0 || width <= 0 || length <= 0) {
                return "Values should be greater than 0. ";
            }

            IParcel parcel = parcelFactory.getParcel("SIMPLE", weight, height, width, length);
            double cost = parcel.getCost();

            if(cost < 0) {  // -1 returned by calculation
                return "Weight Rejected. Exceeds maximum limit. ";
            }

            double discountedValue = voucherService.getDiscountValue(cost, voucherCode);
            return "PHP " + df.format(discountedValue);
        } catch (NumberFormatException nfe) {
            return "Invalid numerical input.  Please check that the entered values are correct. ";
        } catch (RestClientException re) {
            return "Problem encountered when calling voucher api. Ensure you have a correct voucher code. ";
        }
    }
}
