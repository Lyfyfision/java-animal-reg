package org.animalreg.engine;

import org.animalreg.exceptions.RuleProcessingException;
import org.animalreg.model.Animal;
import org.animalreg.model.Rule;
import org.mvel2.MVEL;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RuleEngine {
    private List<Rule> rules = Collections.emptyList();
    private static final Logger logger = Logger.getLogger(RuleEngine.class.getName());

    public void loadRules(List<Rule> rules) {
        this.rules = rules;
    }
    public void applyRules(List<Animal> animals) {
        if (rules.isEmpty()) {
            logger.log(Level.WARNING, "Нет загруженных правил для применения.");
            return;
        }

        for (Rule rule : rules) {
            int count = 0;
            for (Animal animal : animals) {
                try {
                    if (evaluateCondition(animal, rule.getCondition())) {
                        count++;
                    }
                } catch (RuleProcessingException e) {
                    logger.log(Level.SEVERE, "Ошибка при применении правила '{0}': {1}", new Object[]{rule.getName(), e.getMessage()});
                }
            }
            System.out.println(rule.getName() + ": " + count);
        }
    }

    private boolean evaluateCondition(Animal animal, String condition) throws RuleProcessingException {
        Map<String, Object> context = new HashMap<>();
        context.put("weight", animal.getWeight());
        context.put("height", animal.getHeight());
        context.put("type", animal.getType());
        try {
            return (Boolean) MVEL.eval(condition, context);
        } catch (Exception e) {
            throw new RuleProcessingException("Ошибка при оценке условия: " + condition, e);
        }
    }
}
