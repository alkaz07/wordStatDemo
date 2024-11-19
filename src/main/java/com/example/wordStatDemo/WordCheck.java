package com.example.wordStatDemo;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class WordCheck {
   // List<String> strings;
    Map<String, Integer> map = new LinkedHashMap<>();
    List<UserWord> userWords= new ArrayList<>();

    public boolean contains(String w){
        return map.containsKey(w);
    }

    public int saveWord(String w){
        int x = map.getOrDefault(w, 0);
        map.put(w, x+1);
        return x;
    }

    public int saveWord(String u, String w){
        userWords.add(new UserWord(u, w));
        return saveWord(w);
    }
    public List<String> getWordsByUser(String u){
        return userWords.stream().filter(x->x.username.equals(u)).map(x->x.word).toList();
    }

    public List<String> getUsersByWord(String w){
        return userWords.stream().filter(x->x.word.equals(w)).map(x->x.username).toList();
    }

    public void save() throws IOException {
        String fname = "user_words_data.txt";
        List<String> lines = userWords.stream().map(y -> y.getUsername()+";"+y.getWord()).toList();
        Path file = Paths.get(fname);
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    public void load() throws IOException {
        String fname = "user_words_data.txt";
        Path file = Paths.get(fname);
        List<String> lines =Files.readAllLines(file);
        lines.stream().map(z->{
                                String[] mas = z.split(";");
                                return new UserWord(mas[0], mas[1]);
                            }).forEach(elem->saveWord(elem.getUsername(), elem.getWord()));
    }
}
