// 암호만들기 (백준 1759번)

// 1. 주어진 C개의 문자를 소문자 사전순으로 정렬
// 2. 사전순으로 정렬된 문자를 C개에서 L개 뽑는 조합을 구함
// 주의 : 최소 모음 1개 이상, 최소 자음 2개 이상

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1759 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		String arr[] = new String[C];
		boolean check[] = new boolean[C];
		
		StringTokenizer alpha = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			arr[i] = alpha.nextToken();
		}
		//Arrays.sort(arr);
		Arrays.sort(arr, (a,b) -> a.compareTo(b)); // a c i s t w
		combi(arr, check, 0, C, L);
	}
	
	public static void combi(String arr[], boolean check[], int start, int C, int L) {
		String vowel = "aeiou";
		int one_check = 0; // 적어도 모음이 하나 있는지 체크
		int two_check = 0; // 적어도 자음이 두개 이상 있는지 체크
		
		if(L==0) {
			String answer = stringMake(arr, check);
			
			for(int i=0; i<answer.length(); i++) {
				
				if(vowel.contains(Character.toString(answer.charAt(i)))) one_check++;
				else two_check++;
				
				if(one_check>=1 && two_check>=2) {
					System.out.println(answer);
					break;
				}
			}
			
			return;
		}
		
		for(int i=start; i<=C-L; i++) {
			check[i] = true;
			combi(arr, check, i+1, C, L-1);
			check[i] = false;
		}
		
		return;
	}
	
	public static String stringMake(String arr[], boolean check[]) {
		String answer = "";
		for(int i=0; i<check.length; i++) {
			if(check[i]==true) answer += arr[i];
		}
		return answer;
	}
}
