package dyosim.forestfire.parse.openapi;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.config.location=" +
		"classpath:/application.properties" +
		",optional:classpath:/credential.properties")
class ApiCallerTest {

	@Autowired
	private ApiCaller apiCaller;

	@Test
	@DisplayName("정상적인 데이터가 오는지 체크")
	void getResponse() {
	}
}