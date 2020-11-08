package com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.model;

import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;

public class GeoIP {
    private Country country;
    private City city;
    private Double latitude;
    private Double longitude;

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
