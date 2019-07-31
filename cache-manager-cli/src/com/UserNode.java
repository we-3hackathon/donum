package com;

public class UserNode extends Node{

    private User userData;

    public UserNode(User user){
        super(user.getName());
        userData = user;
    }
}
