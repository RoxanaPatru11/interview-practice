package AggregationVsComposition;

import lombok.Getter;

import java.util.List;

public class Collage {

    String instituteName;
    @Getter
    private List<Department> departments;

    Collage(String instituteName,List<Department> departments) {
        this.instituteName = instituteName;
        this.departments = departments;
    }

    public int getTotalStudentsInInstitute()
    {
        int noOfStudents = 0;
        List<Student> students;

        for (Department dept : departments) {
            students = dept.getStudents();

            for (Student s : students) {
                noOfStudents++;
            }
        }

        return noOfStudents;
    }
}
