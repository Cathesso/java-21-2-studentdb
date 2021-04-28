package de.neuefische.studendb.model;

public class MediaScienceStudent extends Student{

    public MediaScienceStudent(String name, String id) {
        super(name, id);
    }

    @Override
    public int anzahlModule() {
        return 25;
    }

}
