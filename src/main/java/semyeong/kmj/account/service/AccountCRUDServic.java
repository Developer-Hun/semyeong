package semyeong.kmj.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.account.dao.AccountRepository;
import semyeong.kmj.account.domain.AccountDomain;
import semyeong.kmj.account.dto.AccountRequest;
import semyeong.kmj.account.dto.AccountResponse;
import semyeong.kmj.account.entity.Account;
import semyeong.kmj.managementItem.entity.ManagementItem;

import java.util.List;
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

	public Long accountEdit(AccountRequest request) {
		Account account = accountRepository.findById(request.getId()).orElseThrow(
				() -> new NoSuchElementException("해당 거래처가 존재하지 않습니다.")
		);

		Account findAccount = accountRepository.findAllByFetchJoin(request.getId());

		// 거래처 정보 변경
		findAccount.updateAccountInfo(request.getAccountType(), request.getStatusType(), request.getAccountName(), request.getComments());

		// 새로운 거래 품목 추가
		for (ManagementItem managementItem : request.getManagementItemsConverter()) {
			if (managementItem.getId() == null) findAccount.addManagementItem(managementItem);
		}

		// 기존 거래 품목 변경
		findAccount.updateManagementItems(request.getManagementItemsConverter());

		return findAccount.getId();
	}

	public void accountDelete(List<Long> accountIdList) {
		accountRepository.deleteByIdIn(accountIdList);
	}
}
