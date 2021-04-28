package de.neuefische.studendb.model;

public class WizardryStudent extends Student {

    private String favoriteBook;

    public WizardryStudent(String name, String id, String favoriteBook) {
        super(name, id);
        this.favoriteBook = favoriteBook;
    }

    @Override
    public int anzahlModule() {
        return (8 * 8);
    }
}
