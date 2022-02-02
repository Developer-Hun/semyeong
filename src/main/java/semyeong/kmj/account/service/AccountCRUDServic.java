package semyeong.kmj.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.account.dao.AccountRepository;
import semyeong.kmj.account.domain.AccountDomain;
import semyeong.kmj.account.dto.AccountRequest;
import semyeong.kmj.account.entity.Account;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountCRUDServic {

	private final AccountRepository accountRepository;

	public Long accountSave(AccountRequest request) {
		Boolean duplications = accountRepository.existsByAccountNameAndAccountType(request.getAccountName(), request.getAccountType());

		if (duplications) throw new IllegalStateException("이미 존재하는 거래처입니다.");

		Account account = accountRepository.save(AccountDomain.from(request).toCreateEntity());

		return account.getId();
	}
}
