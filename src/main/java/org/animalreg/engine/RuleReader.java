package org.animalreg.engine;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.animalreg.model.Rule;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RuleReader {
    private static final Logger logger = Logger.getLogger(RuleReader.class.getName());

    public List<Rule> readRules(String filePath) {
            try (FileReader reader = new FileReader(filePath)) {
                Gson gson = new Gson();
                return gson.fromJson(reader, new TypeToken<List<Rule>>() {
                }.getType());
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Ошибка при чтении файла правил: {0}. {1}", new Object[]{filePath, e.getMessage()});
                return Collections.emptyList();
            } catch (JsonSyntaxException e) {
                logger.log(Level.SEVERE, "Ошибка синтаксиса JSON в файле: {0}. {1}", new Object[]{filePath, e.getMessage()});
                return Collections.emptyList();
            }
    }
}
