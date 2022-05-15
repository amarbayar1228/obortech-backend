package kara.diamond.billing.service.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NumericHelper {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
	private static Random random = new Random();
	
	public static Long generateKey() {
		return Long.valueOf(new StringBuilder(sdf.format(new Date())).append(random.nextInt(900) + 100).toString());
	}
	
	public static Long generateKey(long prev) {
		long curr = 0;
		do {
			curr = Long.parseLong(new StringBuilder(sdf.format(new Date())).append(random.nextInt(900) + 100).toString());
		} while (curr == prev);
		return Long.valueOf(curr); 
	}
	
	public static boolean equals(Long n1, Long n2) {
		if (n1 != null && n2 != null) return n1.equals(n2);
		return false;
	}
}