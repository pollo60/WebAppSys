# Hausaufgabe 1 - Notendurchschnitt-Rechner

## Projekt-Übersicht

Dieses Projekt ist die Lösung zur Hausaufgabe 1 der Übung "Web-basierte Anwendungssysteme".

Es implementiert eine Anwendung zur Berechnung des Notendurchschnitts mit Spring Boot.

## Schnellstart

### Voraussetzungen
- Java 17 oder höher
- Keine weiteren Installationen nötig (Maven Wrapper inkludiert)

### Projekt kompilieren und testen
```bash
cd "Hausaufgabe 1/GradeCalculator"

# Tests ausführen (Linux/macOS)
./mvnw test

# Tests ausführen (Windows)
mvnw.cmd test
```

### Anwendung starten
```bash
# Anwendung starten (Linux/macOS)
./mvnw spring-boot:run

# Anwendung starten (Windows)
mvnw.cmd spring-boot:run
```

## Projektstruktur

```
GradeCalculator/
├── README.md                           # Haupt-Dokumentation
├── ARCHITECTURE.md                     # UML und Architektur-Übersicht
├── AUFGABENSTELLUNG_ANTWORTEN.md       # Detaillierte Antworten auf alle Fragen
├── pom.xml                             # Maven-Konfiguration
├── .gitignore                          # Git-Ignore-Datei
│
├── src/main/java/de/hochschule/gradecalculator/
│   ├── GradeCalculatorApplication.java # Hauptklasse mit User Story Kommentaren
│   ├── model/
│   │   └── Grade.java                  # Note (Model mit Validierung)
│   ├── repository/
│   │   └── GradeRepository.java        # Datenverwaltung (CRUD)
│   └── service/
│       └── GradeService.java           # Geschäftslogik
│
├── src/main/resources/
│   └── application.properties          # Spring Boot Konfiguration
│
└── src/test/java/de/hochschule/gradecalculator/
    ├── model/
    │   └── GradeTest.java              # 20+ Tests für Grade
    ├── repository/
    │   └── GradeRepositoryTest.java    # 18+ Tests für Repository
    └── service/
        └── GradeServiceTest.java       # 30+ Tests für Service
```

## Dokumentation

Die Hausaufgabe enthält drei zentrale Dokumentationsdateien:

1. **README.md** - Projektdokumentation, Technologien, Installation
2. **ARCHITECTURE.md** - UML-Klassendiagramm und Architektur-Erklärung
3. **AUFGABENSTELLUNG_ANTWORTEN.md** - Detaillierte Antworten auf alle Fragen:
   - Verständnis der User Stories
   - User Stories für Notendurchschnitt-Berechnung
   - Vorgeschlagene Ergänzungen
   - Fehlende Funktionalität

## Implementierte Features

### Basis-Funktionen (User Stories)
- ✅ Note erfassen (Kursname, Notenwert, Credits)
- ✅ Alle Noten einsehen
- ✅ Ungewichteten Durchschnitt berechnen
- ✅ Gewichteten Durchschnitt nach Credits berechnen
- ✅ Noten löschen
- ✅ Noten nach Kursname suchen
- ✅ Anzahl der Noten und Credits einsehen

### Erweiterte Features
- ✅ Beste/Schlechteste Note ermitteln
- ✅ Bestandene/Nicht bestandene Noten filtern
- ✅ Prüfen ob alle Noten bestanden sind
- ✅ Umfassende Validierung aller Eingaben

### Test-Abdeckung
- ✅ 70+ Unit Tests
- ✅ Model-Tests (Validierung, Grenzwerte)
- ✅ Repository-Tests (CRUD-Operationen)
- ✅ Service-Tests (User Stories, Berechnungen)

## Technologien

- **Java 17** - Programmiersprache
- **Spring Boot 3.1.5** - Framework
- **Maven** - Build-Management
- **JUnit 5** - Testing-Framework

## Architektur

Das Projekt folgt einer klassischen 4-Schichten-Architektur:

1. **Application** - Einstiegspunkt
2. **Service** - Geschäftslogik
3. **Repository** - Datenhaltung
4. **Model** - Datenmodell

Siehe `ARCHITECTURE.md` für Details und UML-Diagramm.

## Antworten auf Aufgabenstellung

Alle Fragen aus der Aufgabenstellung werden **direkt im Code** beantwortet:
- In `GradeCalculatorApplication.java` als JavaDoc-Kommentare
- Zusätzlich ausführlich in `AUFGABENSTELLUNG_ANTWORTEN.md`

## Demo-Ausgabe

Bei Start der Anwendung wird eine Demo ausgeführt:
```
=== Notendurchschnitt-Rechner Demo ===

Alle erfassten Noten:
Grade{courseName='Mathematik I', gradeValue=1.7, credits=6, passed=Ja}
Grade{courseName='Programmierung I', gradeValue=1.3, credits=8, passed=Ja}
Grade{courseName='Datenbanken', gradeValue=2.0, credits=5, passed=Ja}
Grade{courseName='Web-Anwendungen', gradeValue=1.0, credits=6, passed=Ja}

Ungewichteter Durchschnitt: 1.50
Gewichteter Durchschnitt (nach Credits): 1.48

Anzahl der Kurse: 4
Gesamt-Credits: 25

=== Demo beendet ===
```

## Autor

Erstellt als Hausaufgabe für Web-basierte Anwendungssysteme
