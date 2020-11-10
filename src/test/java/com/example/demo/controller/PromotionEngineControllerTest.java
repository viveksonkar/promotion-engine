package com.example.demo.controller;

import com.example.demo.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PromotionEngineController.class)
public class PromotionEngineControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CheckoutService checkoutService;

    @Test
    public void testCalculatePromotion() throws Exception {

        mvc.perform(get("/promotion/calculate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
