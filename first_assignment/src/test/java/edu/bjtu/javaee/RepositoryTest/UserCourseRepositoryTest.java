package edu.bjtu.javaee.RepositoryTest;

import edu.bjtu.javaee.domain.UserCourse;
import edu.bjtu.javaee.repository.UserCourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserCourseRepositoryTest {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Test
    public void testQuery() {
        List<UserCourse> userCourses = (List<UserCourse>) userCourseRepository.findAll();
        assertThat(userCourses).hasSizeGreaterThan(1);
        for(UserCourse userCourse:userCourses)
            System.out.println(userCourse.toString());

    }

}
