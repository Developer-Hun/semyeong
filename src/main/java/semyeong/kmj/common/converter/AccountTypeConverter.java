package semyeong.kmj.common.converter;

import semyeong.kmj.common.common.AccountType;
import semyeong.kmj.common.common.StatusType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {
	@Override
	public String convertToDatabaseColumn(AccountType attribute) {
		return attribute.getName();
	}

	@Override
	public AccountType convertToEntityAttribute(String dbData) {
		return Arrays.stream(AccountType.values())
				.filter(type -> type.getName().equals(dbData))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
