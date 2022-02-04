package dyosim.forestfire.parse.openapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ApiCaller {
	
	@Value("${openapi.serviceKey}")
	private String serviceKey;
	
	private ResponseEntity<String> getResString() {
		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Object> entity = new HttpEntity<>(headers);
		String url = "http://apis.data.go.kr/1400377/forestPoint/forestPointListGeongugSearch"
				+ "?ServiceKey={serviceKey}"
				+ "&_type=json"
				+ "&excludeForecast=0";

		return rt.exchange(url, HttpMethod.GET, entity, String.class, serviceKey);

	}

	public List<FFResponse> getResponse() {
		String body = getResString().getBody();

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(body);

			JSONObject parsedResponse = (JSONObject) jsonObject.get("response");
			JSONObject parsedBody = (JSONObject) parsedResponse.get("body");
			JSONObject items = (JSONObject) parsedBody.get("items");
			JSONArray item = (JSONArray) items.get("item");

			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");

			FFResponse[] result = (FFResponse[]) item.stream().map(itemObj -> {
						JSONObject obj = (JSONObject) itemObj;
						LocalDateTime ldt;
						try {
							ldt = LocalDateTime.parse(obj.get("analdate").toString(), formatter1);
						}
						catch(DateTimeParseException err) {
							ldt = LocalDateTime.parse(obj.get("analdate").toString(), formatter2);
						}
						
						return FFResponse.builder()
								.date(ldt)
								.meanAvg(((Long) obj.get("meanavg")).intValue())
								.regionCode(((Long) obj.get("regioncode")).intValue())
								.build();
					}
			).toArray(FFResponse[]::new);

			return new ArrayList<>(Arrays.asList(result));
		} catch (ParseException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
