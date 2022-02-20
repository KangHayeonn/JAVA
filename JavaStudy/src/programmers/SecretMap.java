// 비밀지도 (프로그래머스 LEVEL1 - Kakao)

package programmers;

import java.util.Arrays;

public class SecretMap {
	public static void main(String args[]) {
		int n = 5;
		int[] arr1 = {9, 20, 28, 18, 11};
		int[] arr2 = {30, 1, 21, 17, 28};
		
		System.out.println(Arrays.toString(solution(n, arr1, arr2)));
		int i = 16;
		
		String binaryString = Integer.toBinaryString(i);
		System.out.println(binaryString);
	}
	public static String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		String[] arr1toString = new String[n];
		String[] arr2toString = new String[n];
		
		// arr1 변환
		for(int i=0; i<n; i++) {
			String binaryString = Integer.toBinaryString(arr1[i]);
			StringBuffer sb = new StringBuffer(binaryString);
			while(sb.length()<n) {
				sb.insert(0, '0');
			}
			arr1toString[i] = sb.toString();
		}
		
		// arr2 변환
		for(int i=0; i<n; i++) {
			String binaryString = Integer.toBinaryString(arr2[i]);
			StringBuffer sb = new StringBuffer(binaryString);
			while(sb.length()<n) {
				sb.insert(0, '0');
			}
			arr2toString[i] = sb.toString();
		}
		
		System.out.println(Arrays.toString(arr1toString));
		System.out.println(Arrays.toString(arr2toString));
		
		for(int i=0; i<n; i++) {
			String str = "";
			for(int j=0; j<n; j++) {
				if(arr1toString[i].charAt(j) == '1' || arr2toString[i].charAt(j) == '1') {
					str += "#";
				} else {
					str += " ";
				}
			}
			answer[i] = str;
		}
		
		return answer;
	}
}
