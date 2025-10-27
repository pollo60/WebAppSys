package de.hochschule.gradecalculator.repository;

import de.hochschule.gradecalculator.model.Grade;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository-Klasse für die Verwaltung von Noten
 * 
 * Zuständigkeit: Datenhaltung und grundlegende CRUD-Operationen
 * (Create, Read, Update, Delete)
 * 
 * Hinweis: In dieser Implementierung werden Daten im Speicher gehalten.
 * Für eine produktive Anwendung sollte eine Datenbank verwendet werden.
 */
@Repository
public class GradeRepository {

    private final List<Grade> grades = new ArrayList<>();

    /**
     * Fügt eine neue Note hinzu
     * 
     * @param grade Die hinzuzufügende Note
     * @return Die hinzugefügte Note
     */
    public Grade save(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Note darf nicht null sein");
        }
        grades.add(grade);
        return grade;
    }

    /**
     * Gibt alle Noten zurück
     * 
     * @return Liste aller Noten
     */
    public List<Grade> findAll() {
        return new ArrayList<>(grades);
    }

    /**
     * Sucht Noten nach Kursname
     * 
     * @param courseName Der zu suchende Kursname
     * @return Liste der gefundenen Noten
     */
    public List<Grade> findByCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return grades.stream()
                .filter(grade -> grade.getCourseName().equalsIgnoreCase(courseName.trim()))
                .collect(Collectors.toList());
    }

    /**
     * Sucht eine Note anhand des Kursnamens
     * 
     * @param courseName Der zu suchende Kursname
     * @return Optional mit der gefundenen Note oder leer
     */
    public Optional<Grade> findOneByCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            return Optional.empty();
        }
        return grades.stream()
                .filter(grade -> grade.getCourseName().equalsIgnoreCase(courseName.trim()))
                .findFirst();
    }

    /**
     * Löscht eine Note
     * 
     * @param grade Die zu löschende Note
     * @return true wenn erfolgreich gelöscht, sonst false
     */
    public boolean delete(Grade grade) {
        if (grade == null) {
            return false;
        }
        return grades.remove(grade);
    }

    /**
     * Löscht eine Note anhand des Kursnamens
     * 
     * @param courseName Der Kursname der zu löschenden Note
     * @return true wenn erfolgreich gelöscht, sonst false
     */
    public boolean deleteByCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            return false;
        }
        return grades.removeIf(grade -> 
            grade.getCourseName().equalsIgnoreCase(courseName.trim()));
    }

    /**
     * Löscht alle Noten
     */
    public void deleteAll() {
        grades.clear();
    }

    /**
     * Gibt die Anzahl der gespeicherten Noten zurück
     * 
     * @return Anzahl der Noten
     */
    public int count() {
        return grades.size();
    }

    /**
     * Prüft, ob Noten vorhanden sind
     * 
     * @return true wenn keine Noten vorhanden, sonst false
     */
    public boolean isEmpty() {
        return grades.isEmpty();
    }

    /**
     * Gibt alle bestandenen Noten zurück
     * 
     * @return Liste der bestandenen Noten
     */
    public List<Grade> findAllPassed() {
        return grades.stream()
                .filter(Grade::isPassed)
                .collect(Collectors.toList());
    }

    /**
     * Gibt alle nicht bestandenen Noten zurück
     * 
     * @return Liste der nicht bestandenen Noten
     */
    public List<Grade> findAllFailed() {
        return grades.stream()
                .filter(grade -> !grade.isPassed())
                .collect(Collectors.toList());
    }
}
