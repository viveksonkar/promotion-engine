package com.example.demo.controller;

import com.example.demo.models.CheckOutItem;
import com.example.demo.service.CheckoutService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PromotionEngineController.class)
public class PromotionEngineControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectmapper;

    @MockBean
    CheckoutService checkoutService;

    @Test
    public void getPrice() throws Exception {
        mvc.perform(post("/promotion/getPrice")
                .content(getBasket())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    public String getBasket() throws JsonProcessingException {
        List<CheckOutItem> checkOutItems = new ArrayList<>();

        CheckOutItem checkOutItemA = new CheckOutItem();
        checkOutItemA.setSku("A");
        checkOutItemA.setQuantity(5);

        CheckOutItem checkOutItemB = new CheckOutItem();
        checkOutItemB.setSku("B");
        checkOutItemB.setQuantity(5);

        CheckOutItem checkOutItemC = new CheckOutItem();
        checkOutItemC.setSku("C");
        checkOutItemC.setQuantity(1);

        CheckOutItem checkOutItemD = new CheckOutItem();
        checkOutItemD.setSku("D");
        checkOutItemD.setQuantity(1);
        checkOutItems.add(checkOutItemA);
        checkOutItems.add(checkOutItemB);
        checkOutItems.add(checkOutItemC);
        checkOutItems.add(checkOutItemD);

        return objectmapper.writeValueAsString(checkOutItems);
    }
}
