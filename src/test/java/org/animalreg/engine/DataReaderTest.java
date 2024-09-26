package org.animalreg.engine;

import org.animalreg.exceptions.AnimalParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
class DataReaderTest {

    private DataReader dataReader;
    public static final String ANIMAL_FILE = "src/test/resources/animal.txt";
    @BeforeEach
    public void setUp() {
        dataReader = new DataReader();
    }

    @Test
    @DisplayName("Throw exception with incorrect data")
    void testReadingAnimals_whenGivenIncorrectData_shouldThrowException() {
        assertThrows(AnimalParseException.class, () -> {
            dataReader.readAnimals(ANIMAL_FILE);
        });
    }
}
