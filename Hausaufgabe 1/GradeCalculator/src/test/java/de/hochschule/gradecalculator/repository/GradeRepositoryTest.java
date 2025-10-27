package de.hochschule.gradecalculator.repository;

import de.hochschule.gradecalculator.model.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests für das GradeRepository
 */
class GradeRepositoryTest {

    private GradeRepository repository;

    @BeforeEach
    void setUp() {
        repository = new GradeRepository();
    }

    @Test
    void testSave() {
        // Arrange
        Grade grade = new Grade("Mathematik", 1.7, 6);

        // Act
        Grade saved = repository.save(grade);

        // Assert
        assertNotNull(saved);
        assertEquals(grade, saved);
        assertEquals(1, repository.count());
    }

    @Test
    void testSaveNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> repository.save(null));
    }

    @Test
    void testFindAll() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));
        repository.save(new Grade("Programmierung", 1.3, 8));

        // Act
        List<Grade> grades = repository.findAll();

        // Assert
        assertEquals(2, grades.size());
    }

    @Test
    void testFindAll_EmptyRepository() {
        // Act
        List<Grade> grades = repository.findAll();

        // Assert
        assertTrue(grades.isEmpty());
    }

    @Test
    void testFindByCourseName() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));
        repository.save(new Grade("Programmierung", 1.3, 8));

        // Act
        List<Grade> found = repository.findByCourseName("Mathematik");

        // Assert
        assertEquals(1, found.size());
        assertEquals("Mathematik", found.get(0).getCourseName());
    }

    @Test
    void testFindByCourseName_CaseInsensitive() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));

        // Act
        List<Grade> found = repository.findByCourseName("mathematik");

        // Assert
        assertEquals(1, found.size());
    }

    @Test
    void testFindByCourseName_NotFound() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));

        // Act
        List<Grade> found = repository.findByCourseName("Physik");

        // Assert
        assertTrue(found.isEmpty());
    }

    @Test
    void testFindByCourseName_Null() {
        // Act
        List<Grade> found = repository.findByCourseName(null);

        // Assert
        assertTrue(found.isEmpty());
    }

    @Test
    void testFindOneByCourseName() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));

        // Act
        Optional<Grade> found = repository.findOneByCourseName("Mathematik");

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Mathematik", found.get().getCourseName());
    }

    @Test
    void testFindOneByCourseName_NotFound() {
        // Act
        Optional<Grade> found = repository.findOneByCourseName("Mathematik");

        // Assert
        assertFalse(found.isPresent());
    }

    @Test
    void testDelete() {
        // Arrange
        Grade grade = new Grade("Mathematik", 1.7, 6);
        repository.save(grade);

        // Act
        boolean deleted = repository.delete(grade);

        // Assert
        assertTrue(deleted);
        assertEquals(0, repository.count());
    }

    @Test
    void testDelete_NotExisting() {
        // Arrange
        Grade grade = new Grade("Mathematik", 1.7, 6);

        // Act
        boolean deleted = repository.delete(grade);

        // Assert
        assertFalse(deleted);
    }

    @Test
    void testDelete_Null() {
        // Act
        boolean deleted = repository.delete(null);

        // Assert
        assertFalse(deleted);
    }

    @Test
    void testDeleteByCourseName() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));

        // Act
        boolean deleted = repository.deleteByCourseName("Mathematik");

        // Assert
        assertTrue(deleted);
        assertEquals(0, repository.count());
    }

    @Test
    void testDeleteByCourseName_NotFound() {
        // Act
        boolean deleted = repository.deleteByCourseName("Mathematik");

        // Assert
        assertFalse(deleted);
    }

    @Test
    void testDeleteAll() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));
        repository.save(new Grade("Programmierung", 1.3, 8));

        // Act
        repository.deleteAll();

        // Assert
        assertEquals(0, repository.count());
        assertTrue(repository.isEmpty());
    }

    @Test
    void testCount() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));
        repository.save(new Grade("Programmierung", 1.3, 8));
        repository.save(new Grade("Datenbanken", 2.0, 5));

        // Act
        int count = repository.count();

        // Assert
        assertEquals(3, count);
    }

    @Test
    void testIsEmpty_True() {
        // Act & Assert
        assertTrue(repository.isEmpty());
    }

    @Test
    void testIsEmpty_False() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));

        // Act & Assert
        assertFalse(repository.isEmpty());
    }

    @Test
    void testFindAllPassed() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));
        repository.save(new Grade("Programmierung", 4.0, 8));
        repository.save(new Grade("Datenbanken", 5.0, 5));

        // Act
        List<Grade> passed = repository.findAllPassed();

        // Assert
        assertEquals(2, passed.size());
        assertTrue(passed.stream().allMatch(Grade::isPassed));
    }

    @Test
    void testFindAllFailed() {
        // Arrange
        repository.save(new Grade("Mathematik", 1.7, 6));
        repository.save(new Grade("Programmierung", 4.0, 8));
        repository.save(new Grade("Datenbanken", 5.0, 5));

        // Act
        List<Grade> failed = repository.findAllFailed();

        // Assert
        assertEquals(1, failed.size());
        assertEquals("Datenbanken", failed.get(0).getCourseName());
        assertFalse(failed.get(0).isPassed());
    }
}
