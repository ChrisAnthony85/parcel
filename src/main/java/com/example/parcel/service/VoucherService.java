package com.example.parcel.service;

import com.example.parcel.model.response.MyntVoucherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VoucherService {


    private final WebClient webClient;

    @Value("${voucher.url}")
    private String baseUrl;

    @Value("${voucher.key}")
    private String apiKey;

    @Autowired
    public VoucherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public MyntVoucherResponse getDiscount(String voucherCode) throws WebClientResponseException {
        return webClient.get().uri(UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/voucher/{voucherCode}")
                .queryParam("key", apiKey).build(voucherCode))
                .retrieve().bodyToMono(MyntVoucherResponse.class).block();
    }

    public double getDiscountValue(double cost, String voucherCode) throws WebClientResponseException {
        float discount = getDiscount(voucherCode).getDiscount();
        return cost - (cost * (discount / 100));
    }
}
