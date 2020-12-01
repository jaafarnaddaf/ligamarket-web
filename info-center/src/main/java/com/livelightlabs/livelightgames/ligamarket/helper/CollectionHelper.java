package com.livelightlabs.livelightgames.ligamarket.helper;

import java.util.HashMap;
import java.util.Map;

public class CollectionHelper {
	public static <S, T> Map<S, T> mapOf(S key, T value) {
		Map<S, T> map = new HashMap<>();
		map.put(key, value);
		return map;
	}
}