package org.animalreg;

import org.animalreg.engine.DataReader;
import org.animalreg.engine.RuleEngine;
import org.animalreg.engine.RuleReader;
import org.animalreg.model.Animal;
import org.animalreg.model.FilesPathConstants;
import org.animalreg.model.Rule;

import java.util.List;

public class AnimalRulesProcessor {

    public static void main(String[] args) {
        AnimalRulesProcessor processor = new AnimalRulesProcessor();
        processor.processRules();
    }

    public void processRules() {
        DataReader dataReader = new DataReader();
        List<Animal> animals = dataReader.readAnimals(FilesPathConstants.ANIMALS_FILE_PATH);

        RuleReader ruleReader = new RuleReader();
        List<Rule> rules = ruleReader.readRules(FilesPathConstants.RULES_FILE_PATH);

        RuleEngine ruleEngine = new RuleEngine(rules);
        System.out.println(ruleEngine.applyRules(animals));
    }
}
