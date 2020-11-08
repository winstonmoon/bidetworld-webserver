package com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.app.register;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.common.MapHelper;
import com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.model.GeoIP;
import com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.service.RawDBDemoGeoIPLocationService;
import com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.service.impl.RegisterServiceImpl;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterCurrentLocationController {
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private RawDBDemoGeoIPLocationService rawDBDemoGeoIPLocationService;
    private RegisterServiceImpl registerServiceImpl;

    public RegisterCurrentLocationController(RawDBDemoGeoIPLocationService rawDBDemoGeoIPLocationService,
            RegisterServiceImpl registerServiceImpl) {
        this.rawDBDemoGeoIPLocationService = rawDBDemoGeoIPLocationService;
        this.registerServiceImpl = registerServiceImpl;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    @ModelAttribute
    public RegisterForm setRegisterForm() {
        return new RegisterForm();
    }

    @RequestMapping(value = "register-current-location")
    public String displayRegisterCurrentLocation(Model model, HttpServletRequest request) {
        // TODO for test
        // String ip = MapHelper.getIP(request);
        String ip = "219.118.201.119";
        GeoIP geoIP = new GeoIP();
        try {
            rawDBDemoGeoIPLocationService.getCityLatLong(geoIP, ip);
            rawDBDemoGeoIPLocationService.getCountry(geoIP, ip);
        } catch (IOException | GeoIp2Exception e) {
            logger.error(ExceptionUtils.getMessage(e));
            logger.error(ExceptionUtils.getStackTrace(e));
            // TODO error handler
        }

        // TODO get near bidet info from country and city
        Map<Double, Double> bidetInfoMap = registerServiceImpl.getSameCityBidetInfo(geoIP.getCity());
        String bidetInfoPin = MapHelper.createBidetInfoPin(bidetInfoMap);

        // TODO: set current location
        model.addAttribute("longLat", new Double[] { geoIP.getLongitude(), geoIP.getLatitude() });
        // TODO set near bidet pin
        model.addAttribute("", bidetInfoPin);

        return "register/register_current_location";
    }
}
