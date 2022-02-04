package dyosim.forestfire.parse.openapi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FFResponse {
	private LocalDateTime date;
	private int regionCode;
	private int meanAvg;
}
