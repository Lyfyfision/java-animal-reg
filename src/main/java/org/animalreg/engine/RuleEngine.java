package org.animalreg.engine;

import lombok.extern.slf4j.Slf4j;
import org.animalreg.exceptions.RuleProcessingException;
import org.animalreg.model.Animal;
import org.animalreg.model.Rule;
import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class RuleEngine {
    private List<Rule> rules;

    public RuleEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public RuleEngineResult applyRules(List<Animal> animals) {
        if (rules.isEmpty()) {
            log.error("Нет загруженных правил для применения.");
            throw new RuleProcessingException("");
        }
        RuleEngineResult result = new RuleEngineResult();
        for (Rule rule : rules) {
            int count = 0;
            for (Animal animal : animals) {
                try {
                    if (evaluateCondition(animal, rule.condition())) {
                        count++;
                    }
                } catch (RuleProcessingException e) {
                    log.error("Ошибка при применении правил '{}': '{}'", rule.name(), e.getMessage());
                    throw e;
                }
            }
            result.setRuleCounter(rule.name(), count);
        }
        return result;
    }

    private boolean evaluateCondition(Animal animal, String condition) throws RuleProcessingException {
        Map<String, Object> context = new HashMap<>();
        context.put("weight", animal.getWeight());
        context.put("height", animal.getHeight());
        context.put("type", animal.getType());
        try {
            return (Boolean) MVEL.eval(condition, context);
        } catch (Exception e) {
            log.error("Ошибка при оценке условия: " + condition, e);
            throw new RuleProcessingException("");
        }
    }
}
