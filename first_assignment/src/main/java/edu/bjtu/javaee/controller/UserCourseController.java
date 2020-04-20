package edu.bjtu.javaee.controller;

import edu.bjtu.javaee.domain.UserCourse;
import edu.bjtu.javaee.exception.ResourceNotFoundException;
import edu.bjtu.javaee.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserCourseController {

    private final int ROW_PER_PAGE = 8;

    @Autowired
    private UserCourseService usercourseService;

    @GetMapping("/usercourses")
    public String getCourseSelection(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber)
    {
        List<UserCourse> usercourses = usercourseService.findAll(pageNumber, ROW_PER_PAGE);

        long count = usercourseService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("usercourses", usercourses);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);

        return "usercourse-list";
    }

    @GetMapping(value = "/usercourses/{usercourseId}")
    public String getCourseById(Model model, @PathVariable long usercourseId) {
        UserCourse userCourse = null;
        try {
            userCourse = usercourseService.findById(usercourseId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Course not found");
        }
        model.addAttribute("usercourse", userCourse);
        return "usercourse";
    }
}
