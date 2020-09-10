package com.bucares.fuel.controller;

import com.bucares.fuel.service.choferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static com.bucares.fuel.URLConstants.APP_VERSION_URL;
import static com.bucares.fuel.URLConstants.HEALTH_ENDPOINT_URL;

public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private choferService choferService;

    @Value("${build.version}")
    private String buildVersion;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.addObject("chofer", choferService.getAllChofer());
        model.setViewName("index");
        return model;
    }

    @GetMapping(HEALTH_ENDPOINT_URL)
    public String health() {
        return "redirect:/";
    }

    @GetMapping(APP_VERSION_URL)
    @ResponseBody
    public ResponseEntity<Map<String, String>> getVersion() {
        logger.info("Called resource: getVersion");
        logger.info("Project version {}", buildVersion);

        Map<String, String> response = new HashMap<>();
        response.put("version", buildVersion);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
