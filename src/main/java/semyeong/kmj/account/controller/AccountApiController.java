package semyeong.kmj.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semyeong.kmj.account.dto.AccountRequest;
import semyeong.kmj.account.service.AccountCRUDServic;
import semyeong.kmj.account.service.AccountReadService;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.managementItem.dto.ManagementItemRequest;
import semyeong.kmj.managementItem.entity.ManagementItem;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountApiController {

	private final AccountReadService accountReadService;
	private final AccountCRUDServic accountCRUDServic;

	@PostMapping("/account/save")
	public ResponseEntity<Long> accountSave(@RequestBody final AccountRequest request) {
		log.info("start account save...");
		Long accountId = accountCRUDServic.accountSave(request);
		return ResponseEntity.ok(accountId);
	}

	@PutMapping("/account/edit")
	public ResponseEntity<Long> accountEdit(@RequestBody final AccountRequest request) {
		log.info("start account edit...");
		Long accountId = accountCRUDServic.accountEdit(request);
		return ResponseEntity.ok(accountId);
	}

	@DeleteMapping("/account/delete")
	public ResponseEntity<String> accountDelete(@RequestBody final List<Long> accountIdList) {
		log.info("start account delete...");
		accountCRUDServic.accountDelete(accountIdList);
		return ResponseEntity.ok().build();
	}
}
