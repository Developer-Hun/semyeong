package semyeong.kmj.account.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.account.dao.AccountRepository;
import semyeong.kmj.account.dto.AccountListResponse;
import semyeong.kmj.account.dto.AccountRequest;
import semyeong.kmj.account.dto.AccountResponse;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountReadServiceTest {

	@Autowired AccountRepository accountRepository;
	@Autowired AccountCRUDServic accountCRUDServic;
	@Autowired AccountReadService accountReadService;

	@Test
	public void 모든거래처목록가져오기() throws Exception {
	    // given
		AccountRequest request1 = new AccountRequest();
		AccountRequest request2 = new AccountRequest();
		AccountRequest request3 = new AccountRequest();

		request1.setAccountName("거래처1");
		request1.setComments("비고1");
		request1.setStatusType(StatusType.enable);
		request1.setAccountType(AccountType.purchase);

		request2.setAccountName("거래처2");
		request2.setComments("비고2");
		request2.setStatusType(StatusType.enable);
		request2.setAccountType(AccountType.purchase);

		request3.setAccountName("거래처3");
		request3.setComments("비고3");
		request3.setStatusType(StatusType.enable);
		request3.setAccountType(AccountType.purchase);

		accountCRUDServic.accountSave(request1);
		accountCRUDServic.accountSave(request2);
		accountCRUDServic.accountSave(request3);

		Pageable pageable = PageRequest.of(0, 10);

	    // when
		AccountListResponse accountListResponse = accountReadService.accountAll(pageable);

		// then
		assertThat(accountListResponse.getAccountListResponse().getTotalElements()).isEqualTo(3);
	}

	@Test
	public void 존재하지않는_거래처_예외() throws Exception {
	    // given when then
		assertThrows(NoSuchElementException.class, () -> accountReadService.accountOne(1L));
	}

	@Test
	public void 특정_거래처_가져오기() throws Exception {
	    // given
		AccountRequest request1 = new AccountRequest();

		request1.setAccountName("거래처1");
		request1.setComments("비고1");
		request1.setStatusType(StatusType.enable);
		request1.setAccountType(AccountType.purchase);

		Long accountId = accountCRUDServic.accountSave(request1);

		// when
		AccountResponse findAccount = accountReadService.accountOne(accountId);

		// then
		assertThat(findAccount.getId()).isEqualTo(accountId);
	}
}