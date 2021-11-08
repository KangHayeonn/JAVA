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

// ver2 : ���� -> �˰��� ����
package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q17298 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[count];
		Stack<Integer> stack = new Stack<>(); // �߰� ����
		int[] answer = new int[count];
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i=0; i<count; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int j=0;
		StringBuilder sb = new StringBuilder();
		for(int i=count-1; i>=0; i--) {
			if(i==count-1) {
				//sb.append(" ").append(-1);
				answer[j]= -1;
				j++;
				stack.add(arr[i]);
				continue;
			}
			while(!stack.isEmpty()) {
				if(arr[i] < stack.peek()) {
					//sb.append(" ").append(stack.peek());
					answer[j]= stack.peek();
					j++;
					stack.add(arr[i]);
					break;
				}
				else {
					stack.pop();
					//answer[j]= -1;
				}
			}
			if(stack.isEmpty()) {
				answer[j]= -1;
				j++;//sb.append(" ").append(-1);
				stack.add(arr[i]); // ** �̺κ��� ������ Ʋ��
			}
		}
		for(int i=answer.length-1; i>=0; i--) {
			bw.write(answer[i]+" ");
		}
		
		bw.flush();
	    bw.close();

	}
}

