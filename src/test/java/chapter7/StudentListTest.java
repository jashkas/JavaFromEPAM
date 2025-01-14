package chapter7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class StudentListTest {
    private StudentList studentList;
    private List<Student> students;

    @BeforeEach
    public void setUp() {
        students = Arrays.asList(
                new Student("Анна", 20, 8.5),
                new Student("Борис", 22, 9.0),
                new Student("Диана", 21, 8.3),
                new Student("Елена", 19, 7.5)
        );
        studentList = new StudentList(students);
    }

    @Test
    public void testFilterStudents() {
        List<Student> result = studentList.filterStudents(studentList.getGradeAboveEightPredicate());
        assertEquals(3, result.size());
        assertTrue(result.containsAll(Arrays.asList(
                new Student("Анна", 20, 8.5),
                new Student("Борис", 22, 9.0),
                new Student("Диана", 21, 8.3)
        )));
    }

    @Test
    public void testTransformStudents() {
        List<String> names = studentList.transformStudents(studentList.getNameFunction());
        assertEquals(Arrays.asList("Анна", "Борис", "Диана", "Елена"), names);
    }

    @Test
    public void testProcessStudents() {
        // Проверяем, что Consumer не выбрасывает исключения
        studentList.processStudents(studentList.getPrintStudentConsumer());
    }

    @Test
    public void testStudentSupplier() {
        List<Student> suppliedStudents = studentList.getStudentSupplier().get();
        assertEquals(students, suppliedStudents);
    }

    @Test
    public void testSortStudents() {
        List<Student> sortedStudents = studentList.sortStudents(studentList.getNameComparator());
        List<Student> expectedOrder = Arrays.asList(
                new Student("Анна", 20, 8.5),
                new Student("Борис", 22, 9.0),
                new Student("Диана", 21, 8.3),
                new Student("Елена", 19, 7.5)
        );
        assertEquals(expectedOrder, sortedStudents);
    }

    @Test
    public void testFilterStudentsWithClosure() {
        double threshold = 8.0;
        List<Student> result = studentList.filterStudentsWithClosure(threshold);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(Arrays.asList(
                new Student("Анна", 20, 8.5),
                new Student("Борис", 22, 9.0),
                new Student("Диана", 21, 8.3)
        )));
    }
}
