package semyeong.kmj.managementItem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import semyeong.kmj.managementItem.service.ManagementItemCRUDService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ManagementItemApiController {

	private final ManagementItemCRUDService managementItemCRUDService;

	@DeleteMapping("managementItem/delete")
	public ResponseEntity<String> managementItemDelete(@RequestBody final List<Long> managementItemIdList) {
		log.info("start account delete...");
		managementItemCRUDService.managementItemDelete(managementItemIdList);
		return ResponseEntity.ok().build();
	}
}
