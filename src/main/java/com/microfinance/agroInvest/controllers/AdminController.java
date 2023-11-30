package com.microfinance.agroInvest.controllers;

import com.microfinance.agroInvest.model.Admin;
import com.microfinance.agroInvest.services.AdminServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("Admin")
public class AdminController {

    private AdminServiceImpl adminService;

    @GetMapping("/connexion")
    private Admin connexion(@RequestParam("email")String email, @RequestParam("password")String password) throws Exception {
        return adminService.connexion(email, password);

    }

    @GetMapping("/affichertout")
    private List<Admin> afficherTout(){ return adminService.affichertout();
    }

}
