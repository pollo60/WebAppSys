package de.hochschule.gradecalculator.model;

import java.util.Objects;

/**
 * Model-Klasse für eine Note
 * 
 * Repräsentiert eine einzelne Prüfungsleistung mit:
 * - Kursname
 * - Notenwert (1.0 - 5.0, wobei 1.0 = sehr gut, 5.0 = nicht bestanden)
 * - Credits/ECTS-Punkte
 */
public class Grade {
    
    private String courseName;
    private double gradeValue;
    private int credits;

    /**
     * Konstruktor für eine Note
     * 
     * @param courseName Name des Kurses
     * @param gradeValue Notenwert (1.0 - 5.0)
     * @param credits Anzahl der Credits/ECTS-Punkte
     * @throws IllegalArgumentException wenn ungültige Werte übergeben werden
     */
    public Grade(String courseName, double gradeValue, int credits) {
        validateCourseName(courseName);
        validateGradeValue(gradeValue);
        validateCredits(credits);
        
        this.courseName = courseName;
        this.gradeValue = gradeValue;
        this.credits = credits;
    }

    /**
     * Validiert den Kursnamen
     */
    private void validateCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Kursname darf nicht leer sein");
        }
    }

    /**
     * Validiert den Notenwert
     * Deutsche Noten: 1.0 (sehr gut) bis 5.0 (nicht bestanden)
     */
    private void validateGradeValue(double gradeValue) {
        if (gradeValue < 1.0 || gradeValue > 5.0) {
            throw new IllegalArgumentException(
                "Notenwert muss zwischen 1.0 und 5.0 liegen (aktuell: " + gradeValue + ")");
        }
    }

    /**
     * Validiert die Credits
     */
    private void validateCredits(int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException(
                "Credits müssen größer als 0 sein (aktuell: " + credits + ")");
        }
    }

    // Getter und Setter
    
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        validateCourseName(courseName);
        this.courseName = courseName;
    }

    public double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(double gradeValue) {
        validateGradeValue(gradeValue);
        this.gradeValue = gradeValue;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        validateCredits(credits);
        this.credits = credits;
    }

    /**
     * Prüft, ob die Note bestanden ist
     * Im deutschen System: Note <= 4.0 = bestanden
     */
    public boolean isPassed() {
        return gradeValue <= 4.0;
    }

    /**
     * Gibt die gewichtete Note zurück (Note * Credits)
     */
    public double getWeightedGrade() {
        return gradeValue * credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Double.compare(grade.gradeValue, gradeValue) == 0 &&
               credits == grade.credits &&
               Objects.equals(courseName, grade.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, gradeValue, credits);
    }

    @Override
    public String toString() {
        return String.format("Grade{courseName='%s', gradeValue=%.1f, credits=%d, passed=%s}",
                courseName, gradeValue, credits, isPassed() ? "Ja" : "Nein");
    }
}
