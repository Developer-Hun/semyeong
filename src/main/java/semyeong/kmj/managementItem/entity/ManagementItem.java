package semyeong.kmj.managementItem.entity;


import lombok.*;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.entity.BaseEntity;
import semyeong.kmj.item.entity.Item;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ManagementItem extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "management_item_id")
	private Long id;

	private int basicPrice;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	private String comments;

	//==연관관계 메서드==//
	public void setAccount(Account account) {
		this.account = account;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void updateManagementItem(ManagementItem updateManagementItem) {
		this.basicPrice = updateManagementItem.getBasicPrice();
		this.comments = updateManagementItem.getComments();
		setItem(updateManagementItem.getItem());
	}

	public void deleteManagementItem(ManagementItem managementItem) {
		account.getManagementItems().remove(managementItem);
		managementItem.setAccount(null);
	}
}
