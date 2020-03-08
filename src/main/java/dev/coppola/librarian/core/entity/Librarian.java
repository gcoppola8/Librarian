package dev.coppola.librarian.core.entity;

import lombok.Data;

import java.util.Arrays;

@Data
public class Librarian extends User {
    @Override
    public String toString() {
        return "Librarian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password=" + Arrays.toString(password) +
                ", email='" + email + '\'' +
                '}';
    }
}
