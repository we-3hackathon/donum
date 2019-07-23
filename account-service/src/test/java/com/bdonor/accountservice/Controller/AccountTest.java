package com.bdonor.accountservice.Controller;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void loadDynamoKeys() throws Exception {
    }

    @Test
    public void testRegisterUser() throws Exception {
    }

    @Test
    public void testUserCanLogin() throws Exception {
    }

    @Test
    public void testRetrieveUsersInDB() throws Exception {
    }

    @Test
    public void testDeleteUser() throws Exception {
    }

    @Test
    public void testUpdateUser() throws Exception {
    }

    @Test
    public void testGetSpecificUser() throws Exception {
    }

}
