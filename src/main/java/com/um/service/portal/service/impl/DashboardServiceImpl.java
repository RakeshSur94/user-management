package com.um.service.portal.service.impl;

import com.um.service.portal.dto.DashboardResponse;
import com.um.service.portal.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class DashboardServiceImpl implements DashBoardService {

    private final static String QUOTE_API_URL = "https://dummyjson.com/quotes/random";


    @Override
    public DashboardResponse getQuote() {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<DashboardResponse> forEntity = rt.getForEntity(QUOTE_API_URL, DashboardResponse.class);
        return forEntity.getBody();
    }
}
