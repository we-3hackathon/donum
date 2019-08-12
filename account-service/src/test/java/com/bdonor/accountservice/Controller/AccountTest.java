package com.bdonor.accountservice.Controller;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAccountController() throws Exception {
        this.mockMvc.perform(get("/test")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Controller")));
    }

    @Before
    public void loadDynamoKeys() throws Exception { // Passed
        this.mockMvc.perform(get("/api-key/status")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Online")));
    }

//    @Test
//    public void testRegisterUser() throws Exception {
//        this.mockMvc.perform(get("/create/A-/Jack/Williams/newEmail/jack1234/29Bamfordavenue/ha01na")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("User added to Database")));
//    }
//
//    @Test
//    public void testRegisterUser() throws Exception {
//        this.mockMvc.perform(get("/create/A-/Man/Williams/test@gmail.com/jack1234/29Bamfordavenue/ha01na")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("User added to Database")));
//    }
//
//    @Test
//    public void testRepeatedUserRegister() throws  Exception {
//        this.mockMvc.perform(get("/create/A-/Jack/Williams/williams2@gmail.com/jack1234/29Bamfordavenue/ha01na")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Email in use. Try another email")));
//    }
//
//    @Test
//    public void testRegisterValidationRegister() throws Exception {
//        this.mockMvc.perform(get("/create/A-/Jack/Williams/williams2@gmail.com/jack1234/29Bamfordavenue/===---")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Postcode not recognised")));
//    }

    @Test
    public void testUserCanLogin() throws Exception {
        this.mockMvc.perform(get("/login/Jack/williams2@gmail.com/jack1234")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Jack")));
    }

    @Test
    public void testUserLoginFailed() throws Exception {
        this.mockMvc.perform(get("/login/Jack/williamscxzcxzcs/jack1234")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Login Failed")));
    }

    @Test
    public void testRetrieveUsersInDB() throws Exception {
        this.mockMvc.perform(get("/get-all")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("id")));
    }

//    @Test
//    public void testDeleteUser() throws Exception {
//        this.mockMvc.perform(delete("/delete/Jack/williams2@gmail.com")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Deleted")));
//    }
//
//    @Test
//    public void testDeleteUserNotFound() throws Exception {
//        this.mockMvc.perform(delete("/delete/Jack/williams2@gmail")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("User not found")));
//    }

    @Test
    public void testUsersInRange() throws Exception {
        this.mockMvc.perform(get("/usersInRange/-0.39426819999999996/51.5338877/1000")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sharjeel"))); // Users not being collected from APIKeyController._singleDynamoRepo.getAllUsers();
    }

    @Test
    public void testGetSpecificUser() throws Exception {
        this.mockMvc.perform(get("/getUser/Jack/williams2@gmail.com")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Jack")));
        this.mockMvc.perform(get("/getUser/Jack/williams2@gmail")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("User not found")));
    }

    @Test
    public void testUpdatePassword() throws Exception {
        this.mockMvc.perform(get("/updatepassword/test/test@gmail.com/test123")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("success"))); // Users not being collected from APIKeyController._singleDynamoRepo.getAllUsers();
    }

}
