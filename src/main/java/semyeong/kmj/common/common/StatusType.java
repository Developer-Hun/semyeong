package semyeong.kmj.common.common;

import lombok.Getter;

@Getter
public enum StatusType {
	enable("사용"), disable("사용안함");

	private final String name;

	private StatusType(String name) {
		this.name = name;
	}
}
