package dev.coppola.librarian.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class User {
    protected long id;
    protected String name;
    protected char[] password;
    protected String email;
}
