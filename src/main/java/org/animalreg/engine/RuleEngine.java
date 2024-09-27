package org.animalreg.engine;

import lombok.extern.slf4j.Slf4j;
import org.animalreg.exceptions.RuleProcessingException;
import org.animalreg.model.Animal;
import org.animalreg.model.Rule;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
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
            for (Animal animal : animals) {
                try {
                    if (evaluateCondition(animal, rule.condition())) {
                        result.addSuccessfulRule(rule.name());
                    }
                } catch (RuleProcessingException e) {
                    log.error("Ошибка при применении правил '{}': '{}'", rule.name(), e.getMessage());
                    throw e;
                }
            }
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

    /**
     * This is an alternative method for evaluating conditions based on GraalVM lib and JS.
     *
     * @param animal
     * @param condition
     * @return
     * @throws RuleProcessingException
     */
    private boolean evaluateConditionWithJs(Animal animal, String condition) throws RuleProcessingException {
        try (Context ctx = Context.create()) {
            Value bindings = ctx.getBindings("js");

            bindings.putMember("weight", animal.getWeight());
            bindings.putMember("height", animal.getHeight());
            bindings.putMember("type", animal.getType());

            Value result = ctx.eval("js", condition);
            return result.asBoolean();
        } catch (Exception e) {
            throw new RuleProcessingException("Ошибка при оценке условия: " + condition, e);
        }
    }
}
