package de.neuefische.studendb.db;

import de.neuefische.studendb.model.MediaScienceStudent;
import de.neuefische.studendb.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDbTest {

    private static Arguments[] provideTestAddArguments() {
        return new Arguments[]{
                Arguments.of(
                        new Student[]{
                                new MediaScienceStudent("Student 1", "1"),
                                new MediaScienceStudent("Student 2", "2")
                        },
                        new Student[]{
                                new MediaScienceStudent("Student 1", "1"),
                                new MediaScienceStudent("Student 2", "2"),
                                new MediaScienceStudent("Jane", "42")
                        }
                ),
                Arguments.of(
                        new Student[]{},
                        new Student[]{new MediaScienceStudent("Jane", "42")}
                )
        };
    }

    private static Arguments[] provideTestRemoveArguments() {
        return new Arguments[]{
                Arguments.of(
                        new Student[]{
                                new MediaScienceStudent("Hans", "12"),
                                new MediaScienceStudent("Jane", "42"),
                                new MediaScienceStudent("Peter", "23")
                        },
                        new Student[]{
                                new MediaScienceStudent("Hans", "12"),
                                new MediaScienceStudent("Peter", "23")
                        }
                ),
                Arguments.of(
                        new Student[]{
                                new MediaScienceStudent("Hans", "12"),
                                new MediaScienceStudent("Peter", "23")
                        },
                        new Student[]{
                                new MediaScienceStudent("Hans", "12"),
                                new MediaScienceStudent("Peter", "23")
                        }
                ),
                Arguments.of(
                        new Student[]{
                                new MediaScienceStudent("Jane", "42"),
                                new MediaScienceStudent("Hans", "12"),
                                new MediaScienceStudent("Peter", "23")
                        },
                        new Student[]{
                                new MediaScienceStudent("Hans", "12"),
                                new MediaScienceStudent("Peter", "23")
                        }
                ),
                Arguments.of(
                        new Student[]{
                                new MediaScienceStudent("Hans", "12"),
                                new MediaScienceStudent("Peter", "23"),
                                new MediaScienceStudent("Jane", "42")
                        },
                        new Student[]{
                                new MediaScienceStudent("Hans", "12"),
                                new MediaScienceStudent("Peter", "23")
                        }
                ),
                Arguments.of(
                        new Student[]{},
                        new Student[]{}
                ),
                Arguments.of(
                        new Student[]{new MediaScienceStudent("Jane", "42")},
                        new Student[]{}
                )
        };
    }

    @Test
    @DisplayName("list() returns all students in the db")
    public void testList() {
        // Given
        Student[] students = new Student[]{
                new MediaScienceStudent("Jane", "42"),
                new MediaScienceStudent("Klaus", "13"),
                new MediaScienceStudent("Franky", "100")
        };
        StudentDb studentDb = new StudentDb(students);

        // When
        Student[] actual = studentDb.list();

        // Then
        Student[] expected = new Student[]{
                new MediaScienceStudent("Jane", "42"),
                new MediaScienceStudent("Klaus", "13"),
                new MediaScienceStudent("Franky", "100")
        };
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("toString() returns a formatted list of all students")
    public void testToString() {
        // Given
        Student[] students = new Student[]{
                new MediaScienceStudent("Jane", "42"),
                new MediaScienceStudent("Klaus", "13"),
                new MediaScienceStudent("Franky", "100")
        };
        StudentDb studentDb = new StudentDb(students);

        // When
        String actual = studentDb.toString();

        // Then
        String expected = "MediaScienceStudent{name='Jane', id='42'}\n"
                + "MediaScienceStudent{name='Klaus', id='13'}\n"
                + "MediaScienceStudent{name='Franky', id='100'}\n";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideTestAddArguments")
    public void testAdd(Student[] givenStudents, Student[] expectedStudents) {
        // Given
        StudentDb studentDb = new StudentDb(givenStudents);
        Student student = new MediaScienceStudent("Jane", "42");

        // When
        studentDb.add(student);
        Student[] actualStudents = studentDb.list();

        // Then
        assertArrayEquals(expectedStudents, actualStudents);
    }

    @ParameterizedTest
    @MethodSource("provideTestRemoveArguments")
    public void testRemove(Student[] givenStudents, Student[] expectedStudents) {
        // Given
        StudentDb studentDb = new StudentDb(givenStudents);

        // When
        studentDb.remove(new MediaScienceStudent("Jane", "42"));
        Student[] actualStudents = studentDb.list();

        // Then
        assertArrayEquals(expectedStudents, actualStudents);
    }
}