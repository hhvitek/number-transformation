package cz.hhvitek.numbertransformation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import cz.hhvitek.numbertransformation.service.RestService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ApplicationControllerHttpResponseTests {
	private static final String URL = RestServiceController.REST_SERVICE_URL;

	@Autowired
	private MockMvc mockMvc;

	@MockBean // without any further mockito init, any invoked method of this bean does nothing
	private RestService restService;


	@Test
	void testOk() throws Exception {
		mockMvc.perform(
				get(URL + "/43256791")
		)
				.andExpect(status().isOk());
	}

	@Test
	void testTooLargeNumber() throws Exception {
		mockMvc.perform(
						get(URL + "/999999999999999999999")
				)
				.andExpect(status().isBadRequest());
	}

	@Test
	void testNegativeOrZeroNumber() throws Exception {
		mockMvc.perform(
						get(URL + "/-1")
				)
				.andExpect(status().isBadRequest());

		mockMvc.perform(
						get(URL + "/0")
				)
				.andExpect(status().isBadRequest());
	}

	@Test
	void testInvalidNumberFormat() throws Exception {
		mockMvc.perform(
						get(URL + "/InvalidNumberAsString")
				)
				.andExpect(status().isBadRequest());
	}

	@Test
	void testInvalidUrl() throws Exception {
		mockMvc.perform(
						get(URL + "/Unknown/Url/To/Nothing")
				)
				.andExpect(status().isNotFound());
	}

}
