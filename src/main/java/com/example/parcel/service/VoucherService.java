package com.example.parcel.service;

import com.example.parcel.model.response.MyntVoucherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class VoucherService {


    private RestTemplate restTemplate;

    @Autowired
    public VoucherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MyntVoucherResponse getDiscount(String voucherCode) throws RestClientException {
        String apiKey = "apikey";
        return restTemplate.getForObject("https://mynt-exam.mocklab.io/voucher/{voucherCode}?key="+ apiKey,
                        MyntVoucherResponse.class,
                        voucherCode);
    }

    public double getDiscountValue(double cost, String voucherCode) throws RestClientException {
        float discount = getDiscount(voucherCode).getDiscount();
        return cost - (cost * (discount / 100));
    }
}
