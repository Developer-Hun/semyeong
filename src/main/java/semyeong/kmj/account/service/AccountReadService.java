package semyeong.kmj.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.account.dao.AccountRepository;
import semyeong.kmj.account.dto.AccountListResponse;
import semyeong.kmj.account.dto.AccountResponse;
import semyeong.kmj.account.entity.Account;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountReadService {

	private final AccountRepository accountRepository;

	public AccountListResponse accountAll(Pageable pageable) {
		return AccountListResponse.from(accountRepository.findAll(pageable));
	}

//	public AccountListResponse accountAllByStatus(Pageable pageable, String status) {
//		return AccountListResponse.from(accountRepository.findAllByStatus(pageable, status));
//	}

	public AccountResponse accountOne(Long accountId) {
		Account account = accountRepository.findById(accountId).orElseThrow(
				() -> new NoSuchElementException("해당 거래처가 존재하지 않습니다.")
		);

		return AccountResponse.from(account);
	}
}
