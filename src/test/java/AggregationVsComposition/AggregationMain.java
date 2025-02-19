package AggregationVsComposition;

import java.util.ArrayList;
import java.util.List;

public class AggregationMain {

    public static void main(String[] args) {

        // Creating object of Student class inside main()
        Student s1 = new Student("Mia", 1, "CSE");
        Student s2 = new Student("Priya", 2, "CSE");
        Student s3 = new Student("John", 1, "EE");
        Student s4 = new Student("Rahul", 2, "EE");


        // Creating a List of CSE Students
        List<Student> studentsCSE = new ArrayList<>();
        studentsCSE.add(s1);
        studentsCSE.add(s2);

        // Creating a List of EE Students
        List<Student> studentsEE= new ArrayList<>();
        studentsEE.add(s3);
        studentsEE.add(s4);

        //Creating objects of EE and CSE departments
        Department CSE= new Department("CSE", studentsCSE);
        Department EE= new Department("EE", studentsEE);

        //list of departments
        List<Department> departments = new ArrayList<>();
        departments.add(CSE);
        departments.add(EE);

        // Lastly creating an instance of Institute
        Collage collage= new Collage("ASE", departments);


        System.out.print("Total students in institute: ");
        System.out.print(collage.getTotalStudentsInInstitute());

    }
}
