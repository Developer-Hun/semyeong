package semyeong.kmj.account.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;
import semyeong.kmj.account.entity.Account;

@Getter
public class AccountListResponse {

	private final Page<AccountResponse> accountListResponse;

	private AccountListResponse(Page<Account> list) {
		this.accountListResponse = list.map(AccountResponse::from);
	}

	public static AccountListResponse from(Page<Account> list) {
		return new AccountListResponse(list);
	}
}
