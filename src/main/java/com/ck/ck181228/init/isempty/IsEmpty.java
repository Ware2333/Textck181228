package com.ck.ck181228.init.isempty;

import java.util.List;

public class IsEmpty {
	public static boolean str(String a) {
		if (a.length() == 0 || a == "" || a == null) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean lis(List<?> list) {
		if (list.size() == 0 || list == null) {
			return false;
		} else {
			return true;
		}
	}
}
