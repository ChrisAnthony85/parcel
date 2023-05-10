package com.example.parcel.service;

import com.example.parcel.model.response.MyntVoucherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ParcelServiceTest {
    @MockBean
    VoucherService voucherService;

    @Autowired
    ParcelService parcelService;

    @Test
    void getCostShouldNotAcceptZeroes() {
        String result = parcelService.getCost("0","1", "1","1","TEST");
        assertEquals("Values should be greater than 0. ", result);
    }

    @Test
    void getCostShouldRejectOverWeightLimit() {
        String result = parcelService.getCost("51","1", "1","1","TEST");
        assertEquals("Weight Rejected. Exceeds maximum limit. ", result);
    }

    @Test
    void getCostShouldReturnCorrectCost() {
        when(voucherService.getDiscountValue(anyDouble(), anyString())).thenReturn(10.0);
        String result = parcelService.getCost("50","1", "1","1","TEST");
        assertEquals("PHP 10.00", result);
    }

    @Test
    void getCostShouldReturnErrorMessageIfNonNumericInput() {
        when(voucherService.getDiscountValue(anyDouble(), anyString())).thenReturn(10.0);
        String result = parcelService.getCost("abcde","1", "1","1","TEST");
        assertEquals("Invalid numerical input.  Please check that the entered values are correct. ", result);
    }

    @Test
    void getCostShouldErrorMessageIfVoucherApiFails() {
        when(voucherService.getDiscountValue(anyDouble(), anyString()))
                .thenThrow(new WebClientResponseException(400, "Bad Request", null, null, null));
        String result = parcelService.getCost("50","1", "1","1","TEST");
        assertEquals("Problem encountered when calling voucher api. Ensure you have a correct voucher code. ", result);
    }
}