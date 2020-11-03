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

  public RawDBDemoGeoIPLocationService() throws IOException {
    //File database = new File("your-mmdb-location");
    // TODO fix
    File cityDbFile = ResourceUtils.getFile("classpath:GeoLite2-City.mmdb");
    dbReader = new DatabaseReader.Builder(cityDbFile).build();
  }

  public GeoIP getLocation(String ip) throws IOException, GeoIp2Exception {
    GeoIP geoIP = new GeoIP();
    InetAddress ipAddress = InetAddress.getByName(ip);
    CityResponse cityResponse = dbReader.city(ipAddress);
    CountryResponse countryResponse = dbReader.country(ipAddress);

    geoIP.setCountry(countryResponse.getCountry().toString());
    geoIP.setCity(cityResponse.getCity().toString());
    geoIP.setLatitude(cityResponse.getLocation().getLatitude().toString());
    geoIP.setLongitude(cityResponse.getLocation().getLongitude().toString());
    return geoIP;
  }
} 
