package co.uk.fluunt.donum.cachemanager.StressTest;

import co.uk.fluunt.donum.cachemanager.Tree.PostcodeTree;
import co.uk.fluunt.donum.cachemanager.Entity.User;

import java.util.ArrayList;
import java.util.Random;

public class Benchmark {

    private ArrayList<String> filtered;
    private ArrayList<String> users;
    private PostcodeTree tree;
    private ArrayList<Double> avgCaseThree;
    private ArrayList<Double> avgCaseFour;
    private int initial ;

    public Benchmark(){
        filtered = new ArrayList<>();

        filtered.add("1 2");
        filtered.add("2 2");
        filtered.add("4 2");
        filtered.add("15 2");
        filtered.add("18 12");
        filtered.add("17 22");
        filtered.add("13 2");

        avgCaseThree = new ArrayList<>();
        avgCaseFour = new ArrayList<>();
    }

    public void useCase(int useCase){

        switch(useCase){
            case 1: loadIntoBinaryMemory(); break;
            case 2: loadIntoTemporaryMemory(); break;
            case 3: performBinarySearch(); break;
            case 4: performQuadraticSearch(); break;
        }
    }

    private void loadIntoBinaryMemory(){
        initial = 0;
        tree = new PostcodeTree("");

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


        Random rn = new Random();

        int maximum = 100;
        int minimum = 1;

        int n = maximum - minimum + 1;

        for(int i = 0; i<= 1000000; i++){
            int l = rn.nextInt() % n;
            int randomNum =  minimum + l;
            tree.addNode(new User(String.valueOf(randomNum),"o+",String.valueOf(minimum + (rn.nextInt() % n)) + " " + String.valueOf(minimum + rn.nextInt() % n)));

        }
    }

    private void loadIntoTemporaryMemory(){
        initial = 0;

        users = new ArrayList<>();

        Random rn = new Random();

        int maximum = 26;
        int minimum = 1;

        int n = maximum - minimum + 1;

        for(int a = 0; a<= 1000000; a++) {

            int l = rn.nextInt() % n;
            int randomNum =  minimum + l;

            users.add(String.valueOf(minimum + (rn.nextInt() % n)) + " " + String.valueOf(minimum + rn.nextInt() % n));
        }

    }

    private void performBinarySearch(){


        for(int i = 0; i<= 100; i++) {
            long startTime = System.nanoTime();

            for (String postcode : filtered) {

                if (tree.getUsersFromPostcode(postcode) != null) {
                    tree.getUsersFromPostcode(postcode);
                }
            }

            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            double seconds = (double) totalTime / 1_000_000_000.0;
            System.out.println("search in:" + seconds);
            if (initial > 4) {
                avgCaseThree.add(seconds);
                calculateAverage(avgCaseThree);
            }
            initial++;
        }

    }

    private void performQuadraticSearch(){

        for(int i = 0; i<= 100; i++) {
            long startTime = System.nanoTime();

            for (String user : users) {
                for (String postcode : filtered) {

                    if (user.equals(postcode)) {
                    }
                }
            }

            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            double seconds = (double) totalTime / 1_000_000_000.0;
            System.out.println("search in:" + seconds);
            if (initial > 4) {
                avgCaseFour.add(seconds);
                calculateAverage(avgCaseFour);
            }
            initial++;
        }
    }

    private void calculateAverage(ArrayList<Double> values){

        double sum = 0;

        for(Double value: values){
            sum += value;
        }

        System.out.println("************Overall: " + sum / (values.size() + 1));
    }

    //test case: compare 900k rows with filtered postcodes

    //algorithm
    //900k rows
    //search: 0.0001124
    //search: 0.0000153
    //search: 0.0000282
    //470mb ram
    //AVG1: 0.00005196

    //O(N)2
    //900k rows
    //search: 0.0367
    //search: 0.01167
    //search: 0.01045
    //no extra memory
    //temporary memory allocation: 400+mb
    //AVG2:0.0242

    // time difference: AVG2/AVG1 =- 465 times faster!!
}
