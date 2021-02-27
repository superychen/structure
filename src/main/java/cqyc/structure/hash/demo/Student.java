package cqyc.structure.hash.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author cqyc
 * @Description:
 * @date 2021/2/22
 */
public class Student {

    int grade;
    int cls;
    String firstName;
    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int B = 31;

        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return grade == student.grade &&
                cls == student.cls &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName);
    }

    public static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer) a).hashCode());
        int b = -42;
        System.out.println(((Integer) a).hashCode());
        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());
        String d = "cqyc";
        System.out.println(d.hashCode());
        Student student = new Student(3, 2, "bobo", "Liu");
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);
        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);
    }
}
