// ������ (���� 1461��)

/* [ �˰��� ]
 * 
 * 1. 0���� ������ A �켱���� ť�� ���� , 0���� ū �� B ���ÿ� ����  (A�� ��� ������ ū�� �պκп� �ְ� B�� ��� �ڿ� �ֱ� ����)
 * 2. A�� ��� �ּҰ��� ���ָ鼭 �迭�� ����, B�� ��� ������������ ���� �� ���� �ڿ� �ִ� ���� ����
 * 3. ���� ���� ��ŭ ���ְ� ���� �� ���� ���ο� Distance �迭�� ����
 * 4. A�� B �� ���̻� ���� ������ Ż�� Ȥ�� �߰��� ����� �� �հ��� ���� �� Ż��
 * 5. Distance �迭���� ���� ū ���� �����ϰ� ��� ����*2 �ؼ� ������
 * 6. �������� ���� ū �� ���� ������ �� �׶��� ���� ���
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1461 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		//Queue<Integer> A = new LinkedList<>();
		PriorityQueue<Integer> A = new PriorityQueue<>(); // ������������ ���� (��Ʈ ��尡 ���� ���� ��)
		Stack<Integer> B = new Stack<>();
		
		while(N-->0) {
			int a = Integer.parseInt(st.nextToken());
			
			if(a<0) A.add(a);
			else B.push(a);
		}
		
		Collections.sort(B); // ������������ ����
		
		ArrayList<Integer> Distance = new ArrayList<>();
		
		int check = 0;
		while(!A.isEmpty()) {
			if(check==0) Distance.add((-1)*A.poll());
			else A.poll(); 
			check++; 
			if(check==M) check = 0;
		}
		
		check = 0;
		while(!B.isEmpty()) {
			if(check==0) Distance.add(B.pop());
			else B.pop(); 
			check++; 
			if(check==M) check = 0;
		}
		
		Collections.sort(Distance);
		
		int answer = 0;
		
		for(int i =0; i < Distance.size() ; i++) {
			if(i==Distance.size()-1) {
				answer += Distance.get(i);
				break;
			}
			
			answer += 2*Distance.get(i);
		}
		
		System.out.print(answer);
		
	}
}
