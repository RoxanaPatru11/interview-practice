package AggregationVsComposition;

import lombok.Getter;

import java.util.List;

public class Department {
    String name;
    @Getter
    private List<Student> students;

    Department(String name, List<Student> students)
    {
        this.name = name;
        this.students = students;
    }
}
