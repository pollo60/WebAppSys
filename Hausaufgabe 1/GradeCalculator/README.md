# Notendurchschnitt-Rechner

Hausaufgabe zur Übung Web-basierte Anwendungssysteme

## Projektbeschreibung

Dieses Projekt implementiert eine Anwendung zur Berechnung des Notendurchschnitts eines Studenten. Die Anwendung wurde mit Spring Boot entwickelt und folgt dem Prinzip der Trennung von Zuständigkeiten (Separation of Concerns).

## Projektstruktur

```
GradeCalculator/
├── src/
│   ├── main/
│   │   ├── java/de/hochschule/gradecalculator/
│   │   │   ├── GradeCalculatorApplication.java  # Hauptklasse mit User Story Kommentaren
│   │   │   ├── model/
│   │   │   │   └── Grade.java                   # Model für Noten mit Validierung
│   │   │   ├── repository/
│   │   │   │   └── GradeRepository.java         # Datenverwaltung (CRUD)
│   │   │   └── service/
│   │   │       └── GradeService.java            # Geschäftslogik & Berechnungen
│   │   └── resources/
│   │       └── application.properties           # Konfiguration
│   └── test/
│       └── java/de/hochschule/gradecalculator/
│           ├── model/
│           │   └── GradeTest.java               # Tests für Grade-Klasse
│           ├── repository/
│           │   └── GradeRepositoryTest.java     # Tests für Repository
│           └── service/
│               └── GradeServiceTest.java        # Tests für Service
└── pom.xml                                       # Maven Konfiguration
```

## Architektur

Das Projekt folgt einer klassischen mehrschichtigen Architektur:

### 1. Model-Schicht (`model/`)
- **Grade.java**: Repräsentiert eine Note mit Kursname, Notenwert (1.0-5.0) und Credits
- Enthält Validierungslogik für alle Attribute
- Implementiert Hilfsmethoden wie `isPassed()` und `getWeightedGrade()`

### 2. Repository-Schicht (`repository/`)
- **GradeRepository.java**: Verantwortlich für Datenverwaltung
- Implementiert CRUD-Operationen (Create, Read, Update, Delete)
- Bietet Suchmethoden nach Kursname
- Aktuelle Implementierung: In-Memory (könnte leicht durch Datenbank ersetzt werden)

### 3. Service-Schicht (`service/`)
- **GradeService.java**: Enthält die gesamte Geschäftslogik
- Berechnet ungewichteten und gewichteten Durchschnitt
- Liefert Statistiken (beste/schlechteste Note, bestandene/nicht bestandene Noten)
- Koordiniert Zugriffe auf das Repository

### 4. Application-Schicht
- **GradeCalculatorApplication.java**: Hauptklasse mit CommandLineRunner für Demo
- Enthält umfangreiche Kommentare zu allen User Stories und Aufgabenstellungen

## User Stories

Die Anwendung erfüllt folgende User Stories:

1. **Note erfassen**: Als Student möchte ich eine Note mit Kursname, Notenwert und Credits erfassen
2. **Noten einsehen**: Als Student möchte ich alle meine erfassten Noten einsehen
3. **Ungewichteten Durchschnitt berechnen**: Als Student möchte ich meinen einfachen Notendurchschnitt sehen
4. **Gewichteten Durchschnitt berechnen**: Als Student möchte ich meinen nach Credits gewichteten Durchschnitt sehen
5. **Noten löschen**: Als Student möchte ich einzelne Noten löschen können
6. **Noten suchen**: Als Student möchte ich Noten nach Kursname suchen
7. **Anzahl sehen**: Als Student möchte ich die Anzahl meiner Kurse und Credits sehen

### Ergänzungen (im Code vorbereitet)
- Statistiken: Beste/schlechteste Note
- Filterung: Bestandene/nicht bestandene Noten
- Prüfung: Ob alle Noten bestanden sind

## Installation & Ausführung

### Voraussetzungen
- Java 17 oder höher
- Maven 3.6 oder höher

### Projekt bauen
```bash
cd "Hausaufgabe 1/GradeCalculator"
./mvnw clean install
```

### Anwendung starten
```bash
./mvnw spring-boot:run
```

### Tests ausführen
```bash
./mvnw test
```

## Testabdeckung

Das Projekt verfügt über umfangreiche Unit-Tests:

- **GradeTest.java**: 20+ Tests für die Model-Klasse
  - Validierung aller Attribute
  - Grenzwertprüfungen
  - equals/hashCode/toString Tests
  
- **GradeRepositoryTest.java**: 18+ Tests für das Repository
  - CRUD-Operationen
  - Suchfunktionen
  - Filtermethoden
  
- **GradeServiceTest.java**: 30+ Tests für den Service
  - Alle User Stories werden getestet
  - Durchschnittsberechnungen mit verschiedenen Szenarien
  - Statistik-Funktionen
  - Fehlerfälle

## Verwendete Technologien

- **Spring Boot 3.1.5**: Framework für die Anwendung
- **JUnit 5**: Testing Framework
- **Maven**: Build-Management
- **Java 17**: Programmiersprache

## Validierungen

Die Anwendung validiert alle Eingaben:
- Notenwerte müssen zwischen 1.0 und 5.0 liegen
- Credits müssen größer als 0 sein
- Kursname darf nicht leer sein
- Null-Werte werden abgefangen

## Fehlende Funktionalität (für zukünftige Erweiterungen)

Wie in der Hauptklasse dokumentiert, fehlen noch:
- Persistierung in Datenbank
- Benutzerverwaltung
- Semester-Gruppierung
- Export/Import von Noten
- Web-Interface (REST API)
- Notenumrechnung zwischen Systemen
- Zielvorgaben-Berechnung

## Autoren

Erstellt als Hausaufgabe für Web-basierte Anwendungssysteme

## Lizenz

Educational Project - Hausaufgabe
