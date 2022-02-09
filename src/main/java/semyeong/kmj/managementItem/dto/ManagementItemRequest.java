package semyeong.kmj.managementItem.dto;

import lombok.*;
import semyeong.kmj.account.dto.AccountRequest;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.item.dto.ItemRequest;
import semyeong.kmj.item.entity.Item;
import semyeong.kmj.managementItem.entity.ManagementItem;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ManagementItemRequest {
	private Long id;
	private int basicPrice;
	private AccountRequest accountRequest;
	private ItemRequest itemRequest;
	private String comments;

	/**
	 *  AccountRequest 에서 Account로 만드는 과정
	 *  ManagementItem Domain 객체의 필드에 값을 넣기 위한 타입 변환
	 */
	public Account getAccountConverter() {
		return Account.builder()
				.id(this.accountRequest.getId())
				.accountName(this.accountRequest.getAccountName())
				.comments(this.accountRequest.getComments())
				.accountType(this.accountRequest.getAccountType())
				.statusType(this.accountRequest.getStatusType())
				.managementItems(this.accountRequest.getManagementItemsConverter())
				.build();
	}

	/**
	 *  itemRequest 에서 Item으로 만드는 과정
	 *  ManagementItem Domain 객체의 필드에 값을 넣기 위한 타입 변환
	 */
	public Item getItemConverter() {
		return Item.builder()
				.id(this.itemRequest.getId())
				.itemName(this.itemRequest.getItemName())
				.stockQuantity(this.itemRequest.getStockQuantity())
				.statusType(this.itemRequest.getStatusType())
				.comments(this.itemRequest.getComments())
				.build();

	}
}
