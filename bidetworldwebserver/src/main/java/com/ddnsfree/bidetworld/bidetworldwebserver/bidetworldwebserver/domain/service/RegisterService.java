package com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.service;

import java.util.Map;

import com.maxmind.geoip2.record.City;

public interface RegisterService {

    Map<Double, Double> getSameCityBidetInfo(City city);
}