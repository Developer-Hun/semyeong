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

}
