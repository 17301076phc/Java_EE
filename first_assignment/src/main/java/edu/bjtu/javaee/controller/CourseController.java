package edu.bjtu.javaee.controller;

import edu.bjtu.javaee.domain.Course;
import edu.bjtu.javaee.exception.ResourceNotFoundException;
import edu.bjtu.javaee.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CourseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private CourseService courseService;

    @Value("${msg.title}")
    private String title;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", title);
        return "index";
    }

    @GetMapping(value = "/courses")
    public String getCourses(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Course> courses = courseService.findAll(pageNumber, ROW_PER_PAGE);

        long count = courseService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("courses", courses);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "course-list";
    }

    @GetMapping(value = "/courses/{courseId}")
    public String getCourseById(Model model, @PathVariable long courseId) {
        Course course = null;
        try {
            course = courseService.findById(courseId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Course not found");
        }
        model.addAttribute("course", course);
        return "course";
    }

    @GetMapping(value = {"/courses/add"})
    public String showAddCourse(Model model) {
        Course course = new Course();
        model.addAttribute("add", true);
        model.addAttribute("course", course);

        return "course-edit";
    }

    @PostMapping(value = "/courses/add")
    public String addCourse(Model model, @ModelAttribute("course") Course course) {
        try {
            Course newCourse = courseService.save(course);
            return "redirect:/courses/" + String.valueOf(newCourse.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            //model.addAttribute("contact", contact);
            model.addAttribute("add", true);
            return "course-edit";
        }
    }

    @GetMapping(value = {"/courses/{courseId}/edit"})
    public String showEditCourse(Model model, @PathVariable long courseId) {
        Course course = null;
        try {
            course = courseService.findById(courseId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Course not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("course", course);
        return "course-edit";
    }

    @PostMapping(value = {"/courses/{courseId}/edit"})
    public String updateCourse(Model model, @PathVariable long courseId, @ModelAttribute("course") Course course) {
        try {
            course.setId(courseId);
            courseService.update(course);
            return "redirect:/courses/" + String.valueOf(course.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "course-edit";
        }
    }

    @GetMapping(value = {"/courses/{courseId}/delete"})
    public String showDeleteCourseById( Model model, @PathVariable long courseId) {
        Course course = null;
        try {
            course = courseService.findById(courseId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Course not found");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("course", course);
        return "course";
    }

    @PostMapping(value = {"/courses/{courseId}/delete"})
    public String deleteCourseById(Model model, @PathVariable long courseId) {
        try {
            courseService.deleteById(courseId);
            return "redirect:/courses";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "course";
        }
    }
}
