package com.legal_system.mediation.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legal_system.mediation.Service.UserDetailsService;



@Controller
@RequestMapping("/admin")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @GetMapping("/user-details")
    public String getUserDetailsTable(Model model){
        model.addAttribute("users",userDetailsService.getuserDetails());
        return "Admin/Users/user_details";
    }
}
