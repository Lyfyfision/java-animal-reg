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

    public void setRuleCounter(String ruleName, int count) {
        ruleCounts.put(ruleName, count);
    }

    @Override
    public String toString() {
        return ruleCounts.entrySet().toString();
    }
}
