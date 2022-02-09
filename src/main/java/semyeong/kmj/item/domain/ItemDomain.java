package semyeong.kmj.item.domain;

import lombok.Getter;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.dto.ItemRequest;
import semyeong.kmj.item.entity.Item;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class ItemDomain {
	private final String itemName;
	private final String unit;
	private final String comments;
	private final int stockQuantity;
	@Enumerated(EnumType.STRING)
	private final StatusType statusType;

	public ItemDomain(ItemRequest request) {
		itemName = request.getItemName();
		unit = request.getUnit();
		comments = request.getComments();
		stockQuantity = request.getStockQuantity();
		statusType = request.getStatusType();
	}

	public static ItemDomain from(ItemRequest request) {
		return new ItemDomain(request);
	}

	public Item toCreateEntity() {
		return Item.builder()
				.itemName(itemName)
				.unit(unit)
				.comments(comments)
				.stockQuantity(stockQuantity)
				.statusType(statusType)
				.build();
	}
}
