package com.microfinance.agroInvest.controllers;

import com.microfinance.agroInvest.services.ForumServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forum")
@AllArgsConstructor
public class ForumController {
    @Autowired
    private ForumServiceImpl forumService;

}
