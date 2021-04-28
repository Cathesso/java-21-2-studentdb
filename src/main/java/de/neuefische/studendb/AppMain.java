package de.neuefische.studendb;

import de.neuefische.studendb.db.StudentDb;
import de.neuefische.studendb.model.MediaScienceStudent;
import de.neuefische.studendb.model.Student;
import de.neuefische.studendb.model.WizardryStudent;

public class AppMain {

    public static void main(String[] args) {
        Student[] students = new Student[]{
                new MediaScienceStudent("Jane", "42"),
                new MediaScienceStudent("Klaus", "13"),
                new MediaScienceStudent("Franky", "100"),
                new WizardryStudent("Rincewind", "8", "Das Oktav")
        };
        StudentDb studentDb = new StudentDb(students);

        for(int i=0; i < 5; i++){
            System.out.println(studentDb.randomStudent().anzahlModule());
        }

    }

}
