package semyeong.kmj.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
	public ResponseEntity<String> accountSave(@RequestBody final AccountRequest request) {
		log.info("start account save...");
		Long id = request.getId();
		String accountName = request.getAccountName();
		AccountType accountType = request.getAccountType();
		String comments = request.getComments();
		List<ManagementItemRequest> managementItemRequests = request.getManagementItemRequests();
		StatusType statusType = request.getStatusType();

		System.out.println("id = " + id);
		System.out.println("accountName = " + accountName);
		System.out.println("accountType = " + accountType);
		System.out.println("comments = " + comments);
		System.out.println("statusType = " + statusType);
		System.out.println("managementItemRequests = " + managementItemRequests);
		for (ManagementItemRequest managementItemRequest : managementItemRequests) {
			System.out.println("managementItemRequest getItemRequest = " + managementItemRequest.getItemRequest());
		}

		accountCRUDServic.accountSave(request);

		return ResponseEntity.ok().build();
	}
}
