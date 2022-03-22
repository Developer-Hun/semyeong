package semyeong.kmj.order.entity;

import lombok.*;
import semyeong.kmj.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;

}
