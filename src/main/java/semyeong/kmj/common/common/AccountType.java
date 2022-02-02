package semyeong.kmj.common.common;

import lombok.Getter;

@Getter
public enum AccountType {
	purchase("구매"), sale("판매");

	private final String name;

	private AccountType(String name) {
		this.name = name;
	}
}
