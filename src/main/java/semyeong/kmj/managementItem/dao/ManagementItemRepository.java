package semyeong.kmj.managementItem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import semyeong.kmj.managementItem.entity.ManagementItem;

import java.util.List;

public interface ManagementItemRepository extends JpaRepository<ManagementItem, Long> {

	void deleteByIdIn(List<Long> managementItemIdList);

	List<ManagementItem> findByIdIn(List<Long> managementItemIdList);
}
