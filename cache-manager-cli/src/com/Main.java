package com;


import java.util.Random;

public class Main {

    public static void main(String[] args) {

        PostcodeTree tree = new PostcodeTree();

        User user = new User("abc","o+","LL4 8YY");
        User user1 = new User("abd","o+","LL4 8YY");
        User user2 = new User("abf","o+","LL4 8YZ");
        User user3 = new User("abh","o+","LL1 3YY");
        User user4 = new User("ab3","o+","LL4 8YA");
        User user5 = new User("ab5","o+","LL1 8YY");
        User user6 = new User("ab7","o+","LL4 8YY");

        System.out.println(tree.addNode(user));
        System.out.println(tree.addNode(user1));
        System.out.println(tree.addNode(user2));
        System.out.println(tree.addNode(user3));
        System.out.println(tree.addNode(user4));
        System.out.println(tree.addNode(user5));

        /*
        Random rn = new Random();

        int maximum = 100;
        int minimum = 1;

        int n = maximum - minimum + 1;

        for(int i = 0; i<= 900000; i++){
            int l = rn.nextInt() % n;
            int randomNum =  minimum + l;
            System.out.println(tree.addNode(new User(String.valueOf(randomNum),"o+",String.valueOf(minimum + (rn.nextInt() % n)) + " " + String.valueOf(minimum + rn.nextInt() % n))));

        }
        */
        tree.drawTree();
        //System.out.println(tree.getUsersFromPostcode("1 2"));



    }
}
