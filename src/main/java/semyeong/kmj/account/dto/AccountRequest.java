package semyeong.kmj.account.dto;

import lombok.*;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.managementItem.entity.ManagementItem;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountRequest {

	private Long id;
	private String accountName;
	private String comments;
	private List<ManagementItem> managementItems = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private StatusType statusType;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
}
