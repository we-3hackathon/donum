package co.uk.fluunt.donum.cachemanager.Tree;


import co.uk.fluunt.donum.cachemanager.Entity.*;
import org.json.JSONArray;

import java.util.HashMap;

public class PostcodeTree {

    private TopNode root;
	private String _jsonData;

    public PostcodeTree(String data){
        // first top node
        root = new TopNode("UK");
		_jsonData = data;
    }

    public HashMap<String, Node> getUsersFromPostcode(String postcode){

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
	
	public void processJsonData(){

        String name;
        String postcode;
        String bloodGroup;

		try {
            JSONArray allUsers = new JSONArray(_jsonData);

            for (int i = 0; i < allUsers.length(); i++) {
                name = allUsers.getJSONObject(i).getString("firstName");
                postcode = allUsers.getJSONObject(i).getString("postcode");
                bloodGroup = allUsers.getJSONObject(i).getString("bloodGroup");
				addNode(new User(name, bloodGroup, postcode));
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("#1");
        }
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
