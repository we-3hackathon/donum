package com;

import java.util.HashMap;

public class PostcodeTree {

    private TopNode root;

    public PostcodeTree(){
        // first top node
        root = new TopNode("UK");
    }

    public HashMap<String,Node> getUsersFromPostcode(String postcode){

        String[] split = postcode.split(" ");

        String outcode = split[0];
        String incode = split[1];


        // new outcode created, add to hashmap
        if(!root.contains(outcode)){
            return null;
        }

        Node outcodeNode = root.getChild(outcode);

        if(!outcodeNode.contains(incode)){
            return null;
        }

        Node incodeNode = outcodeNode.getChild(incode);

        return incodeNode.getCollection();
    }

    public int addNode(User user){
        // "LL4" "8YY" comes


        String postcode = user.getPostcode();

        String[] split = postcode.split(" ");

        String outcode = split[0];
        String incode = split[1];

        // new outcode created, add to hashmap
        if(!root.contains(outcode)){
            root.addChild(new OutcodeNode(outcode));
        }

        Node outcodeNode = root.getChild(outcode);

        if(!outcodeNode.contains(incode)){
            outcodeNode.addChild(new IncodeNode(incode));
        }

        Node incodeNode = outcodeNode.getChild(incode);

        incodeNode.addChild(new UserNode(user));

        /**
        System.out.println(root.getData());
        System.out.println(root.getCollection());
        System.out.println(outcodeNode.getData());
        System.out.println(outcodeNode.getCollection());
        System.out.println(incodeNode.getData());
        System.out.println(incodeNode.getCollection());
         **/

        return 1;
    }

    public void drawTree(){

        for(Node outcode : root.getCollection().values()){
            System.out.println();
            System.out.println();
            System.out.print("Outcode:" + outcode.getData());
            System.out.println();

            for(Node incode: outcode.getCollection().values()){
                System.out.println();
                System.out.print("incode:"+incode.getData());
                System.out.println();

                for(Node user: incode.getCollection().values()){
                    System.out.print("*user:" + user.getData());
                }
            }
        }
    }
}
