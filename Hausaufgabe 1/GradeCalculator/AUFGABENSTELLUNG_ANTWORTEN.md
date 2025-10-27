# Antworten auf die Aufgabenstellung

## Fragen zur Hausaufgabe

### 1. Haben Sie alle User Stories verstanden?

**Ja**, alle User Stories für die Chat-Anwendung wurden verstanden.

Die User Stories beschreiben funktionale Anforderungen aus Sicht eines registrierten Benutzers:
- **Anmeldung**: Benutzer möchte sich authentifizieren
- **Konversationen einsehen**: Überblick über alle Gespräche
- **Konversationen löschen**: Verwaltung der Gespräche
- **Neue Konversation anlegen**: Kommunikation mit neuen Kontakten initiieren
- **Konversation auswählen**: Gezielter Nachrichtenaustausch
- **Daten einsehen**: Datenschutz und Transparenz
- **Nachrichten anzeigen**: Historie der Kommunikation
- **Nachrichten versenden**: Aktive Kommunikation

Jede User Story folgt dem Schema: **Als [Rolle] möchte ich [Funktion], damit [Nutzen]**

---

### 2. Wie würden die User Stories für Ihre Anwendung zur Berechnung des Notendurchschnitts aussehen?

#### Basis User Stories (implementiert):

**US-01: Note erfassen**
> Als Student möchte ich eine Note mit Kursname, Notenwert und Credits erfassen, 
> damit ich meine Leistungen dokumentieren kann.

**US-02: Alle Noten einsehen**
> Als Student möchte ich alle meine erfassten Noten einsehen, 
> damit ich einen Überblick über meine bisherigen Leistungen habe.

**US-03: Ungewichteten Durchschnitt berechnen**
> Als Student möchte ich meinen ungewichteten Notendurchschnitt berechnen, 
> damit ich eine einfache Übersicht meiner durchschnittlichen Leistung erhalte.

**US-04: Gewichteten Durchschnitt berechnen**
> Als Student möchte ich meinen gewichteten Notendurchschnitt nach Credits berechnen, 
> damit ich ein realistisches Bild meiner Gesamtleistung bekomme.

**US-05: Note löschen**
> Als Student möchte ich einzelne Noten löschen können, 
> damit ich Fehleingaben korrigieren kann.

**US-06: Note suchen**
> Als Student möchte ich Noten nach Kursname suchen können, 
> damit ich schnell spezifische Kursergebnisse finden kann.

**US-07: Anzahl einsehen**
> Als Student möchte ich die Anzahl meiner erfassten Noten sehen, 
> damit ich weiß, wie viele Kurse ich bereits absolviert habe.

#### Erweiterte User Stories (teilweise implementiert):

**US-08: Statistiken einsehen**
> Als Student möchte ich meine beste und schlechteste Note sehen, 
> damit ich meine Leistungsspanne einschätzen kann.

**US-09: Bestandene Noten filtern**
> Als Student möchte ich nur bestandene Noten anzeigen lassen, 
> damit ich meine erfolgreichen Leistungen sehe.

**US-10: Nicht bestandene Noten filtern**
> Als Student möchte ich nicht bestandene Noten anzeigen lassen, 
> damit ich weiß, welche Prüfungen ich wiederholen muss.

---

### 3. Welche Ergänzungen würden Sie einfügen?

#### Fachliche Ergänzungen:

**US-E01: Semester-Gruppierung**
> Als Student möchte ich meine Noten nach Semester gruppieren, 
> damit ich semesterweise Durchschnitte berechnen kann.

**US-E02: Detaillierte Statistiken**
> Als Student möchte ich Median, Standardabweichung und Notenverteilung sehen, 
> damit ich meine Leistungsverteilung analysieren kann.

**US-E03: Daten exportieren**
> Als Student möchte ich meine Noten als CSV oder PDF exportieren, 
> damit ich sie extern weiterverarbeiten oder archivieren kann.

**US-E04: Daten importieren**
> Als Student möchte ich Noten aus einer Datei importieren, 
> damit ich bestehende Daten leicht übernehmen kann.

**US-E05: Kategorie-Filter**
> Als Student möchte ich Noten nach Kategorie filtern (Pflicht/Wahl/Projekt), 
> damit ich zielgerichtete Auswertungen erstellen kann.

**US-E06: Trendanalyse**
> Als Student möchte ich einen Trendverlauf meiner Noten sehen, 
> damit ich meine Leistungsentwicklung über die Zeit verfolgen kann.

**US-E07: Zielvorgaben**
> Als Student möchte ich berechnen, welche Note ich im nächsten Kurs benötige, 
> damit ich einen gewünschten Gesamtdurchschnitt erreiche.

**US-E08: Vergleich**
> Als Student möchte ich meinen Durchschnitt mit dem Jahrgangsdurchschnitt vergleichen, 
> damit ich meine Position einordnen kann.

#### Technische Ergänzungen:

