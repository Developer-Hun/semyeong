package semyeong.kmj.account.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.account.dao.AccountRepository;
import semyeong.kmj.account.dto.AccountRequest;
import semyeong.kmj.account.dto.AccountResponse;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class AccountCRUDServicTest {

	@Autowired AccountRepository accountRepository;
	@Autowired AccountCRUDServic accountCRUDServic;
	@Autowired AccountReadService accountReadService;

	@Test
	public void 거래처_중복_예외() throws Exception {
		// given
		AccountRequest request1 = new AccountRequest();
		AccountRequest request2 = new AccountRequest();

		request1.setAccountName("거래처1");
		request1.setComments("비고1");
		request1.setStatusType(StatusType.enable);
		request1.setAccountType(AccountType.purchase);

		request2.setAccountName("거래처1");
		request2.setComments("비고1");
		request2.setStatusType(StatusType.enable);
		request2.setAccountType(AccountType.purchase);

		// when
		accountCRUDServic.accountSave(request1);

		// then
		assertThrows(IllegalStateException.class, () -> accountCRUDServic.accountSave(request2));
	}

	@Test
	public void 거래처_등록() throws Exception {
	    // given
		AccountRequest request = new AccountRequest();

		request.setAccountName("거래처1");
		request.setComments("비고1");
		request.setStatusType(StatusType.enable);
		request.setAccountType(AccountType.purchase);
	    
	    // when
		Long accountId = accountCRUDServic.accountSave(request);
		AccountResponse findAccount = accountReadService.accountOne(accountId);

		// then
		assertThat(accountId).isEqualTo(findAccount.getId());
	}
}