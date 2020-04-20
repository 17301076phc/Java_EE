package edu.bjtu.javaee.repository;

import edu.bjtu.javaee.domain.UserCourse;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends PagingAndSortingRepository<UserCourse, Long>,
        JpaSpecificationExecutor<UserCourse> {
}
