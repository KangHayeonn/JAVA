// ��ȣ����� (���� 1759��)

// 1. �־��� C���� ���ڸ� �ҹ��� ���������� ����
// 2. ���������� ���ĵ� ���ڸ� C������ L�� �̴� ������ ����
// ���� : �ּ� ���� 1�� �̻�, �ּ� ���� 2�� �̻�

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
		int one_check = 0; // ��� ������ �ϳ� �ִ��� üũ
		int two_check = 0; // ��� ������ �ΰ� �̻� �ִ��� üũ
		
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
