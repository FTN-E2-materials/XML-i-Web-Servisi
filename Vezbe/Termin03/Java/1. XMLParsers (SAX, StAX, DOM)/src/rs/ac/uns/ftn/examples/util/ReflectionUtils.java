package rs.ac.uns.ftn.examples.util;

import java.lang.reflect.Field;

public class ReflectionUtils {

	public static String getConstantName(int value, Class<?> c) throws IllegalArgumentException, IllegalAccessException {
		for (Field field : c.getDeclaredFields()) {
			if (field.getType() == int.class && field.getInt(null) == value) {
				return field.getName();
			}
		}
		return "";
	}

}
