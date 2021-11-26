// �Ա� �ɻ� (���� 3079��)

/* [ �˰��� ]
 * 
 * 1. �Ա��ɻ翡�� �ɸ��� �ð��� map���� �޾��� 
 * 2. �ش� �ð�(key)�� �������� ���� (�� �κ��� �ʿ䰡 ������)
 * 3. �ð��� �������� mid�� ������ (�ּҽð� + �ִ�ð� /2 = mid)
 * 4. ���� ���� mid ������ mid���� key�� ���� ������ ���� value�� ���ذ��� �ݺ����� ������ (sum = mid�� value + (�ݺ���) mid%3 = 1.5*value + mid%2 =2 *value)
 * 5. sum�� ���� ��ü M���� Ŭ��� ���� �κ� Ž��
 * 6. sum�� ���� ��ü M���� ������� ������ �κ� Ž��
 * 7. 3-6���� �ݺ��� �� �� ���� M==sum�� �������� �ñⰡ ������ �׶��� key�� return
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Q3079 {
	static long answer = Long.MAX_VALUE;
	
	public static void main (String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // �ɻ�� ��
		int M = Integer.parseInt(st.nextToken()); // ����̿� ģ������ ��
		
		Map<Integer, Integer> map = new HashMap<>();
		long Max = 0; // �� �κж����� Ʋ�� !!!
		
		/* ���� ���̽�
		 * 1 1000000000
		 * 1000000000
		 */
		
		for(int i=0; i<N; i++) {
			int time = Integer.parseInt(br.readLine());
			if(Max < time) Max = time;
			int value = map.containsKey(time)? map.get(time) : 0;
			map.put(time, value+1);
		}

		long left = 0;
		long right = M*Max;
		BinarySearch(map, left, right, M);
	}
	
	public static void BinarySearch(Map<Integer, Integer>map, long left, long right, int M) {
		while(left<=right) {
			int sum = 0;
			long mid = (left+right)/2;
			
			for(Integer key : map.keySet()) {
				sum += mid/key*map.get(key);
				if(sum >= M) break; // �� �κ� ��� ��!!
			}

			if(sum < M) {
				left = mid+1;
			} else {
				right = mid-1;
				answer = Math.min(answer, mid);
				//answer = mid;
			}
		}
		System.out.print(answer);
		return;
	}
}
