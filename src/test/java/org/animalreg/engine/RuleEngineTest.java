package org.animalreg.engine;

import org.animalreg.exceptions.RuleProcessingException;
import org.animalreg.model.Animal;
import org.animalreg.model.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RuleEngineTest {
    private RuleReader ruleReader;
    private DataReader dataReader;
    private RuleEngine ruleEngine;
    private List<Animal> animals;
    private List<Rule> rules;

    public static final String ANIMALS_FILE = "src/test/resources/animals.txt";
    public static final String RULES_FILE = "src/test/resources/rules.json";
    public static final String WRONG_RULES_FILE = "src/test/resources/incorrect_rules.json";

    @BeforeEach
    public void setUp() {
        ruleReader = new RuleReader();
        dataReader = new DataReader();
        animals = dataReader.readAnimals(ANIMALS_FILE);
        rules = ruleReader.readRules(RULES_FILE);
        ruleEngine = new RuleEngine(rules);
    }

    @Test
    @DisplayName("Rule engine counting matches properly")
    void testCorrectApplyingRules_whenGivenCorrectData_returnNumberOfMatchesToRules() {
        RuleEngineResult result = ruleEngine.applyRules(animals);

        assertEquals(2, result.getRuleCounts().get(rules.get(2).name()),
                "Counter of the third rule should be returned");
    }

    @Test
    @DisplayName("RuleEngine throw exception with wrong rules")
    void testApplyingRules_whenGivenIncorrectRules_shouldThrowException() {
        List<Rule> wrongRules = ruleReader.readRules(WRONG_RULES_FILE);
        RuleEngine wrongEngine = new RuleEngine(wrongRules);

        assertThrows(RuleProcessingException.class, () -> {
            wrongEngine.applyRules(animals);
        }, "RuleProcessingException should be thrown here");
    }
}