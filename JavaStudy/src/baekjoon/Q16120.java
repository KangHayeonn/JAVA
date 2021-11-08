// PPAP (���� 16120��)

/* [ �˰��� ]
 * 
 * 1. �ε��� 0���� �ش� ���ڿ��� length()-3 ���� �ݺ���
 * 2. 4���� ��� PPAP���� Ȯ�� -> substring
 * 3. 2������ ���� �κ��� PPAP�̸� P�� ��ȯ (replace)
 * 4. �ƴϸ� ���� �ε����� �Ѿ
 * 5. ���� �迭�� �ѱ��̰� 4�� �Ǹ� �ݺ��� Ż��
 * 6. �ش� ���ڿ��� PPAP�� ������ ��� PPAP ��� / �ƴ� ��� NP ��� (equals)
 * 
 */

// ver1 : ���� -> �ð��ʰ�
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16120 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder str = new StringBuilder();
		str.append(br.readLine());
		//System.out.println(str);
		for(int i=0; i<str.length()-3; ) {
			String check = str.substring(i, i+4);
			if(check.equals("PPAP")) {
				if(str.length()==4) break;
				str.replace(i, i+4, "P");
				i= 0;
			} else i++;
			//System.out.println(str);
			//System.out.println(check);
			//System.out.println("i : " + i);
		}
		
		if(str.length()==4) {
			if(("PPAP").equals(str.substring(0, 4))) System.out.print("PPAP");
			else System.out.print("NP");
		} else System.out.print("NP");
	}
}*/


// --------------------------------------
// �˰��� ���� 

/* [ �˰��� ] : ( P == PPAP )
 * 
 * 1. �ε��� 0���� �ش� ���ڿ��� ������ �ݺ���
 * 2. P�� ���� ���ÿ� ����
 * 3. A�� ��� ���ÿ��� �ΰ��� pop�ؼ� P���� Ȯ�� (���ÿ� �ּ� 2�� �̻� �־����)
 * 4. 3������ �ƴϸ� NP �ٷ� ���
 * 5. 3���� ������ A ���� ���� P������ Ȯ�� (length ������ ���� �ʴ� ������ -> length�� ������ NP)
 * 6. 5���� �ƴϸ� NP �ٷ� ���
 * 7. 5���� ������ ���� �ε����� �Ѿ (maybe 4������ ���� P�� ���ÿ� ����)
 * 8. �ݺ����� ������ check�� ���� NP ���
 * 9. ������ ���̰� 1�̸� �ش� ���� P���� Ȯ�� �� ������ PPAP ��� / �ƴ� ��� NP ��� 
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q16120 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str= br.readLine();
		Stack<Character> stack = new Stack<>();
		boolean check = false; // true�� ��� NP ���
		int i;
		
		for(i=0; i<str.length(); i++) {

			if(str.charAt(i)=='P') {
				stack.add('P');
			}
			else {
				if(i == str.length()-1) { // ** �� �κ� �߿�
					check =true;
					break;
				}
				if(stack.size()>=2) { // ** == �߿�
					int count = 0;
					while(count < 2) { // P�� �ι� ����
						stack.pop(); 
						count++;
					}
					//System.out.println(str.charAt(i+1));
					if(str.charAt(i+1)=='P') {
						continue;
					}
					else {
						check =true;
					}
				} else {
					check =true;
				}
			}
			if(check==true) break;
		}
		
		if(check == true) System.out.print("NP");
		else {
			if(stack.size()==1 && stack.peek()=='P') {   // ** P �Ѱ��� ������ �� PPAP (PP �� NP�� ħ)
	            System.out.print("PPAP");
	        } else {
	            System.out.print("NP");
	        }
		}
	}
}


