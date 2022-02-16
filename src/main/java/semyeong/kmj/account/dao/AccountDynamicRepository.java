package semyeong.kmj.account.dao;

import semyeong.kmj.account.entity.Account;

import java.util.List;

public interface AccountDynamicRepository {

	Account findAllByFetchJoin(Long accountId);
}
