// �Ӹ���� ����� (���� 1254��)

/* [ �˰��� ]
 * 
 * 1. �ڿ��� ���̱� ���� substring�� �̿��� ���ڿ��� ������ üũ
 * 2. �Ӹ�������� üũ�ϱ� ���� �̺�Ž�� ���
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1254 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int answer = str.length();
		
		for(int i=0; i<str.length(); i++) {
			if(Palindrome(str.substring(i))) {
				break;
			}
			answer++;
		}
		System.out.print(answer);
	}
	
	public static boolean Palindrome(String str) {
		int left = 0;
		int right = str.length()-1;
		while(left < right) {
			char leftC = str.charAt(left);
			char rightC = str.charAt(right);
			
			if(leftC!=rightC) return false;
			
			if(leftC==rightC) {
				left++;
				right--;
			}
		}
		return true;
	}
}
