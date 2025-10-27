package de.hochschule.gradecalculator.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests für die Grade-Klasse
 */
class GradeTest {

    @Test
    void testGradeCreation() {
        // Arrange & Act
        Grade grade = new Grade("Mathematik", 1.7, 6);

        // Assert
        assertEquals("Mathematik", grade.getCourseName());
        assertEquals(1.7, grade.getGradeValue(), 0.01);
        assertEquals(6, grade.getCredits());
    }

    @Test
    void testGradeWithMinimumValue() {
        // Arrange & Act
        Grade grade = new Grade("Sehr Gut", 1.0, 5);

        // Assert
        assertEquals(1.0, grade.getGradeValue(), 0.01);
        assertTrue(grade.isPassed());
    }

    @Test
    void testGradeWithMaximumValue() {
        // Arrange & Act
        Grade grade = new Grade("Nicht Bestanden", 5.0, 3);

        // Assert
        assertEquals(5.0, grade.getGradeValue(), 0.01);
        assertFalse(grade.isPassed());
    }

    @Test
    void testInvalidGradeValueTooLow() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Grade("Test", 0.5, 5)
        );
        assertTrue(exception.getMessage().contains("zwischen 1.0 und 5.0"));
    }

    @Test
    void testInvalidGradeValueTooHigh() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Grade("Test", 5.5, 5)
        );
        assertTrue(exception.getMessage().contains("zwischen 1.0 und 5.0"));
    }

    @Test
    void testInvalidCreditsZero() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Grade("Test", 2.0, 0)
        );
        assertTrue(exception.getMessage().contains("größer als 0"));
    }

    @Test
    void testInvalidCreditsNegative() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Grade("Test", 2.0, -5)
        );
        assertTrue(exception.getMessage().contains("größer als 0"));
    }

    @Test
    void testInvalidCourseNameNull() {
        // Act & Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> new Grade(null, 2.0, 5)
        );
    }

    @Test
    void testInvalidCourseNameEmpty() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Grade("", 2.0, 5)
        );
        assertTrue(exception.getMessage().contains("nicht leer"));
    }

    @Test
    void testInvalidCourseNameWhitespace() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Grade("   ", 2.0, 5)
        );
        assertTrue(exception.getMessage().contains("nicht leer"));
    }

    @Test
    void testIsPassed_WithPassingGrade() {
        // Arrange
        Grade grade = new Grade("Test", 4.0, 5);

        // Act & Assert
        assertTrue(grade.isPassed());
    }

    @Test
    void testIsPassed_WithFailingGrade() {
        // Arrange
        Grade grade = new Grade("Test", 4.3, 5);

        // Act & Assert
        assertFalse(grade.isPassed());
    }

    @Test
    void testGetWeightedGrade() {
        // Arrange
        Grade grade = new Grade("Test", 2.0, 5);

        // Act
        double weighted = grade.getWeightedGrade();

        // Assert
        assertEquals(10.0, weighted, 0.01);
    }

    @Test
    void testSetCourseName() {
        // Arrange
        Grade grade = new Grade("Alter Name", 2.0, 5);

        // Act
        grade.setCourseName("Neuer Name");

        // Assert
        assertEquals("Neuer Name", grade.getCourseName());
    }

    @Test
    void testSetGradeValue() {
        // Arrange
        Grade grade = new Grade("Test", 2.0, 5);

        // Act
        grade.setGradeValue(1.3);

        // Assert
        assertEquals(1.3, grade.getGradeValue(), 0.01);
    }

    @Test
    void testSetCredits() {
        // Arrange
        Grade grade = new Grade("Test", 2.0, 5);

        // Act
        grade.setCredits(8);

        // Assert
        assertEquals(8, grade.getCredits());
    }

    @Test
    void testEquals_SameObject() {
        // Arrange
        Grade grade = new Grade("Test", 2.0, 5);

        // Act & Assert
        assertEquals(grade, grade);
    }

    @Test
    void testEquals_EqualObjects() {
        // Arrange
        Grade grade1 = new Grade("Test", 2.0, 5);
        Grade grade2 = new Grade("Test", 2.0, 5);

        // Act & Assert
        assertEquals(grade1, grade2);
    }

    @Test
    void testEquals_DifferentCourseName() {
        // Arrange
        Grade grade1 = new Grade("Test1", 2.0, 5);
        Grade grade2 = new Grade("Test2", 2.0, 5);

        // Act & Assert
        assertNotEquals(grade1, grade2);
    }

    @Test
    void testEquals_DifferentGradeValue() {
        // Arrange
        Grade grade1 = new Grade("Test", 2.0, 5);
        Grade grade2 = new Grade("Test", 2.3, 5);

        // Act & Assert
        assertNotEquals(grade1, grade2);
    }

    @Test
    void testEquals_DifferentCredits() {
        // Arrange
        Grade grade1 = new Grade("Test", 2.0, 5);
        Grade grade2 = new Grade("Test", 2.0, 6);

        // Act & Assert
        assertNotEquals(grade1, grade2);
    }

    @Test
    void testHashCode_EqualObjects() {
        // Arrange
        Grade grade1 = new Grade("Test", 2.0, 5);
        Grade grade2 = new Grade("Test", 2.0, 5);

        // Act & Assert
        assertEquals(grade1.hashCode(), grade2.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        Grade grade = new Grade("Mathematik", 1.7, 6);

        // Act
        String result = grade.toString();

        // Assert
        assertTrue(result.contains("Mathematik"));
        assertTrue(result.contains("1.7"));
        assertTrue(result.contains("6"));
        assertTrue(result.contains("Ja")); // isPassed
    }

    @Test
    void testToString_FailedGrade() {
        // Arrange
        Grade grade = new Grade("Test", 5.0, 5);

        // Act
        String result = grade.toString();

        // Assert
        assertTrue(result.contains("Nein")); // not passed
    }
}
