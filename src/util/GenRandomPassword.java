package util;

import java.util.Random;

public class GenRandomPassword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getPassword(10));
	}

	public static String getPassword(int pwd_len) {
		// 35是因為陣列是從0開始的,26個字母+10個數字
		final int maxNum = 62;
		int i; // 生成的隨機數
		int count = 0; // 生成的密碼的長度
		char[] str = { 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
				'n', 'o', 'p', 'q', 'r', 's','t', 'u', 'v', 'w', 'x', 'y', 'z', 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S','T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成隨機數,取絕對值,防止生成負數,
			i = Math.abs(r.nextInt(maxNum)); // 生成的數最大為36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}
}
