package com.snapmine.SnapMineApi.api;


import com.snapmine.SnapMineApi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/login")
public class LoginController {

    final
    SecurityService securityService;

    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping(value = "/test", method = POST)
    public String test(@RequestBody String text){
        return this.securityService.test(text);
    }

}
