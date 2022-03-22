package semyeong.kmj.order.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;
import semyeong.kmj.order.entity.Order;

@Getter
public class OrderListResponse {

	private final Page<OrderResponse> orderListResponse;

	private OrderListResponse(Page<Order> list) {
		this.orderListResponse = list.map(OrderResponse::from);
	}

	public static OrderListResponse from(Page<Order> list) {
		return new OrderListResponse(list);
	}
}
