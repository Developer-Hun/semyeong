package semyeong.kmj.item.entity;

import lombok.*;
import semyeong.kmj.common.common.StatusType;
import semyeong.kmj.common.converter.StatusTypeConverter;
import semyeong.kmj.common.entity.BaseEntity;
import semyeong.kmj.exception.NotEnoughStockException;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	private String itemName;
	private String unit;
	private String comment;
	private int stockQuantity;
	@Convert(converter = StatusTypeConverter.class)
	private StatusType statusType;


	//==비즈니스 로직==/

	/**
	 * stock 증가
	 */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	/**
	 * stock 감소
	 */
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if (restStock < 0) {
			throw new NotEnoughStockException("재고 수량이 부족합니다.");
		}
		this.stockQuantity = restStock;
	}

	/**
	 * 품목 정보 업데이트
	 */
	public void updateItem(String itemName, String unit, StatusType statusType, String comment) {
		this.itemName = itemName;
		this.unit = unit;
		this.statusType = statusType;
		this.comment = comment;
	}
}
