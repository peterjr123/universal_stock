package com.universalstock.yml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.util.Map;

public class YamlConfigManager {
    public YamlConfigManager() {

    }

    @SuppressWarnings("unchecked")
    public Object loadYaml(Class<?> clazz, File yamlFile) {
        try (FileInputStream inputStream = new FileInputStream(yamlFile)) {
            Yaml yaml = new Yaml(new Constructor(clazz, new LoaderOptions()));
            return yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveYaml(Class<?> clazz, Object object, File yamlFile) {
        try (PrintWriter writer = new PrintWriter(yamlFile)) {
            DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

            Representer representer = new Representer(options);
            representer.getPropertyUtils().setSkipMissingProperties(true);
            representer.addClassTag(clazz, Tag.MAP);

            Yaml yaml = new Yaml(new Constructor(Map.class, new LoaderOptions()), representer, options);
            yaml.dump(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
