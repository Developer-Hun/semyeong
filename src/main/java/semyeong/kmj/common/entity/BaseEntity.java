package semyeong.kmj.common.entity;


import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 진짜 상속 관계는 아니고 속성들을 공유할 수 있도록 해주는 어노테이션
public class BaseEntity {

	@Column(updatable = false)
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	// @PrePersist => persist하기 전에 이벤트가 발생한다.
	@PrePersist
	public void prePersist() {
		LocalDateTime now = LocalDateTime.now();
		createdDate = now;
		updatedDate = now;
	}

	// @PreUpdate => update하기 전에 이벤트가 발생한다.
	@PreUpdate
	public void preUpdate() {
		updatedDate = LocalDateTime.now();
	}
}
