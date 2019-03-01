package ru.job4j.school;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SchoolTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 25.02.2019
 */
public class SchoolTest {

    private List<Student> students;
    private School school;
    private static final int FIFTY = 50;
    private static final int SEVENTY = 70;

    @Before
    public void createStudentsList() {
       this.students = Arrays.asList(new Student("John", 10),
                                    new Student("Ivan", 100),
                                    new Student("Darya", 60),
                                    new Student("Ekaterina", 30),
                                    new Student( "Eugene", 80),
                                    new Student("Michail", 65)
                                    );
       this.school = new School();
    }

    /**
     * tests collect when class A is needed.
     */
    @Test
    public void shouldBe2StudentsInClassA() {
        List<Student> expected = Arrays.asList(this.students.get(1), this.students.get(4));
        assertThat(this.school.collect(this.students, student -> student.getScore() > SEVENTY),
                is(expected));
    }

    /**
     * tests collect when class B is needed.
     */
    @Test
    public void shouldBe2StudentsInClassB() {
        List<Student> expected = Arrays.asList(this.students.get(2), this.students.get(5));
        assertThat(this.school.collect(this.students, student -> student.getScore() > FIFTY && student.getScore() <= SEVENTY),
                    is(expected));
    }

    /**
     * tests collect when class C is needed.
     */
    @Test
    public void shouldBe2StudentsInClassC() {
        List<Student> expected = Arrays.asList(this.students.get(0), this.students.get(3));
        assertThat(this.school.collect(this.students, student -> student.getScore() <= FIFTY), is(expected));
    }

    @Test
    public void shouldListReturnTheMap() {
        Map<String, Student> map = new TreeMap<>();
        for (int i = 0; i < this.students.size(); i++) {
            Student student = this.students.get(i);
            map.put(student.getName(), student);
        }
        assertThat(this.school.getStudents(this.students), is(map));
    }

    /**
     * tests levelOf
     */
    @Test
    public void shouldBeMichailEugeneIvan() {
        assertThat(this.school.levelOf(this.students, 60),
                                        is(List.of(this.students.get(5),
                                                    this.students.get(4),
                                                    this.students.get(1))));
    }
}