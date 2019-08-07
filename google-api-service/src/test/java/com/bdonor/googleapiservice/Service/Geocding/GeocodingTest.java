package com.bdonor.googleapiservice.Service.Geocding;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeocodingTest  {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loadAllAPIKeys () throws Exception{
        this.mockMvc.perform(get("/api-key/load/google-map")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("A")));
        this.mockMvc.perform(get("/api-key/load/google-places")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("A")));

    }

    @Test
    public void testCorrectCoordinate() throws Exception {
        this.mockMvc.perform(get("/geocoding/get-coordinates/25bamfordavenue/ha01na")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("51.5381428,-0.2903295")));
    }

    @Test
    public void testIncorrectCoordinates() throws Exception {
        this.mockMvc.perform(get("/geocoding/get-coordinates/1/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("error_message")));
    }
}

