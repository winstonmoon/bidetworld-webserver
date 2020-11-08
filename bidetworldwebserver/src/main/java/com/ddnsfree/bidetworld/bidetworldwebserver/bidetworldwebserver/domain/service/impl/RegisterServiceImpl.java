package com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.service.RegisterService;
import com.maxmind.geoip2.record.City;

import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Override
    public Map<Double, Double> getSameCityBidetInfo(City city) {
        Map<Double, Double> bidetInfoMap = new HashMap<>();
        return bidetInfoMap;
    }
}