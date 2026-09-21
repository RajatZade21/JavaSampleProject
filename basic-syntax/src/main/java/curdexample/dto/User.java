package curdexample.dto;

import java.util.Objects;

public class User {

    private Long  id;
    private String name;
    private String email;

    public User(Long id,String name,String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String  email){
        this.email = email;
    }

    // Important for duplicate removal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User use = (User) o;
        return Objects.equals(id, use.id);
    }
}
