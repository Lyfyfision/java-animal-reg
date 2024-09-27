package org.animalreg.engine;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.animalreg.model.Rule;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@Slf4j
public class RuleReader {
    public List<Rule> readRules(String filePath) {
            try (FileReader reader = new FileReader(filePath)) {
                Gson gson = new Gson();
                return gson.fromJson(reader, new TypeToken<List<Rule>>() {
                }.getType());
            } catch (IOException e) {
                log.error("Ошибка при чтении файла правил: {}. {}", filePath, e.getMessage());
                return Collections.emptyList();
            } catch (JsonSyntaxException e) {
                log.error("Ошибка синтаксиса JSON в файле: {}. {}", filePath, e.getMessage());
                return Collections.emptyList();
            }
    }
}
