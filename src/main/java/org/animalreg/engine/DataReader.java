package org.animalreg.engine;

import org.animalreg.exceptions.AnimalParseException;
import org.animalreg.model.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataReader {
    private static final Logger logger = Logger.getLogger(DataReader.class.getName());

    public List<Animal> readAnimals(String filePath) {
        List<Animal> animals = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                try {
                    String[] properties = line.split(",");
                    if (properties.length < 3) {
                        throw new AnimalParseException("Неверное количество свойств в строке: " + line);
                    }
                    String weight = properties[0].trim();
                    String height = properties[1].trim();
                    String type = properties[2].trim();
                    animals.add(new Animal(weight, height, type));
                } catch (AnimalParseException e) {
                    logger.log(Level.WARNING, "Ошибка при обработке строки: {0}. {1}",
                            new Object[]{line, e.getMessage()});
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при чтении файла: {0}. {1}", new Object[]{filePath, e.getMessage()});
        }
        return animals;
    }
}
