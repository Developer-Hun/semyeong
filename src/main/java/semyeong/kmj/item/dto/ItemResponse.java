package semyeong.kmj.item.dto;

import lombok.Getter;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.entity.Item;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
public class ItemResponse {

	private final Long id;
	private final String itemName;
	private final String unit;
	private final String comments;
	private final int stockQuantity;
	@Enumerated(EnumType.STRING)
	private final StatusType statusType;
	private final LocalDateTime createdDate;
	private final LocalDateTime updatedDate;


	private ItemResponse(Item item) {
		id = item.getId();
		itemName = item.getItemName();
		unit = item.getUnit();
		comments = item.getComments();
		stockQuantity = item.getStockQuantity();
		statusType = item.getStatusType();
		createdDate = item.getCreatedDate();
		updatedDate = item.getUpdatedDate();
	}

	public static ItemResponse from(Item item) {
		return new ItemResponse(item);
	}
}
