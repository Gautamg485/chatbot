package com.example.servingwebcontent.Controller;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatbotController {
    @GetMapping("/chatbot")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model) {
        System.out.println("asdfsadas");
        model.addAttribute("name", name);
        return "chatbotui";
    }

}