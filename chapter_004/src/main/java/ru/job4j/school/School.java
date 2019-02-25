package ru.job4j.school;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * School
 * @author Vladislav Nechaev
 * @since 25.02.2019
 */
public class School {

    /**
     * collect
     * @param students - a students list.
     * @param predicate - selection expression.
     * @return - selection students list.
     */
    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * getStudents
     * @param students - a student list.
     * @return - students map.
     */
    public Map<String, Student> getStudents(List<Student> students) {
        return students.stream().collect(Collectors.toMap(student -> student.getSurname(), student -> student));
    }
}
