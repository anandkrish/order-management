package com.order.management.ordermanagement;

import com.order.management.ordermanagement.controller.OrderProcessingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OrderManagementApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Autowired
	private OrderProcessingController orderProcessingController;


	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@DisplayName("Test to check if controller is created")
	@Test
	void contextLoads() {
		assertThat(orderProcessingController).isNotNull();
	}


	@Test
	public void getOrders() throws Exception {
		String uri = "/orders";
		this.mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());
	}
}
