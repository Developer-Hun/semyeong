package semyeong.kmj.item.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;
import semyeong.kmj.item.entity.Item;

@Getter
public class ItemListResponse {

	private Page<ItemResponse> itemListResponse;

	private ItemListResponse(Page<Item> list) {
		this.itemListResponse = list.map(ItemResponse::from);
	}

	public static ItemListResponse from(Page<Item> list) {
		return new ItemListResponse(list);
	}

}
