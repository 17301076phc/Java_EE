package edu.bjtu.javaee.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher {
    private int teacherId;
    private String teacherName;

    public Teacher(int teacherId,String teacherName)
    {
        this.teacherId=teacherId;
        this.teacherName=teacherName;
    }
}
