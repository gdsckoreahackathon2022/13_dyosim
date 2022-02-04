package dyosim.forestfire.parse.openapi;

import dyosim.forestfire.api.domain.ForestFire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForestFireParser {
	
	public void parseForestFireFromFFResponse(FFResponse ffResponse) {
		System.out.println(ffResponse.toString());
		
	}
	
	public void parseForestFireFromFFResponse(List<FFResponse> ffResponses) {
		
	}
}
