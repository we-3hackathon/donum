package co.uk.fluunt.donum.cachemanager.Controller;


import co.uk.fluunt.donum.cachemanager.InternalService.AccountServiceHelper;
import co.uk.fluunt.donum.cachemanager.Tree.PostcodeTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController extends BaseController{

    @Override
    public void loadController() {
            _controllerName = "CacheController";
    }
	
	@CrossOrigin()
    @GetMapping(value = "/load") // Working
    public ResponseEntity<String> loadAllToMemory() {

		String allUsers = AccountServiceHelper.requestAllUsers();
		
		PostcodeTree tree = new PostcodeTree(allUsers);
		
		tree.processJsonData();
		
		return new ResponseEntity<>("Success",HttpStatus.OK);
		
    }

	
	
}