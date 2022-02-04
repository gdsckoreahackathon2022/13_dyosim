package dyosim.forestfire.api.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForestFire {
	
	private Long id;
	private LocalDateTime date;
	private int code;
	private int predict;
	private int meanVal;
	
}