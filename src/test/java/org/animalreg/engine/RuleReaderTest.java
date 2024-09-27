package org.animalreg.engine;

import org.animalreg.model.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RuleReaderTest {
    private RuleReader ruleReader;
    public static final String RULE_FILE = "src/test/resources/rules.json";

    @BeforeEach
    public void setUp() {
        ruleReader = new RuleReader();
    }
    @Test
    @DisplayName("Rules reader read properly")
    void testReadingRules_whenGivenCorrectJson_returnRuleName() {
        List<Rule> rules = ruleReader.readRules(RULE_FILE);

        assertEquals("Count Herbivores", rules.get(0).name(), "Name of first rule should be returned");
    }
}