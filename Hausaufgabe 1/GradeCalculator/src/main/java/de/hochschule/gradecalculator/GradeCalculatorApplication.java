package de.hochschule.gradecalculator;

import de.hochschule.gradecalculator.model.Grade;
import de.hochschule.gradecalculator.service.GradeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hauptklasse der Notendurchschnitt-Berechnungsanwendung
 * 
 * Beantwortet Fragen aus der Aufgabenstellung:
 * 
 * 1. Haben Sie alle User Stories verstanden?
 *    Ja, die User Stories für die Chat-Anwendung sind verstanden.
 *    Sie beschreiben die grundlegenden Anforderungen wie Anmeldung,
 *    Konversationsverwaltung, Nachrichtenaustausch und Dateneinsicht.
 * 
 * 2. Wie würden die User Stories für Ihre Anwendung zur Berechnung 
 *    des Notendurchschnitts aussehen?
 *    
 *    - Als Student möchte ich eine Note mit Kursname, Notenwert und Credits 
 *      erfassen, damit ich meine Leistungen dokumentieren kann.
 *    
 *    - Als Student möchte ich alle meine erfassten Noten einsehen, damit ich 
 *      einen Überblick über meine bisherigen Leistungen habe.
 *    
 *    - Als Student möchte ich meinen ungewichteten Notendurchschnitt berechnen, 
 *      damit ich eine einfache Übersicht meiner durchschnittlichen Leistung erhalte.
 *    
 *    - Als Student möchte ich meinen gewichteten Notendurchschnitt nach Credits 
 *      berechnen, damit ich ein realistisches Bild meiner Gesamtleistung bekomme.
 *    
 *    - Als Student möchte ich einzelne Noten löschen können, damit ich 
 *      Fehleingaben korrigieren kann.
 *    
 *    - Als Student möchte ich Noten nach Kursname suchen können, damit ich 
 *      schnell spezifische Kursergebnisse finden kann.
 *    
 *    - Als Student möchte ich die Anzahl meiner erfassten Noten sehen, damit 
 *      ich weiß, wie viele Kurse ich bereits absolviert habe.
 * 
 * 3. Welche Ergänzungen würden Sie einfügen?
 *    
 *    - Als Student möchte ich Noten nach Semester gruppieren, damit ich 
 *      semesterweise Durchschnitte berechnen kann.
 *    
 *    - Als Student möchte ich Statistiken (beste/schlechteste Note, Median) 
 *      einsehen, damit ich meine Leistungsverteilung analysieren kann.
 *    
 *    - Als Student möchte ich Noten exportieren können (z.B. als CSV), damit 
 *      ich sie extern weiterverarbeiten kann.
 *    
 *    - Als Student möchte ich Noten importieren können, damit ich bestehende 
 *      Daten leicht übernehmen kann.
 *    
 *    - Als Student möchte ich Noten nach Kategorie filtern (z.B. Pflicht/Wahl), 
 *      damit ich zielgerichtete Auswertungen erstellen kann.
 *    
 *    - Als Student möchte ich einen Trendverlauf meiner Noten sehen, damit ich 
 *      meine Leistungsentwicklung verfolgen kann.
 * 
 * 4. Was fehlt Ihrer Meinung nach noch an Funktionalität?
 *    
 *    - Persistierung: Speicherung der Noten in einer Datenbank oder Datei, 
 *      damit Daten nicht bei jedem Neustart verloren gehen.
 *    
 *    - Benutzerverwaltung: Mehrere Studenten sollten die Anwendung nutzen 
 *      können, jeder mit eigenem Datensatz.
 *    
 *    - Validierung: Prüfung der Eingabedaten (z.B. Noten zwischen 1.0 und 5.0, 
 *      Credits > 0) mit aussagekräftigen Fehlermeldungen.
 *    
 *    - Notenumrechnung: Konvertierung zwischen verschiedenen Notensystemen 
 *      (z.B. deutsches System vs. ECTS-Noten).
 *    
 *    - Zielvorgaben: Berechnung, welche Note im nächsten Kurs benötigt wird, 
 *      um einen gewünschten Gesamtdurchschnitt zu erreichen.
 *    
 *    - Berichtsgenerierung: Automatische Erstellung von Leistungsübersichten 
 *      und Transkripten.
 *    
 *    - Backup/Wiederherstellung: Sicherung und Wiederherstellung der Notendaten.
 *    
 *    - Mehrsprachigkeit: Unterstützung für verschiedene Sprachen.
 */
@SpringBootApplication
public class GradeCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradeCalculatorApplication.class, args);
    }

    /**
     * CommandLineRunner für Demo-Zwecke
     */
    @Bean
    public CommandLineRunner demo(GradeService gradeService) {
        return args -> {
            System.out.println("\n=== Notendurchschnitt-Rechner Demo ===\n");

            // Noten hinzufügen
            gradeService.addGrade(new Grade("Mathematik I", 1.7, 6));
            gradeService.addGrade(new Grade("Programmierung I", 1.3, 8));
            gradeService.addGrade(new Grade("Datenbanken", 2.0, 5));
            gradeService.addGrade(new Grade("Web-Anwendungen", 1.0, 6));

            // Alle Noten anzeigen
            System.out.println("Alle erfassten Noten:");
            gradeService.getAllGrades().forEach(System.out::println);

            // Durchschnitt berechnen
            System.out.println("\nUngewichteter Durchschnitt: " + 
                String.format("%.2f", gradeService.calculateSimpleAverage()));
            System.out.println("Gewichteter Durchschnitt (nach Credits): " + 
                String.format("%.2f", gradeService.calculateWeightedAverage()));

            System.out.println("\nAnzahl der Kurse: " + gradeService.getGradeCount());
            System.out.println("Gesamt-Credits: " + gradeService.getTotalCredits());

            System.out.println("\n=== Demo beendet ===\n");
        };
    }
}
