package chapter7;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class StudentList {
    private List<Student> students;

    public StudentList(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    // Метод для фильтрации студентов по оценке выше 8.0 c использованием Predicate
    public Predicate<Student> getGradeAboveEightPredicate() {
        return student -> student.getGrade() > 8.0;
    }

    // Метод использования полученного Predicate
    public List<Student> filterStudents(Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    // Метод для получения функции, которая возвращает имя студента
    // с использованием интерфейса Function для трансформации
    public Function<Student, String> getNameFunction() {
        return Student::getName;
    }

    public List<String> transformStudents(Function<Student, String> function) {
        return students.stream().map(function).collect(Collectors.toList());
    }

    // Метод для обработки студента через Consumer
    public Consumer<Student> getPrintStudentConsumer() {
        return System.out::println;
    }

    public void processStudents(Consumer<Student> consumer) {
        students.forEach(consumer);
    }

    // Метод для использования Supplier, который предоставляет список студентов
    public Supplier<List<Student>> getStudentSupplier() {
        return () -> new ArrayList<>(students);
    }

    // Метод для сортировки студентов по имени
    public Comparator<Student> getNameComparator() {
        return Comparator.comparing(Student::getName);
    }

    public List<Student> sortStudents(Comparator<Student> comparator) {
        return students.stream().sorted(comparator).collect(Collectors.toList());
    }

    // Метод, использующий замыкание
    public Predicate<Student> getClosurePredicate(double threshold) {
        return student -> student.getGrade() > threshold;
    }

    public List<Student> filterStudentsWithClosure(double threshold) {
        Predicate<Student> closurePredicate = getClosurePredicate(threshold);
        return students.stream().filter(closurePredicate).collect(Collectors.toList());
    }
}
