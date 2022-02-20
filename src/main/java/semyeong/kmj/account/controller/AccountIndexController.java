package semyeong.kmj.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import semyeong.kmj.account.dto.AccountResponse;
import semyeong.kmj.account.service.AccountReadService;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.item.dto.ItemResponse;
import semyeong.kmj.item.service.ItemReadService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountIndexController {

	private final AccountReadService accountReadService;
	private final ItemReadService itemReadService;

	@GetMapping("/account/accountMain")
	public String main(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) final Pageable pageable, Model model) {
		log.info("start accountMain...");
		Page<AccountResponse> accountListResponse = accountReadService.accountAll(pageable).getAccountListResponse();
		model.addAttribute("accountPage", accountListResponse);
		return "account/accountMain";
	}

	@GetMapping("/account/accountNew")
	public String accountNew() {
		log.info("start accountNew...");
		return "account/accountNew";
	}

	@GetMapping("/account/accountEdit/{accountId}")
	public String accountEdit(@PathVariable Long accountId, Model model) {
		log.info("start accountEdit...");
		AccountResponse accountResponse = accountReadService.accountOne(accountId);
		List<ItemResponse> itemResponses = itemReadService.itemSelectByStatus(StatusType.enable);
		model.addAttribute("account", accountResponse);
		model.addAttribute("items", itemResponses);
		return "account/accountEdit";
	}
}
