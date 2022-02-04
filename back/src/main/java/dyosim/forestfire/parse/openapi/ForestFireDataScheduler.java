package dyosim.forestfire.parse.openapi;

import dyosim.forestfire.api.domain.ForestFire;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForestFireDataScheduler {
	
	private final ApiCaller apiCaller;
	private final ForestFireParser forestFireParser;

	public ForestFireDataScheduler(ApiCaller apiCaller, ForestFireParser forestFireParser) {
		this.apiCaller = apiCaller;
		this.forestFireParser = forestFireParser;
	}

	@Scheduled(cron = "10 * * * * ?")
	public void scheduleGetDataFromOpenAPI() {
		List<FFResponse> ffResponses = apiCaller.getResponse();
		System.out.println(ffResponses.get(0));
	}
}
