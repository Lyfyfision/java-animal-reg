package org.animalreg.engine;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RuleEngineResult {
    private Map<String, Integer> ruleCounts;

    public RuleEngineResult() {
        this.ruleCounts = new HashMap<>();
    }

    public void addSuccessfulRule(String rule) {
        ruleCounts.put(rule, ruleCounts.getOrDefault(rule, 0) + 1);
    }

    @Override
    public String toString() {
        return ruleCounts.entrySet().toString();
    }
}
