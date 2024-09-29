

# Animal registration

This is a simple animal registration data-matching system that counts matches between data and required rules.


## Built with
- Java 17
- MVEL
- GraalVM
- JUnit 5
## Source Code Overview
The source code for the application is located in `/src/main/java/org/animalreg/`. All files required to run the application are in `/src/main/resources`. The following is an overview of how the components interact with each other:

1. Data Input: The system reads a JSON file containing rules and a .txt file with animal data using the `DataReader` and `RuleReader` classes. The expected format for the `animals.txt` file is as follows:

```
ЛЕГКОЕ,МАЛЕНЬКОЕ,ВСЕЯДНОЕ 
ТЯЖЕЛОЕ,МАЛЕНЬКОЕ,ТРАВОЯДНОЕ 
ТЯЖЕЛОЕ,НЕВЫСОКОЕ,ТРАВОЯДНОЕ
```
Each line contains comma-separated values representing the attributes of an animal: weight, height, and type.

The expected format for the rules specified in the `rules.json` file is as follows:
```
   [
     { "name": "Count Herbivores", "condition": "type == 'ТРАВОЯДНОЕ'" },
     { "name": "Count Small Herbivores or Carnivores", "condition": "type == 'ТРАВОЯДНОЕ' || type == 'ПЛОТОЯДНОЕ' && height == 'МАЛЕНЬКОЕ'" },
     { "name": "Count Omnivores not Tall", "condition": "type == 'ВСЕЯДНОЕ' && height != 'ВЫСОКОЕ'" }
   ]
```
Each rule includes a name and a condition to evaluate.

2. Rule Verification: The `RuleEngine` class verifies the rules and applies them to the animals using the MVEL or GraalVM libraries:
```
    return (Boolean) MVEL.eval(condition, context);

    if (evaluateCondition(animal, rule.condition())) {
        result.addSuccessfulRule(rule.name());
    }
```
3. Results: The results are stored in a hashmap inside the `RuleEngineResult` class, which shows the count of matches with the rules:
```
  System.out.println(ruleEngine.applyRules(animals));
```
For example, this is output to the console when the input data is correct:
```
  [Count Herbivores=3, Count Small Herbivores or Carnivores=5, Count Omnivores not Tall=2]

```

It is also worth noting that it is possible to implement this application without using external libraries such as MVEL for evaluation, by writing your own simple expression evaluator.




## Running Tests

To run tests, run the following command

```bash
  mvn test
```

