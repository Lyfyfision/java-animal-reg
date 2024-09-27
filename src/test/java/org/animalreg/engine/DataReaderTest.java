package org.animalreg.engine;

import org.animalreg.exceptions.AnimalParseException;
import org.animalreg.model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DataReaderTest {

    private DataReader dataReader;
    public static final String WRONG_ANIMAL_FILE = "src/test/resources/incorrect_animals.txt";
    public static final String ANIMAL_FILE = "src/test/resources/animals.txt";
    @BeforeEach
    public void setUp() {
        dataReader = new DataReader();
    }

    @Test
    @DisplayName("Throw exception with incorrect data")
    void testReadingAnimals_whenGivenIncorrectData_shouldThrowException() {
        assertThrows(AnimalParseException.class, () -> {
            dataReader.readAnimals(WRONG_ANIMAL_FILE);
        });
    }
    @Test
    @DisplayName("Read correct properties")
    void testReadingAnimals_whenGivenCorrectData_returnAnimalType() {
        List<Animal> animals = dataReader.readAnimals(ANIMAL_FILE);

        assertEquals("ВСЕЯДНОЕ", animals.get(0).getType(), "Third property of Animal should be returned");
    }
}
