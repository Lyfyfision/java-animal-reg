package org.animalreg.engine;

import lombok.extern.slf4j.Slf4j;
import org.animalreg.exceptions.AnimalParseException;
import org.animalreg.model.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
public class DataReader {
    public List<Animal> readAnimals(String filePath) throws AnimalParseException {
        List<Animal> animals = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] properties = line.split(",");
                if (properties.length != 3) {
                    log.error("Неверное количество свойств в строке: " + line);
                    throw new AnimalParseException("");
                }
                animals.add(new Animal(Arrays.asList(properties)));
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении файла: " + e.getMessage(), e);
            throw new AnimalParseException("");
        }
        return animals;
    }
}
