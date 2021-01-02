package com.snapmine.SnapMineApi.config;


import com.google.gson.Gson;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class BeanConfig {

    private Map<String,String> secret;

    @Autowired
    public BeanConfig(ApplicationArguments args) {

        String path = args.getSourceArgs()[0];
        StringBuilder sb = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine())
                sb.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        this.secret = g.fromJson(sb.toString(), Map.class);


    }

    @Bean
    public Function<String,String> factor(){
        return name->secret.get(name);
    }


}
