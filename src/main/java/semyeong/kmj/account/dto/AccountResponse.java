package semyeong.kmj.account.dto;

import lombok.Getter;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.managementItem.entity.ManagementItem;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AccountResponse {
	private final Long id;
	private final String accountName;
	private final String comments;
	private final List<ManagementItem> managementItems;
	private final StatusType statusType;
	private final AccountType accountType;
	private final LocalDateTime createdDate;
	private final LocalDateTime updatedDate;

	private AccountResponse(Account account) {
		id = account.getId();
		accountName = account.getAccountName();
		comments = account.getComments();
		managementItems = account.getManagementItems();
		statusType = account.getStatusType();
		accountType = account.getAccountType();
		createdDate = account.getCreatedDate();
		updatedDate = account.getUpdatedDate();
	}

	public static AccountResponse from(Account account) {
		return new AccountResponse(account);
	}
}