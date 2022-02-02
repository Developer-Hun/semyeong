package semyeong.kmj.common.converter;

import semyeong.kmj.common.common.StatusType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class StatusTypeConverter implements AttributeConverter<StatusType, String> {
	@Override
	public String convertToDatabaseColumn(StatusType attribute) {
		return attribute.getName();
	}

	@Override
	public StatusType convertToEntityAttribute(String dbData) {
		return Arrays.stream(StatusType.values())
				.filter(type -> type.getName().equals(dbData))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
