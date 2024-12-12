package chapter7;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public double getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{имя='" + name + "', возраст=" + age + ", оценка=" + grade + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return age == student.age &&
                Double.compare(student.grade, grade) == 0 &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, grade);
    }

    // Интерфейс для проверки оценки содержит Default и static методы
    interface GradeCheck {
        default boolean isAboveThreshold(Student student) {
            return student.getGrade() > 8.0;
        }

        static boolean isExcellent(Student student) {
            return student.getGrade() == 10.0;
        }
    }

    // Метод использования Predicate
    public static List<Student> filterStudents(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    // Метод использования Function
    public static List<String> mapStudentNames(List<Student> students, Function<Student, String> function) {
        return students.stream().map(function).collect(Collectors.toList());
    }

    // Метод использования Consumer
    public static void consumeStudents(List<Student> students, Consumer<Student> consumer) {
        students.forEach(consumer);
    }

    // Метод использования Supplier
    public static List<Student> supplyStudents(Supplier<List<Student>> supplier) {
        return supplier.get();
    }

    // Метод использования Comparator
    public static List<Student> sortStudentsByName(List<Student> students, Comparator<Student> comparator) {
        return students.stream().sorted(comparator).collect(Collectors.toList());
    }

    // Метод с использованием функционального интерфейса
    @FunctionalInterface
    public interface CustomFunctionalInterface {
        void execute(String message);
    }

    public static void executeCustomFunction(CustomFunctionalInterface function, String message) {
        function.execute(message);
    }
}
