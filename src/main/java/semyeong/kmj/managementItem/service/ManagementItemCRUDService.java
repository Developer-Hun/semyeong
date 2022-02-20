package semyeong.kmj.managementItem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semyeong.kmj.managementItem.dao.ManagementItemRepository;
import semyeong.kmj.managementItem.entity.ManagementItem;

import java.util.ArrayList;
import java.util.List;

import static semyeong.kmj.managementItem.entity.QManagementItem.managementItem;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ManagementItemCRUDService {

	private final ManagementItemRepository managementItemRepository;

	public void managementItemDelete(List<Long> managementItemIdList) {
		List<ManagementItem> findManagementItems = managementItemRepository.findByIdIn(managementItemIdList);

		for (ManagementItem deleteManagementItem : findManagementItems) {
			deleteManagementItem.deleteManagementItem(deleteManagementItem);
		}

		managementItemRepository.deleteByIdIn(managementItemIdList);
	}
}
