package hu.asd.merkur.core.util;

import org.springframework.lang.Nullable;

public class StringUtil {

	public static boolean isEmpty(@Nullable Object str) {
		return (str == null || "".equals(str));
	}

}
