package semyeong.kmj.order.dto;

import lombok.Getter;
import semyeong.kmj.order.entity.Order;

@Getter
public class OrderResponse {

	private OrderResponse(Order order) {
	}

	public static OrderResponse from(Order order) {
		return new OrderResponse(order);
	}
}