package semyeong.kmj.account.dto;

import lombok.*;
import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.managementItem.dto.ManagementItemRequest;
import semyeong.kmj.managementItem.entity.ManagementItem;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountRequest {

	private Long id;
	private String accountName;
	private String comments;
	private List<ManagementItemRequest> managementItemRequests = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private StatusType statusType;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	/**
	 *  List<ManagementItemRequest> 에서 List<ManagementItem>로 만드는 과정
	 *  Account Domain 객체의 필드에 값을 넣기 위한 타입 변환
	 */
	public List<ManagementItem> getManagementItemsConverter() {
		return this.managementItemRequests.stream()
				.map(managementItemRequest -> ManagementItem.builder()
						.item(managementItemRequest.getItemConverter())
//						.account(managementItemRequest.getAccountConverter())
						.basicPrice(managementItemRequest.getBasicPrice())
						.id(managementItemRequest.getId())
						.build()
				).collect(Collectors.toList());
	}
}
