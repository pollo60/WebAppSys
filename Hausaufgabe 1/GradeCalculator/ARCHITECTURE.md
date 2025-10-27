# UML Klassendiagramm - Notendurchschnitt-Rechner

```
┌─────────────────────────────────────┐
│     GradeCalculatorApplication      │
│          <<Application>>            │
├─────────────────────────────────────┤
│ + main(String[]): void              │
│ + demo(GradeService): CommandLine.. │
└─────────────────────────────────────┘
                  │
                  │ verwendet
                  ▼
┌─────────────────────────────────────┐
│          GradeService               │
│           <<Service>>               │
├─────────────────────────────────────┤
│ - gradeRepository: GradeRepository  │
├─────────────────────────────────────┤
│ + addGrade(Grade): Grade            │
│ + getAllGrades(): List<Grade>       │
│ + findGradeByCourseName(String):..  │
│ + deleteGradeByCourseName(String).. │
│ + calculateSimpleAverage(): double  │
│ + calculateWeightedAverage(): double│
│ + getGradeCount(): int              │
│ + getTotalCredits(): int            │
│ + getBestGrade(): Optional<Grade>   │
│ + getWorstGrade(): Optional<Grade>  │
│ + getPassedGrades(): List<Grade>    │
│ + getFailedGrades(): List<Grade>    │
│ + allGradesPassed(): boolean        │
│ + deleteAllGrades(): void           │
│ + hasNoGrades(): boolean            │
└─────────────────────────────────────┘
                  │
                  │ verwendet
                  ▼
┌─────────────────────────────────────┐
│        GradeRepository              │
│         <<Repository>>              │
├─────────────────────────────────────┤
│ - grades: List<Grade>               │
├─────────────────────────────────────┤
│ + save(Grade): Grade                │
│ + findAll(): List<Grade>            │
│ + findByCourseName(String): List..  │
│ + findOneByCourseName(String): Op.. │
│ + delete(Grade): boolean            │
│ + deleteByCourseName(String): bool..│
│ + deleteAll(): void                 │
│ + count(): int                      │
│ + isEmpty(): boolean                │
│ + findAllPassed(): List<Grade>      │
│ + findAllFailed(): List<Grade>      │
└─────────────────────────────────────┘
                  │
                  │ verwaltet
                  ▼
┌─────────────────────────────────────┐
│             Grade                   │
│           <<Model>>                 │
├─────────────────────────────────────┤
│ - courseName: String                │
│ - gradeValue: double                │
│ - credits: int                      │
├─────────────────────────────────────┤
│ + Grade(String, double, int)        │
│ + getCourseName(): String           │
│ + setCourseName(String): void       │
│ + getGradeValue(): double           │
│ + setGradeValue(double): void       │
│ + getCredits(): int                 │
│ + setCredits(int): void             │
│ + isPassed(): boolean               │
│ + getWeightedGrade(): double        │
│ - validateCourseName(String): void  │
│ - validateGradeValue(double): void  │
│ - validateCredits(int): void        │
│ + equals(Object): boolean           │
│ + hashCode(): int                   │
│ + toString(): String                │
└─────────────────────────────────────┘
```

## Architektur-Übersicht

### Schichtenarchitektur (von oben nach unten):

1. **Application Layer** (Anwendungsschicht)
   - `GradeCalculatorApplication`
   - Einstiegspunkt der Anwendung
   - CommandLineRunner für Demo-Zwecke

2. **Service Layer** (Geschäftslogik-Schicht)
   - `GradeService`
   - Implementiert alle User Stories
   - Enthält Berechnungslogik (Durchschnitte, Statistiken)
   - Koordiniert Zugriffe auf Repository

3. **Repository Layer** (Datenzugriffs-Schicht)
   - `GradeRepository`
   - CRUD-Operationen
   - Datenfilterung und -suche
   - Aktuell: In-Memory Speicherung

4. **Model Layer** (Datenmodell-Schicht)
   - `Grade`
   - Repräsentiert Geschäftsobjekte
   - Enthält Validierungslogik
   - Domain-spezifische Methoden

## Beziehungen

- **Application → Service**: Verwendet GradeService für alle Operationen
- **Service → Repository**: Delegiert Datenzugriffe an GradeRepository
- **Repository → Model**: Verwaltet Grade-Objekte
- **Service → Model**: Arbeitet mit Grade-Objekten

## Design Principles

- **Separation of Concerns**: Jede Schicht hat eine klare Zuständigkeit
- **Dependency Injection**: Spring injiziert Abhängigkeiten automatisch
- **Single Responsibility**: Jede Klasse hat eine einzige Verantwortung
- **Open/Closed Principle**: Erweiterbar ohne Änderung bestehenden Codes

## Test-Klassen (nicht im Diagramm)

- `GradeTest`: Testet Model-Validierung und Methoden
- `GradeRepositoryTest`: Testet CRUD-Operationen
- `GradeServiceTest`: Testet User Stories und Geschäftslogik
