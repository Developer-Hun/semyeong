package semyeong.kmj.account.entity;

import lombok.*;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.common.converter.AccountTypeConverter;
import semyeong.kmj.common.converter.StatusTypeConverter;
import semyeong.kmj.common.entity.BaseEntity;
import semyeong.kmj.item.entity.Item;
import semyeong.kmj.managementItem.entity.ManagementItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Account extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "account_id")
	private Long id;

	private String accountName;
	private String comments;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<ManagementItem> managementItems = new ArrayList<>();

	@Convert(converter = StatusTypeConverter.class)
	private StatusType statusType;

	@Convert(converter = AccountTypeConverter.class)
	private AccountType accountType;

	/**
	 * 연관 관계 메서드
	 */
	
	// 거래 품목 추가(단일)
	public void addManagementItem(ManagementItem managementItem) {
		managementItem.setAccount(this);
		this.managementItems.add(managementItem);
	}

	// 거래 품목 추가(다중)
	public void addManagementItems(List<ManagementItem> managementItemList) {
		System.out.println("managementItemList = " + managementItemList);
		for (ManagementItem managementItem : managementItemList) {
			System.out.println("managementItem.getComments() = " + managementItem.getComments());
			System.out.println("managementItem.getBasicPrice() = " + managementItem.getBasicPrice());
			managementItem.setAccount(this);
			this.managementItems.add(managementItem);
		}
	}

	/**
	 * 서비스 로직
	 */
	
	// 거래 품목 정보 변경
	public void updateManagementItems(List<ManagementItem> managementItems) {
		this.managementItems.forEach(originManegementItem -> {
			managementItems.forEach(updateManagementItem -> {
				if (originManegementItem.getId() == updateManagementItem.getId()) {
					originManegementItem.updateManagementItem(updateManagementItem);
				}
			});
		});
	}

	public void updateAccountInfo(AccountType accountType, StatusType statusType, String accountName, String comments) {
		this.accountType = accountType;
		this.statusType = statusType;
		this.accountName = accountName;
		this.comments = comments;
	}
}
