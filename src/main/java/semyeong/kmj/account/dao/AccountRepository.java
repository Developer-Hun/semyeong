package semyeong.kmj.account.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.common.common.AccountType;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountDynamicRepository {
	Boolean existsByAccountNameAndAccountType(String accountName, AccountType accountType);

	void deleteByIdIn(List<Long> accountIdList);

//	Page<Account> findAllByStatus(Pageable pageable, String status);
}
