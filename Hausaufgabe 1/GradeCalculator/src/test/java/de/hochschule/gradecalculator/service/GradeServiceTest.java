package de.hochschule.gradecalculator.service;

import de.hochschule.gradecalculator.model.Grade;
import de.hochschule.gradecalculator.repository.GradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests für den GradeService
 * 
 * Diese Tests validieren die User Stories:
 * - Als Student möchte ich Noten erfassen
 * - Als Student möchte ich Noten einsehen
 * - Als Student möchte ich Durchschnitte berechnen
 * - Als Student möchte ich Noten löschen
 */
class GradeServiceTest {

    private GradeService gradeService;
    private GradeRepository gradeRepository;

    @BeforeEach
    void setUp() {
        gradeRepository = new GradeRepository();
        gradeService = new GradeService(gradeRepository);
    }

    // Tests für User Story: Note erfassen
    
    @Test
    void testAddGrade() {
        // Arrange
        Grade grade = new Grade("Mathematik", 1.7, 6);

        // Act
        Grade added = gradeService.addGrade(grade);

        // Assert
        assertNotNull(added);
        assertEquals(1, gradeService.getGradeCount());
    }

    @Test
    void testAddGrade_Null() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> gradeService.addGrade(null));
    }

    // Tests für User Story: Noten einsehen
    
    @Test
    void testGetAllGrades() {
        // Arrange
        gradeService.addGrade(new Grade("Mathematik", 1.7, 6));
        gradeService.addGrade(new Grade("Programmierung", 1.3, 8));

        // Act
        List<Grade> grades = gradeService.getAllGrades();

        // Assert
        assertEquals(2, grades.size());
    }

    @Test
    void testGetAllGrades_Empty() {
        // Act
        List<Grade> grades = gradeService.getAllGrades();

        // Assert
        assertTrue(grades.isEmpty());
    }

    @Test
    void testFindGradeByCourseName() {
        // Arrange
        gradeService.addGrade(new Grade("Mathematik", 1.7, 6));

        // Act
        Optional<Grade> found = gradeService.findGradeByCourseName("Mathematik");

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Mathematik", found.get().getCourseName());
    }

    @Test
    void testFindGradeByCourseName_NotFound() {
        // Act
        Optional<Grade> found = gradeService.findGradeByCourseName("Physik");

        // Assert
        assertFalse(found.isPresent());
    }

    // Tests für User Story: Noten löschen
    
    @Test
    void testDeleteGradeByCourseName() {
        // Arrange
        gradeService.addGrade(new Grade("Mathematik", 1.7, 6));

        // Act
        boolean deleted = gradeService.deleteGradeByCourseName("Mathematik");

        // Assert
        assertTrue(deleted);
        assertEquals(0, gradeService.getGradeCount());
    }

    @Test
    void testDeleteGradeByCourseName_NotFound() {
        // Act
        boolean deleted = gradeService.deleteGradeByCourseName("Mathematik");

        // Assert
        assertFalse(deleted);
    }

    // Tests für User Story: Ungewichteten Durchschnitt berechnen
    
    @Test
    void testCalculateSimpleAverage() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.0, 5));
        gradeService.addGrade(new Grade("Kurs2", 2.0, 5));
        gradeService.addGrade(new Grade("Kurs3", 3.0, 5));

        // Act
        double average = gradeService.calculateSimpleAverage();

        // Assert
        assertEquals(2.0, average, 0.01);
    }

    @Test
    void testCalculateSimpleAverage_SingleGrade() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 5));

        // Act
        double average = gradeService.calculateSimpleAverage();

        // Assert
        assertEquals(1.7, average, 0.01);
    }

    @Test
    void testCalculateSimpleAverage_NoGrades() {
        // Act & Assert
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> gradeService.calculateSimpleAverage()
        );
        assertTrue(exception.getMessage().contains("Keine Noten vorhanden"));
    }

    // Tests für User Story: Gewichteten Durchschnitt berechnen
    
    @Test
    void testCalculateWeightedAverage() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.0, 10)); // 1.0 * 10 = 10
        gradeService.addGrade(new Grade("Kurs2", 2.0, 5));  // 2.0 * 5 = 10
        // Summe: 20, Credits: 15, Durchschnitt: 20/15 = 1.333...

        // Act
        double average = gradeService.calculateWeightedAverage();

        // Assert
        assertEquals(1.333, average, 0.01);
    }

    @Test
    void testCalculateWeightedAverage_EqualCredits() {
        // Arrange - Bei gleichen Credits sollte gewichtet = ungewichtet sein
        gradeService.addGrade(new Grade("Kurs1", 1.0, 5));
        gradeService.addGrade(new Grade("Kurs2", 2.0, 5));
        gradeService.addGrade(new Grade("Kurs3", 3.0, 5));

        // Act
        double weightedAvg = gradeService.calculateWeightedAverage();
        double simpleAvg = gradeService.calculateSimpleAverage();

        // Assert
        assertEquals(simpleAvg, weightedAvg, 0.01);
    }

    @Test
    void testCalculateWeightedAverage_DifferentCredits() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.0, 8));  // 1.0 * 8 = 8
        gradeService.addGrade(new Grade("Kurs2", 3.0, 2));  // 3.0 * 2 = 6
        // Summe: 14, Credits: 10, Durchschnitt: 14/10 = 1.4

        // Act
        double average = gradeService.calculateWeightedAverage();

        // Assert
        assertEquals(1.4, average, 0.01);
    }

    @Test
    void testCalculateWeightedAverage_NoGrades() {
        // Act & Assert
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> gradeService.calculateWeightedAverage()
        );
        assertTrue(exception.getMessage().contains("Keine Noten vorhanden"));
    }

    // Tests für User Story: Anzahl der Noten sehen
    
    @Test
    void testGetGradeCount() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));
        gradeService.addGrade(new Grade("Kurs2", 2.0, 5));
        gradeService.addGrade(new Grade("Kurs3", 1.3, 8));

        // Act
        int count = gradeService.getGradeCount();

        // Assert
        assertEquals(3, count);
    }

    @Test
    void testGetTotalCredits() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));
        gradeService.addGrade(new Grade("Kurs2", 2.0, 5));
        gradeService.addGrade(new Grade("Kurs3", 1.3, 8));

        // Act
        int totalCredits = gradeService.getTotalCredits();

        // Assert
        assertEquals(19, totalCredits);
    }

    // Tests für Ergänzungen: Statistiken
    
    @Test
    void testGetBestGrade() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));
        gradeService.addGrade(new Grade("Kurs2", 1.0, 5));
        gradeService.addGrade(new Grade("Kurs3", 2.3, 8));

        // Act
        Optional<Grade> best = gradeService.getBestGrade();

        // Assert
        assertTrue(best.isPresent());
        assertEquals(1.0, best.get().getGradeValue(), 0.01);
        assertEquals("Kurs2", best.get().getCourseName());
    }

    @Test
    void testGetBestGrade_NoGrades() {
        // Act
        Optional<Grade> best = gradeService.getBestGrade();

        // Assert
        assertFalse(best.isPresent());
    }

    @Test
    void testGetWorstGrade() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));
        gradeService.addGrade(new Grade("Kurs2", 3.7, 5));
        gradeService.addGrade(new Grade("Kurs3", 2.3, 8));

        // Act
        Optional<Grade> worst = gradeService.getWorstGrade();

        // Assert
        assertTrue(worst.isPresent());
        assertEquals(3.7, worst.get().getGradeValue(), 0.01);
        assertEquals("Kurs2", worst.get().getCourseName());
    }

    @Test
    void testGetPassedGrades() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));
        gradeService.addGrade(new Grade("Kurs2", 4.0, 5));
        gradeService.addGrade(new Grade("Kurs3", 5.0, 8));

        // Act
        List<Grade> passed = gradeService.getPassedGrades();

        // Assert
        assertEquals(2, passed.size());
        assertTrue(passed.stream().allMatch(Grade::isPassed));
    }

    @Test
    void testGetFailedGrades() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));
        gradeService.addGrade(new Grade("Kurs2", 4.3, 5));
        gradeService.addGrade(new Grade("Kurs3", 5.0, 8));

        // Act
        List<Grade> failed = gradeService.getFailedGrades();

        // Assert
        assertEquals(2, failed.size());
        assertTrue(failed.stream().noneMatch(Grade::isPassed));
    }

    @Test
    void testAllGradesPassed_True() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));
        gradeService.addGrade(new Grade("Kurs2", 2.0, 5));
        gradeService.addGrade(new Grade("Kurs3", 4.0, 8));

        // Act
        boolean allPassed = gradeService.allGradesPassed();

        // Assert
        assertTrue(allPassed);
    }

    @Test
    void testAllGradesPassed_False() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));
        gradeService.addGrade(new Grade("Kurs2", 5.0, 5));

        // Act
        boolean allPassed = gradeService.allGradesPassed();

        // Assert
        assertFalse(allPassed);
    }

    @Test
    void testDeleteAllGrades() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));
        gradeService.addGrade(new Grade("Kurs2", 2.0, 5));

        // Act
        gradeService.deleteAllGrades();

        // Assert
        assertEquals(0, gradeService.getGradeCount());
        assertTrue(gradeService.hasNoGrades());
    }

    @Test
    void testHasNoGrades_True() {
        // Act & Assert
        assertTrue(gradeService.hasNoGrades());
    }

    @Test
    void testHasNoGrades_False() {
        // Arrange
        gradeService.addGrade(new Grade("Kurs1", 1.7, 6));

        // Act & Assert
        assertFalse(gradeService.hasNoGrades());
    }
}
