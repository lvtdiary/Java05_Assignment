package com.example.assignment01.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
    @GetMapping("/dash-board")
    public String showAdminDashboard() {
        return "dashboard/layout-admin";
    }
}
