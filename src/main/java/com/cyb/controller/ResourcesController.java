package com.cyb.controller;

import com.cyb.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    private ResourcesService resourcesService;

}
