package com.example.gfgbasics.architecture.mvp.login;

import java.util.ArrayList;

public class LoginModel implements LoginContract.Model{

    private final ArrayList<User> list = new ArrayList<>();

    @Override
    public boolean login(String email, String password) {
        return checkUser(email, password);
    }

    private boolean checkUser(String email, String password){
        list.add(new User("x@mail.com", "x", "x"));
        list.add(new User("a@mail.com", "a", "a"));
        list.add(new User("s@mail.com", "s", "s"));
        list.add(new User("d@mail.com", "d", "d"));
        list.add(new User("f@mail.com", "f", "f"));
        list.add(new User("g@mail.com", "g", "g"));
        list.add(new User("h@mail.com", "h", "h"));

        for (User user : list){
            if ((user.getEmail().equals(email) || user.getUsername().equals(email)) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
