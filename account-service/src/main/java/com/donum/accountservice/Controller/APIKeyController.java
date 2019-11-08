package com.donum.accountservice.Controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.donum.accountservice.Config.DynamoDBConfig;
import com.donum.accountservice.Enum.EnumAPIKey;
import com.donum.accountservice.Repository.DynamoRepo;
import com.donum.accountservice.Security.KeyHelper;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api-key")
@RefreshScope
public class APIKeyController extends BaseController {

    KeyHelper _keyContainer = new KeyHelper();
    public static DynamoDBMapper _singleDynamoMapper;
    public static DynamoRepo _singleDynamoRepo;

    @GetMapping(value = "/load/dynamo-access")
    @ResponseBody
    private String dynamoAccessKey(){

        return _keyContainer.loadDynamoAccessKey();
    }

    @GetMapping(value = "/load/dynamo-secret")
    @ResponseBody
    private String dynamoSecretKey(){

        return _keyContainer.loadDynamoSecretKey();
    }

    @GetMapping(value = "/load/gmail/password")
    @ResponseBody
    public String gmailDefaultPassword(){

        return _keyContainer.loadEmailPassword();
    }
	
    @GetMapping(value = "/load/all")
    @ResponseBody
    private String loadAll(){

        return _keyContainer.loadDynamoSecretKey() + " " + _keyContainer.loadDynamoAccessKey() + " " + gmailDefaultPassword() ;
    }

    @GetMapping(value = "/status")
    @ResponseBody
    public String checkKeyStatus(){
        loadAll();

        // new single instance created of DynamoDB
        _singleDynamoMapper = new DynamoDBConfig().mapper();
        _singleDynamoRepo = new DynamoRepo();

        return "Your Access Key:" + ((EnumAPIKey.DYNAMO_KEY.toString() != "" ) ? " Set" :"Not set" )
                + " <p></p> "
                + "Your Secret Key:"
                + ((EnumAPIKey.DYNAMO_KEY.toString() != "" ) ? " Set" :"Not set" )
                + " <p></p> "
                + ((!_singleDynamoRepo.getAllUsers().isEmpty())? "Dynamo connection is Online" : "Dynamo connection is Offline") ;
    }

    @Override
    public void loadController() {
        _controllerName = "APIKeyController";
    }
}
