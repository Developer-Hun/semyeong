package semyeong.kmj.order.domain;

import lombok.Getter;
import semyeong.kmj.order.dto.OrderRequest;
import semyeong.kmj.order.entity.Order;

@Getter
public class OrderDomain {

	private OrderDomain(OrderRequest request) {
	}

	public static OrderDomain from(OrderRequest request) {
		return new OrderDomain(request);
	}

	public Order toCreateEntity() {

		return null;
	}
}