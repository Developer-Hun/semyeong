package semyeong.kmj.account.domain;

import lombok.Getter;
import semyeong.kmj.account.dto.AccountRequest;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.managementItem.entity.ManagementItem;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AccountDomain {
	private final String accountName;
	private final String comments;
	private final List<ManagementItem> managementItems;
	private final StatusType statusType;
	private final AccountType accountType;

	private AccountDomain(AccountRequest request) {
		accountName = request.getAccountName();
		comments = request.getComments();
		managementItems = request.getManagementItems();
		statusType = request.getStatusType();
		accountType = request.getAccountType();
	}

	public static AccountDomain from(AccountRequest request) {
		return new AccountDomain(request);
	}

	public Account toCreateEntity() {
		return Account.builder()
				.accountName(accountName)
				.comments(comments)
				.managementItems(managementItems)
				.statusType(statusType)
				.accountType(accountType)
				.build();
	}
}