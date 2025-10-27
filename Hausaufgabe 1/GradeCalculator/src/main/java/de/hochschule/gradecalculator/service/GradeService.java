package de.hochschule.gradecalculator.service;

import de.hochschule.gradecalculator.model.Grade;
import de.hochschule.gradecalculator.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service-Klasse für die Geschäftslogik der Notenberechnung
 * 
 * Zuständigkeit: Implementierung der fachlichen Anforderungen und Berechnungen
 * - Notendurchschnitt (einfach und gewichtet)
 * - Statistiken
 * - Validierung von Geschäftsregeln
 */
@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    /**
     * Fügt eine neue Note hinzu
     * 
     * User Story: Als Student möchte ich eine Note erfassen
     * 
     * @param grade Die hinzuzufügende Note
     * @return Die hinzugefügte Note
     */
    public Grade addGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Note darf nicht null sein");
        }
        return gradeRepository.save(grade);
    }

    /**
     * Gibt alle Noten zurück
     * 
     * User Story: Als Student möchte ich alle meine Noten einsehen
     * 
     * @return Liste aller Noten
     */
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    /**
     * Sucht eine Note nach Kursname
     * 
     * User Story: Als Student möchte ich Noten nach Kursname suchen
     * 
     * @param courseName Der zu suchende Kursname
     * @return Optional mit der gefundenen Note
     */
    public Optional<Grade> findGradeByCourseName(String courseName) {
        return gradeRepository.findOneByCourseName(courseName);
    }

    /**
     * Löscht eine Note nach Kursname
     * 
     * User Story: Als Student möchte ich Noten löschen können
     * 
     * @param courseName Der Kursname der zu löschenden Note
     * @return true wenn erfolgreich gelöscht
     */
    public boolean deleteGradeByCourseName(String courseName) {
        return gradeRepository.deleteByCourseName(courseName);
    }

    /**
     * Berechnet den einfachen (ungewichteten) Notendurchschnitt
     * 
     * User Story: Als Student möchte ich meinen ungewichteten Notendurchschnitt berechnen
     * 
     * @return Der einfache Durchschnitt aller Noten
     * @throws IllegalStateException wenn keine Noten vorhanden sind
     */
    public double calculateSimpleAverage() {
        List<Grade> grades = gradeRepository.findAll();
        if (grades.isEmpty()) {
            throw new IllegalStateException("Keine Noten vorhanden für Durchschnittsberechnung");
        }

        double sum = grades.stream()
                .mapToDouble(Grade::getGradeValue)
                .sum();

        return sum / grades.size();
    }

    /**
     * Berechnet den gewichteten Notendurchschnitt nach Credits
     * 
     * User Story: Als Student möchte ich meinen gewichteten Notendurchschnitt berechnen
     * 
     * Formel: Summe(Note * Credits) / Summe(Credits)
     * 
     * @return Der gewichtete Durchschnitt
     * @throws IllegalStateException wenn keine Noten vorhanden sind
     */
    public double calculateWeightedAverage() {
        List<Grade> grades = gradeRepository.findAll();
        if (grades.isEmpty()) {
            throw new IllegalStateException("Keine Noten vorhanden für Durchschnittsberechnung");
        }

        double weightedSum = grades.stream()
                .mapToDouble(Grade::getWeightedGrade)
                .sum();

        int totalCredits = grades.stream()
                .mapToInt(Grade::getCredits)
                .sum();

        if (totalCredits == 0) {
            throw new IllegalStateException("Gesamt-Credits dürfen nicht 0 sein");
        }

        return weightedSum / totalCredits;
    }

    /**
     * Gibt die Anzahl aller erfassten Noten zurück
     * 
     * User Story: Als Student möchte ich die Anzahl meiner Noten sehen
     * 
     * @return Anzahl der Noten
     */
    public int getGradeCount() {
        return gradeRepository.count();
    }

    /**
     * Berechnet die Gesamtzahl der Credits
     * 
     * @return Summe aller Credits
     */
    public int getTotalCredits() {
        return gradeRepository.findAll().stream()
                .mapToInt(Grade::getCredits)
                .sum();
    }

    /**
     * Gibt die beste Note zurück
     * 
     * Ergänzung: Statistiken zur Leistungsanalyse
     * 
     * @return Optional mit der besten Note
     */
    public Optional<Grade> getBestGrade() {
        return gradeRepository.findAll().stream()
                .min(Comparator.comparingDouble(Grade::getGradeValue));
    }

    /**
     * Gibt die schlechteste Note zurück
     * 
     * Ergänzung: Statistiken zur Leistungsanalyse
     * 
     * @return Optional mit der schlechtesten Note
     */
    public Optional<Grade> getWorstGrade() {
        return gradeRepository.findAll().stream()
                .max(Comparator.comparingDouble(Grade::getGradeValue));
    }

    /**
     * Gibt alle bestandenen Noten zurück
     * 
     * @return Liste der bestandenen Noten
     */
    public List<Grade> getPassedGrades() {
        return gradeRepository.findAllPassed();
    }

    /**
     * Gibt alle nicht bestandenen Noten zurück
     * 
     * @return Liste der nicht bestandenen Noten
     */
    public List<Grade> getFailedGrades() {
        return gradeRepository.findAllFailed();
    }

    /**
     * Prüft, ob alle Noten bestanden sind
     * 
     * @return true wenn alle Noten bestanden sind
     */
    public boolean allGradesPassed() {
        return gradeRepository.findAll().stream()
                .allMatch(Grade::isPassed);
    }

    /**
     * Löscht alle Noten
     */
    public void deleteAllGrades() {
        gradeRepository.deleteAll();
    }

    /**
     * Prüft, ob Noten vorhanden sind
     * 
     * @return true wenn keine Noten vorhanden
     */
    public boolean hasNoGrades() {
        return gradeRepository.isEmpty();
    }
}
