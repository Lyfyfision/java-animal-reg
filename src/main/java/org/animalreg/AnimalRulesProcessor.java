package org.animalreg;

import org.animalreg.engine.DataReader;
import org.animalreg.engine.RuleEngine;
import org.animalreg.engine.RuleReader;
import org.animalreg.exceptions.AnimalParseException;
import org.animalreg.model.Animal;
import org.animalreg.model.FilesPath;
import org.animalreg.model.Rule;

import java.util.List;

public class AnimalRulesProcessor {

    public static void main(String[] args) throws AnimalParseException {
        AnimalRulesProcessor processor = new AnimalRulesProcessor();
        processor.processRules();
    }

    public void processRules() throws AnimalParseException {
        DataReader dataReader = new DataReader();
        List<Animal> animals = dataReader.readAnimals(FilesPath.ANIMALS_FILE_PATH);

        RuleReader ruleReader = new RuleReader();
        List<Rule> rules = ruleReader.readRules(FilesPath.RULES_FILE_PATH);

        RuleEngine ruleEngine = new RuleEngine();
        ruleEngine.loadRules(rules);
        ruleEngine.applyRules(animals);
    }
}
