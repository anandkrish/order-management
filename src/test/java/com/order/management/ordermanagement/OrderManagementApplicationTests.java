package com.order.management.ordermanagement;

import com.order.management.ordermanagement.controller.OrderProcessingController;
import com.order.management.ordermanagement.dto.OrderDTO;
import com.order.management.ordermanagement.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

	@Test
	public void submitOrder() throws Exception {
		String uri = "/orders";
		Product productDetails = new Product();
		productDetails.setProductId("A12132");
		productDetails.setProductType("Fashion");
		productDetails.setPrice(500.50);
		OrderDTO orderDetails = new OrderDTO();
		orderDetails.setOrderId(UUID.randomUUID().toString());
		orderDetails.setPhoneNumber("9952563200");
		orderDetails.setShipmentAddress("Chennai");
		orderDetails.setCustomerName("Anand");
		orderDetails.setProductList(List.of(productDetails));
		String inputJson = TestUtils.mapToJson(orderDetails);
		this.mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andDo(print()).andExpect(status().isCreated());
	}


}
