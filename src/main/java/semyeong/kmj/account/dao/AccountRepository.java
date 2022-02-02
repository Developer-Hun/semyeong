package semyeong.kmj.account.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.common.common.AccountType;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Boolean existsByAccountNameAndAccountType(String accountName, AccountType accountType);

//	Page<Account> findAllByStatus(Pageable pageable, String status);
}