**US-E09: Notizen zu Kursen**
> Als Student möchte ich zu jedem Kurs Notizen hinzufügen, 
> damit ich wichtige Informationen dokumentieren kann.

**US-E10: Mehrere Bewertungsarten**
> Als Student möchte ich verschiedene Bewertungsarten (Klausur, Hausarbeit, Projekt) 
> erfassen, damit ich differenzierte Auswertungen habe.

---

### 4. Was fehlt Ihrer Meinung nach noch an Funktionalität?

#### Kritische fehlende Funktionalität:

**A. Persistierung**
- **Problem**: Aktuell werden Daten nur im Speicher gehalten und gehen beim Neustart verloren
- **Lösung**: Integration einer Datenbank (z.B. H2, PostgreSQL, MySQL)
- **Implementierung**: Spring Data JPA für Repository-Schicht verwenden

**B. Benutzerverwaltung**
- **Problem**: Nur ein Student kann die Anwendung nutzen
- **Lösung**: Multi-User-Fähigkeit mit Authentifizierung und Autorisierung
- **Implementierung**: Spring Security für Login/Registrierung

**C. Umfassende Validierung**
- **Problem**: Nur grundlegende Validierungen vorhanden
- **Fehlende Validierungen**:
  - Duplikat-Prüfung (gleicher Kurs mehrfach erfasst)
  - Notenschritte (1.0, 1.3, 1.7, 2.0, etc.)
  - Maximale Anzahl Credits pro Kurs
  - Zeitliche Validierung (Datum der Prüfung)

**D. Fehlerbehandlung**
- **Problem**: Exceptions werden nur geworfen, nicht benutzerfreundlich behandelt
- **Lösung**: Zentrale Exception-Handler mit aussagekräftigen Fehlermeldungen

#### Wichtige fehlende Funktionalität:

**E. Web-Schnittstelle**
- **Problem**: Nur Konsolen-Demo vorhanden (wie in Aufgabe gefordert)
- **Zukünftige Erweiterung**: REST API oder Web-UI
- **Technologien**: Spring Web, Thymeleaf oder React/Angular

**F. Notenumrechnung**
- **Problem**: Nur deutsches Notensystem (1.0-5.0)
- **Fehlend**: 
  - ECTS-Noten (A-F)
  - Prozent-basierte Systeme
  - GPA (Grade Point Average)
  - Umrechnungstabellen

**G. Berichte und Reports**
- **Problem**: Keine formatierten Ausgaben
- **Fehlend**:
  - PDF-Transkripte
  - Leistungsübersichten
  - Grafische Darstellungen (Diagramme)

**H. Zeitliche Dimension**
- **Problem**: Keine Zeitstempel für Noten
- **Fehlend**:
  - Erfassungsdatum
  - Prüfungsdatum
  - Semester-Zuordnung
  - Studienjahr

**I. Backup und Wiederherstellung**
- **Problem**: Keine Möglichkeit zur Datensicherung
- **Fehlend**:
  - Automatische Backups
  - Export-/Import-Funktion
  - Versionierung

**J. Erweiterte Features**
- **Fehlend**:
  - Mehrsprachigkeit (i18n)
  - Benachrichtigungen
  - Erinnerungen an Prüfungen
  - Kalender-Integration
  - Mobile App
  - Offline-Fähigkeit

#### Qualitätsmerkmale:

**K. Logging und Monitoring**
- Detailliertes Logging aller Operationen
- Performance-Monitoring
- Audit-Trail für Änderungen

**L. Dokumentation**
- API-Dokumentation (z.B. Swagger/OpenAPI)
- Benutzerhandbuch
- Entwickler-Dokumentation

**M. Konfigurierbarkeit**
- Verschiedene Notensysteme konfigurierbar
- Validierungsregeln anpassbar
- Berechnungsformeln konfigurierbar

---

## Zusammenfassung der Implementierung

### Was wurde umgesetzt:

✅ Alle Basis-User Stories (US-01 bis US-07)  
✅ Erweiterte Statistik-Features (US-08 bis US-10)  
✅ Klare Schichtenarchitektur (Model, Repository, Service, Application)  
✅ Umfassende Unit-Tests (70+ Tests)  
✅ Validierung aller Eingabedaten  
✅ Detaillierte Code-Dokumentation  
✅ README und ARCHITECTURE-Dokumentation  

### Was als nächstes umgesetzt werden sollte:

1. **Persistierung** (Datenbank-Integration)
2. **Web-Interface** (REST API)
3. **Benutzerverwaltung** (Multi-User)
4. **Semester-Verwaltung** (zeitliche Gruppierung)
5. **Export/Import** (Datenaustausch)

### Learnings:

- **User Stories** sind ein effektives Werkzeug zur Anforderungsanalyse
- **Test-Driven Development** hilft bei der Qualitätssicherung
- **Separation of Concerns** erleichtert Wartung und Erweiterung
- **Validierung** ist essentiell für robuste Anwendungen
- **Dokumentation** ist genauso wichtig wie der Code selbst
