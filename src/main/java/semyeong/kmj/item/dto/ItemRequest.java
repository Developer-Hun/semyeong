package semyeong.kmj.item.dto;

import lombok.*;
import semyeong.kmj.common.common.StatusType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemRequest {

	private Long id;
	private String itemName;
	private String unit;
	private String comment;
	private int stockQuantity;
	@Enumerated(EnumType.STRING)
	private StatusType statusType;
}
