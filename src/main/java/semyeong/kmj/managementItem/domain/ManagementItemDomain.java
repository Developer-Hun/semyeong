package semyeong.kmj.managementItem.domain;

import lombok.Getter;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.item.entity.Item;
import semyeong.kmj.managementItem.dto.ManagementItemRequest;
import semyeong.kmj.managementItem.entity.ManagementItem;

@Getter
public class ManagementItemDomain {
	private final int basicPrice;
	private final Account account;
	private final Item item;
	private final String comments;

	public ManagementItemDomain(ManagementItemRequest request) {
		basicPrice = request.getBasicPrice();
		account = request.getAccountConverter();
		item = request.getItemConverter();
		comments = request.getComments();
	}

	public static ManagementItemDomain from(ManagementItemRequest request) {
		return new ManagementItemDomain(request);
	}

	public ManagementItem toCreateEntity() {
		return ManagementItem.builder()
				.basicPrice(basicPrice)
				.account(account)
				.item(item)
				.comments(comments)
				.build();
	}
}
