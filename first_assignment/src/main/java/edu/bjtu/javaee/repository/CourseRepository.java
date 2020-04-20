package edu.bjtu.javaee.repository;

import edu.bjtu.javaee.domain.Course;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long>,
        JpaSpecificationExecutor<Course> {
}
