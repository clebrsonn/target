package br.com.hyteck.platform.controller;

import br.com.hyteck.platform.PlataformaTargetApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class CartControllerTest extends PlataformaTargetApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private CartController cartController;


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void addCoupon() {
    }

    @Test
    void getService() {
    }
}