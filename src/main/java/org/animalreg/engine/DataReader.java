package org.animalreg.engine;

import org.animalreg.exceptions.AnimalParseException;
import org.animalreg.model.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public List<Animal> readAnimals(String filePath) throws AnimalParseException {
        List<Animal> animals = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] properties = line.split(",");
                if (properties.length != 3) {
                    throw new AnimalParseException("Неверное количество свойств в строке: " + line);
                }
                String weight = properties[0].trim();
                String height = properties[1].trim();
                String type = properties[2].trim();
                animals.add(new Animal(weight, height, type));
            }
        } catch (IOException e) {
            throw new AnimalParseException("Ошибка при чтении файла: " + e.getMessage(), e);
        }
        return animals;
    }
}
