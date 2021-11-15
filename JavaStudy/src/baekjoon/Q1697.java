// ���ٲ��� (���� 1697��)

/* [ �˰��� ]
 * 
 * 1. X<K , X==K, X>K �� ���� ������ Ǯ����
 * 2. X<K �� ��� X+1, X-1, X*2 �� ���� ���� �ش� ��ġ�� �Դ� ��츦 check�ϸ� for���� �� (BFS, ť �̿�)
 * 3. X==K �� ���, 0 ���
 * 4. X>K �� ��� X-K�� ���
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1697 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken()); // �����̰� �ִ� ��ġ
		int K = Integer.parseInt(st.nextToken()); // ������ �ִ� ��ġ
		
		if(X<K) FIND(X, K); // BFS�̿� , ť�̿�
		else if(X>K) {
			System.out.print(X-K);
		}
		else {
			System.out.print(0);
		}
	}
	
	public static void FIND(int X, int K) {
		Queue<Integer> q = new LinkedList<>();
		int[] check = new int[100001];
		
		q.add(X);

		while(!q.isEmpty()) {
			int now = q.poll(); // ������ġ
			for(int i=0; i<3; i++) {
				int next = 0;
				switch(i) {
					case 0: 
						next = now+1;
						break;
					case 1: 
						next = now-1;
						break;
					case 2: 
						next = now*2;
						break;
				}
				if(next >= 0 && next < check.length && check[next] ==0) {
					q.add(next);
					check[next] = check[now] +1;
				}
                
                if(next == K) {
					System.out.println(check[next]);
					return;
				}
			}
		}
	}
}