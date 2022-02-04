package dyosim.forestfire.api.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ForestFire {

	@Id
	private Long id;
	private LocalDateTime date;
	private int code;
	private int predict;
	private int meanVal;
	
}