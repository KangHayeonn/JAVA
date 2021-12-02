// ��ȭ��ȣ ��� (���� 5052��)

/* [ �˰��� ]
 * 
 * 1. '���ξ�'��� �ſ� ������ �����.
 * 2. ��� ��ȣ�� �������ش�.
 * 3. �ε��� �� �ε����� �� �ε����� ���ԵǴ��� �����ش�.
 * 4. ���� �� �ε����� �� �ε��� ���̺��� �� ���� ���ξ��� �� �����ϱ� �̸� ����
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q5052 {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCount = Integer.parseInt(br.readLine());		
		
		for(int i=0; i<testCount; i++) {
			int count = Integer.parseInt(br.readLine());
			String[] arr = new String[count];
			for(int j=0; j<count; j++) {
				arr[j] = br.readLine();
			}
			Arrays.sort(arr);
			comparison(arr);
		}
		
		System.out.print(sb);
	}
	
	public static void comparison(String[] arr) {
		boolean check = true;
		for(int i=0; i<arr.length-1; i++) {
			if(arr[i].length() < arr[i+1].length()) {
				//if(arr[i+1].contains(arr[i])) check=false; // �̷��� ���� ��� ���ξ �ƴ� ��쵵 ��� NO ��µ�
				if(arr[i+1].indexOf(arr[i])==0) check=false;
			}
		}
		if(!check) sb.append("NO").append("\n");
		else sb.append("YES").append("\n");
	}
}
