
# Animal registration

As mentioned above, this is a simple animal registration data-matching system that is based on counting matches between data and required rules


## Built with
- Java 17
- MVEL
- GraalVM
- JUnit 5
## Source Code Review
Source code for the example is located in /src/main/java/org/animalreg/engine. All the files required to run the app reside in /src/main/resources The followings overview of how it communicates with each other:

1. Take a JSON file with rules and .txt file with animals than 
read them with DataReader and RuleReader classes.
```bash
  ...
  animals.add(new Animal(Arrays.asList(properties)));

  ....
  return gson.fromJson(reader, new TypeToken<List<Rule>>() {
  }.getType());
```
2. Verification of rules and applying them on animals in RuleEngine.class with MVEL or GraalVM libs:
```bash
  ...
  return (Boolean) MVEL.eval(condition, context);

  ...
  if (evaluateCondition(animal, rule.condition())) {
  result.addSuccessfulRule(rule.name());
  }
```
3. Take a result (A hashmap inside RuleEngineResult.class) and show a counter of matches with rules
```bash
  System.out.println(ruleEngine.applyRules(animals));
```
For example, this is output to the console when the input data is correct:
```bash
  [Count Herbivores=3, Count Small Herbivores or Carnivores=5, Count Omnivores not Tall=2]

```


It is also worth noting that it is possible to make such a program without using external libraries such MVEL for evaluating, by writing your own simple Expression Evaluator. 



## Running Tests

To run tests, run the following command

```bash
  mvn test
```

