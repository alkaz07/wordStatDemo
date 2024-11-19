package com.example.wordStatDemo;

import java.util.Objects;

public class UserWord {
     String username;
     String word;

    public String getUsername() {
        return username;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "UserWord{" +
                "username='" + username + '\'' +
                ", word='" + word + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWord userWord = (UserWord) o;
        return Objects.equals(username, userWord.username) && Objects.equals(word, userWord.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, word);
    }

    public UserWord(String username, String word) {
        this.username = username;
        this.word = word;
    }
}
