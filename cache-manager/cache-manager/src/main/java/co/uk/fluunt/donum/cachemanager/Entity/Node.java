package co.uk.fluunt.donum.cachemanager.Entity;

import java.util.HashMap;

public class Node {

    private HashMap<String,Node> _children;
    private String _data;


    public Node(String data){
        _data = data;
        _children = new HashMap<>();
    }

    public void addChild(Node node){

        _children.put(node._data, node);
    }

    public HashMap<String,Node> getCollection(){

        return _children;
    }

    public boolean contains(String code){

        return _children.containsKey(code);
    }

    public Node getChild(String code){

        return _children.get(code);
    }

    public String getData(){
        return _data;
    }
}
