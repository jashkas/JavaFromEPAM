package chapter7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private List<Student> students;

    @BeforeEach
    void setUp() {
        // Инициализация списка студентов перед каждым тестом
        students = Arrays.asList(
                new Student("Анна", 20, 8.5),
                new Student("Борис", 22, 9.0),
                new Student("Виктор", 19, 7.8),
                new Student("Диана", 21, 8.3)
        );
    }

    @AfterEach
    void tearDown() {
        // Очистка ресурсов или сброс состояний, если необходимо (в данном примере не требуется)
        students = null;
    }

    @Test
    void testGettersAndSetters() {
        Student student = new Student("Алексей", 23, 7.5);

        // Тестируем геттеры
        assertEquals("Алексей", student.getName(), "Геттер имени не сработал");
        assertEquals(23, student.getAge(), "Геттер возраста не сработал");
        assertEquals(7.5, student.getGrade(), 0.01, "Геттер оценки не сработал");

        // Тестируем сеттеры
        student.setName("Мария");
        student.setAge(24);
        student.setGrade(8.0);

        assertEquals("Мария", student.getName(), "Ошибка при установке имени");
        assertEquals(24, student.getAge(), "Ошибка при установке возраста");
        assertEquals(8.0, student.getGrade(), 0.01, "Ошибка при установке оценки");
    }

    @Test
    void testFilterStudents() {
        Predicate<Student> gradeAboveEight = student -> student.getGrade() > 8.0;
        List<Student> filteredStudents = Student.filterStudents(students, gradeAboveEight);

        // Проверка, что размер отфильтрованного списка соответствует ожиданиям
        assertEquals(3, filteredStudents.size(), "Должно быть три студента с оценкой выше 8.0");

        // Проверка содержания отфильтрованного списка
        assertTrue(
                filteredStudents.contains(new Student("Анна", 20, 8.5)) &&
                        filteredStudents.contains(new Student("Борис", 22, 9.0)) &&
                        filteredStudents.contains(new Student("Диана", 21, 8.3))
        );
    }

    @Test
    void testMapStudentNames() {
        Function<Student, String> getNameFunction = Student::getName;
        List<String> studentNames = Student.mapStudentNames(students, getNameFunction);

        // Проверка, что список имен студентов совпадает с ожиданиями
        List<String> expectedNames = Arrays.asList("Анна", "Борис", "Виктор", "Диана");
        assertEquals(expectedNames, studentNames, "Имена студентов должны совпадать с ожиданием");
    }

    @Test
    void testConsumeStudents() {
        // проверка использования Consumer
        List<String> collectedNames = new ArrayList<>();
        Consumer<Student> collectNames = student -> collectedNames.add(student.getName());

        Student.consumeStudents(students, collectNames);

        // Ожидание, что собрано правильное количество имен
        assertEquals(4, collectedNames.size(), "Должно быть собрано 4 имени");
    }

    @Test
    void testSupplyStudents() {
        Supplier<List<Student>> studentSupplier = () -> new ArrayList<>(students);
        List<Student> suppliedStudents = Student.supplyStudents(studentSupplier);

        assertEquals(students.size(), suppliedStudents.size(), "Размер поставленного списка студентов должен совпадать с исходным");
    }

    @Test
    void testSortStudentsByName() {
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
        List<Student> sortedStudents = Student.sortStudentsByName(students, nameComparator);

        // Проверка, что студенты отсортированы по именам
        List<String> sortedNames = Arrays.asList("Анна", "Борис", "Виктор", "Диана");
        for (int i = 0; i < sortedStudents.size(); i++) {
            assertEquals(sortedNames.get(i), sortedStudents.get(i).getName(), "Имена должны быть отсортированы");
        }
    }

    @Test
    void testExecuteCustomFunction() {
        // Перенаправляем System.out на наш ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Student.CustomFunctionalInterface customFunction = message -> System.out.println("Полученное сообщение: " + message);
        Student.executeCustomFunction(customFunction, "тест");

        // Проверка, что правильное сообщение вывелось
        assertEquals("Полученное сообщение: тест\r\n", outContent.toString());

        System.setOut(System.out);
    }

    @Test
    void testGradeCheck() {
        Student anna = new Student("Анна", 20, 9.5);
        Student boris = new Student("Борис", 22, 10.0);
        Student victor = new Student("Виктор", 19, 7.8);

        // Создаём экземпляр анонимного класса, реализующего GradeCheck, чтобы получить доступ к методу по умолчанию
        Student.GradeCheck checker = new Student.GradeCheck(){};

        assertTrue(checker.isAboveThreshold(anna), "Оценка Анны должна быть выше пороговой");
        assertTrue(Student.GradeCheck.isExcellent(boris), "Оценка Бориса должна быть отличной");
        assertFalse(checker.isAboveThreshold(victor), "Оценка Виктора не должна быть выше пороговой");
        assertFalse(Student.GradeCheck.isExcellent(victor), "Оценка Виктора не должна быть отличной");
    }
}
