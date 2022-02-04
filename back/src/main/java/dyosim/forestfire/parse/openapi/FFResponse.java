package dyosim.forestfire.parse.openapi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class FFResponse {
	private LocalDateTime date;
	private int regionCode;
	private int meanAvg;
}
