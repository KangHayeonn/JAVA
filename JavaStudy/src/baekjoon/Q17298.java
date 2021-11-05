// ��ū�� (���� 17298��)

/* [ �˰��� ]
 * �ϴ� �Էµ� ���� ���ÿ� ��� ����
 * 3 5 2 7
 * ���� �����ʿ� �ִ� ������ �� �� �������� Ž��
 * ���� ������ �ִ°� ������ -1�� �ϰ� �� ����� �ʱⰪ���� �����
 * 
 * 2�� ��� 7���� �����ϱ� ���
 * �߰� ���ÿ� �ٽ� ���� [7, 2]
 * ���� ���� [-1, 7]
 * 
 * 5�� ��� �߰����ÿ��� �ϳ��� pop �ؼ� �� 2���� ũ�ϱ� 2�� ����, �״��� �� 7�� ��
 * -> 7�� �� ũ�ϱ� ���ΰ� 5�� �߰� ���ÿ� add
 * �߰����� [7, 5]
 * ���� ����[-1, 7, 7]
 * 
 * 3�� ��� �߰����ÿ��� �ϳ��� pop�ؼ� �� 3���� 5�� ũ�ϱ� 5�� �״�� ���ΰ� 3�� add
 * �߰� ���� [7, 5, 3]
 * ���� ���� [-1, 7, 7, 5]
 * 
 * �ε����� �������ϱ� ������
 * �ش� ���� �Ųٷ� ���
 */

// ver1 : ���� -> �ð��ʰ�
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q17298 {
	public static class type {
		private int position;
		private int value;
		
		public type (int position, int value) {
			this.position = position;
			this.value = value;
		}
		
		public String toString() {
			//return position + " : " + value;
			return value + "";
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<type> stack = new Stack<>(); 
		int[] arr= new int[count];
		
		for(int i=0; i<count; i++) {
			int num = Integer.parseInt(st.nextToken()); 
			stack.add(new type(i, num));
			arr[i] = num;
		}
		
		int[] answer= new int[count];
		//Arrays.fill(answer, -1);
	
		while(!stack.isEmpty()) {
			int val = stack.peek().value;
			int pos = stack.peek().position;
			
			for(int i=0; i< pos; i++) {
				if(val > arr[i]) answer[i] = pos;
			}
			
			stack.pop();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<count; i++) {
			if(answer[i]==0) sb.append(-1).append(" ");
			else sb.append(arr[answer[i]]).append(" ");
		}
		
		System.out.print(sb);
	}
}
*/

