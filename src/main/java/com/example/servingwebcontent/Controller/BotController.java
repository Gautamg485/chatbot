package com.example.servingwebcontent.Controller;

import org.alicebot.ab.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
public class BotController {
    private static final boolean TRACE_MODE = false;
    static String botName = "gowtham";

    @RequestMapping("/receive")
    public @ResponseBody ResponseEntity<String> receive(@RequestParam("chat") String chat) {
        String chatresponse = this.chatbotAnswer(chat);
        System.out.println("Dsadasdas");
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatresponse);
    }
//    public String receive(HttpServletRequest request, Model model) {
////        System.out.println(request.getParameter("chat"));
//        String chatresponse = this.chatbotAnswer(request.getParameter("chat"));
////        model.addAttribute("chatresponse", chatresponse);
//        return chatresponse;
//    }

    public String chatbotAnswer(String textLine) {
        String response = "";
        try {
            String resourcesPath = getResourcesPath();
            Bot bot = new Bot("gowtham", resourcesPath);
            Chat chatSession = new Chat(bot);
            String request = textLine;
            if (MagicBooleans.trace_mode)
                System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
            response = chatSession.multisentenceRespond(request);
            System.out.println("Robot Raw: " + response);
            while (response.contains("&lt;"))
                response = response.replace("&lt;", "<");
            while (response.contains("&gt;"))
                response = response.replace("&gt;", ">");
            if (response.contains("button"))
                response = response.replace("button", "button class='option'");
            System.out.println("Robot : " + response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String chatbotUtils(String textLine) {

        String response = "";
        try {
            String resourcesPath = getResourcesPath();
            System.out.println(resourcesPath);
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot bot = new Bot("gowtham", resourcesPath);
            Chat chatSession = new Chat(bot);
//            bot.brain.nodeStats();
//            String textLine = "";
            while(true) {
                if ((textLine == null) || (textLine.length() < 1))
                    textLine = MagicStrings.null_input;
                if (textLine.equals("q")) {
                } else if (textLine.equals("wq")) {
                } else {
                    String request = textLine;
                    if (MagicBooleans.trace_mode)
                        System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                    response = chatSession.multisentenceRespond(request);
                    System.out.println("Robot Raw: " + response);
                    while (response.contains("&lt;"))
                        response = response.replace("&lt;", "<");
                    while (response.contains("&gt;"))
                        response = response.replace("&gt;", ">");
                    System.out.println("Robot : " + response);
                    return response;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }
}
