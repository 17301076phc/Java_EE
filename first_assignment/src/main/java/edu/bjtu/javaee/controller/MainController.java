package edu.bjtu.javaee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

  /*  @GetMapping("/")
    public String root() {
        return "index";
    }*/

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "loginpage";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "index";
    }

    @GetMapping("/teacher")
    public String teacherIndex() {
        return "teacher";
    }

    @GetMapping("/student")
    public String studentIndex() {
        return "student";
    }

    @GetMapping("/invalidsession")
    public String invalidsession() {
        return "invalidsession";
    }
}
