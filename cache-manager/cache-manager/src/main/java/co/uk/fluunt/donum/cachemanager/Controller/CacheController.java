package co.uk.fluunt.donum.cachemanager.Controller;


import co.uk.fluunt.donum.cachemanager.Entity.Node;
import co.uk.fluunt.donum.cachemanager.Entity.User;
import co.uk.fluunt.donum.cachemanager.Entity.UserNode;
import co.uk.fluunt.donum.cachemanager.InternalService.AccountServiceHelper;
import co.uk.fluunt.donum.cachemanager.Tree.PostcodeTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController extends BaseController{

    static PostcodeTree tree;
    @Override
    public void loadController() {
            _controllerName = "CacheController";
    }
	
	@CrossOrigin()
    @GetMapping(value = "/load")
    public ResponseEntity<String> loadAllToMemory() {

		String allUsers = AccountServiceHelper.requestAllUsers();
		
		 tree = new PostcodeTree(allUsers);
		
		tree.processJsonData();
		
		return new ResponseEntity<>("Success",HttpStatus.OK);
		
    }

    @CrossOrigin()
    @GetMapping(value = "/search/{postcode}")
    public ResponseEntity<String> searchForUsers(@PathVariable String postcode) {

        StringBuilder sb = new StringBuilder();

        for(Node user: tree.getUsersFromPostcode(postcode).values()){
            sb.append(user.getUserData().getName() + " " + user.getUserData().getBlood());
        }

        return new ResponseEntity<>(sb.toString(),HttpStatus.OK);

    }
	
	
}