package ru.job4j.school;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * @param students - a students list.
     * @return - students map.
     */
    public Map<String, Student> getStudents(List<Student> students) {
        return students.stream().collect(Collectors.toMap(Student::getName, student -> student));
    }

    /**
     * levelOf
     *
     * @param students - a students list
     * @param bound - the bound of student score
     * @return - the list of student who have a score above the specified bound.
     */
    public List<Student> levelOf(List<Student> students, int bound) {
       return students
               .stream()
               .flatMap(Stream::ofNullable)
               .sorted(Comparator.comparingInt(Student::getScore))
               .dropWhile(s -> s.getScore() <= bound)
               .collect(Collectors.toList());
    }
}
