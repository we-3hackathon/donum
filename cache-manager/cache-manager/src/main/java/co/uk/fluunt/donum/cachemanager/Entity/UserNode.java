package co.uk.fluunt.donum.cachemanager.Entity;

public class UserNode extends Node {


    public UserNode(User user){
        super(user.getName());
        userData = user;
    }


}
