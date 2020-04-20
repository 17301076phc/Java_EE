/**
 * Spring Boot + Thymeleaf Example (https://www.dariawan.com)
 * Copyright (C) 2019 Dariawan <hello@dariawan.com>
 *
 * Creative Commons Attribution-ShareAlike 4.0 International License
 *
 * Under this license, you are free to:
 * # Share - copy and redistribute the material in any medium or format
 * # Adapt - remix, transform, and build upon the material for any purpose,
 *   even commercially.
 *
 * The licensor cannot revoke these freedoms
 * as long as you follow the license terms.
 *
 * License terms:
 * # Attribution - You must give appropriate credit, provide a link to the
 *   license, and indicate if changes were made. You may do so in any
 *   reasonable manner, but not in any way that suggests the licensor
 *   endorses you or your use.
 * # ShareAlike - If you remix, transform, or build upon the material, you must
 *   distribute your contributions under the same license as the original.
 * # No additional restrictions - You may not apply legal terms or
 *   technological measures that legally restrict others from doing anything the
 *   license permits.
 *
 * Notices:
 * # You do not have to comply with the license for elements of the material in
 *   the public domain or where your use is permitted by an applicable exception
 *   or limitation.
 * # No warranties are given. The license may not give you all of
 *   the permissions necessary for your intended use. For example, other rights
 *   such as publicity, privacy, or moral rights may limit how you use
 *   the material.
 *
 * You may obtain a copy of the License at
 *   https://creativecommons.org/licenses/by-sa/4.0/
 *   https://creativecommons.org/licenses/by-sa/4.0/legalcode
 */
package edu.bjtu.javaee.service;

import edu.bjtu.javaee.domain.Course;
import edu.bjtu.javaee.exception.BadResourceException;
import edu.bjtu.javaee.exception.ResourceAlreadyExistsException;
import edu.bjtu.javaee.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.bjtu.javaee.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    private boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }
    
    public Course findById(Long id) throws ResourceNotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if (course==null) {
            throw new ResourceNotFoundException("Cannot find Course with id: " + id);
        }
        else return course;
    }
    
    public List<Course> findAll(int pageNumber, int rowPerPage) {
        List<Course> courses = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, 
                Sort.by("id").ascending());
        courseRepository.findAll(sortedByIdAsc).forEach(courses::add);
        return courses;
    }
    
    public Course save(Course course) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(course.getName())) {
            if (course.getId() != null && existsById(course.getId())) {
                throw new ResourceAlreadyExistsException("Course with id: " + course.getId() + " already exists");
            }
            return courseRepository.save(course);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save course");
            exc.addErrorMessage("Course is null or empty");
            throw exc;
        }
    }
    
    public void update(Course course)
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(course.getName())) {
            if (!existsById(course.getId())) {
                throw new ResourceNotFoundException("Cannot find Course with id: " + course.getId());
            }
            courseRepository.save(course);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save course");
            exc.addErrorMessage("Course is null or empty");
            throw exc;
        }
    }
    
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) { 
            throw new ResourceNotFoundException("Cannot find course with id: " + id);
        }
        else {
            courseRepository.deleteById(id);
        }
    }
    
    public Long count() {
        return courseRepository.count();
    }
}
