package com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.model.GeoIP;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class RawDBDemoGeoIPLocationService {
  private DatabaseReader dbReader;

  public GeoIP getCityLatLong(GeoIP geoIP, String ip) throws IOException, GeoIp2Exception {
    File cityDbFile = ResourceUtils.getFile("classpath:GeoLite2-City.mmdb");
    dbReader = new DatabaseReader.Builder(cityDbFile).build();
    InetAddress ipAddress = InetAddress.getByName(ip);
    CityResponse cityResponse = dbReader.city(ipAddress);

    geoIP.setCity(cityResponse.getCity());
    geoIP.setLatitude(cityResponse.getLocation().getLatitude());
    geoIP.setLongitude(cityResponse.getLocation().getLongitude());
    return geoIP;
  }

  public GeoIP getCountry(GeoIP geoIP, String ip) throws IOException, GeoIp2Exception {
    File countryDbFile = ResourceUtils.getFile("classpath:GeoLite2-Country.mmdb");
    dbReader = new DatabaseReader.Builder(countryDbFile).build();
    InetAddress ipAddress = InetAddress.getByName(ip);
    CountryResponse countryResponse = dbReader.country(ipAddress);

    geoIP.setCountry(countryResponse.getCountry());
    return geoIP;
  }
}
