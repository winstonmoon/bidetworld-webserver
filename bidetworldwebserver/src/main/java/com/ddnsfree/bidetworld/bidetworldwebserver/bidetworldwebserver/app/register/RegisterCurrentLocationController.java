package com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.app.register;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javax.servlet.http.HttpServletRequest;

import com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.common.MapHelper;
import com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.model.GeoIP;
import com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.domain.service.RawDBDemoGeoIPLocationService;
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

    public RegisterCurrentLocationController(RawDBDemoGeoIPLocationService rawDBDemoGeoIPLocationService) {
        this.rawDBDemoGeoIPLocationService = rawDBDemoGeoIPLocationService;
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
        String ip = MapHelper.getIP(request);
        GeoIP geoIP = new GeoIP();
        try {
            geoIP = rawDBDemoGeoIPLocationService.getLocation(ip);
        } catch (IOException | GeoIp2Exception e) {
            logger.error(ExceptionUtils.getMessage(e));
            logger.error(ExceptionUtils.getStackTrace(e));
            // TODO error handle
        }

        // TODO get near bidet info from country and city

        // TODO create bidet info pin by MapHelper

        // TODO: set current location
        model.addAttribute("", geoIP.getLatitude());
        model.addAttribute("", geoIP.getLongitude());

        // TODO set near bidet pin

        return "register/register_current_location";
    }
}
