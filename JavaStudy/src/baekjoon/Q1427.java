// 소트인사이드 (백준 1427번)

// 선택정렬 구현
// 문자열 정렬을 위해 StringBuilder 사용
// 내림차순 -> Max를 찾고 해당 인덱스 부분이랑 교환
package baekjoon;

import java.util.Scanner;

public class Q1427 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String number = sc.next();
		Q1427 s = new Q1427();
		System.out.println(s.selectionSort(number));
		sc.close();
	}
	public StringBuilder selectionSort(String number) {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<number.length(); i++) {
			sb.append(number.charAt(i));
		}
		
		for(int j=0; j<sb.length(); j++) {
			
			char max = sb.charAt(j);
			int idx = j;
			
			for(int k=j+1; k<sb.length(); k++) {
				if(sb.charAt(k) > max) {
					max = sb.charAt(k);
					idx = k;
				}
			}
			
			if(max != sb.charAt(j)) {
				sb.setCharAt(idx, sb.charAt(j));
				sb.setCharAt(j, max);
			}
		}
		return sb;
	}
}
