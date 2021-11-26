// 입국 심사 (백준 3079번)

/* [ 알고리즘 ]
 * 
 * 1. 입국심사에서 걸리는 시간을 map으로 받아줌 
 * 2. 해당 시간(key)을 기준으로 정렬 (이 부분이 필요가 없어짐)
 * 3. 시간을 기준으로 mid를 구해줌 (최소시간 + 최대시간 /2 = mid)
 * 4. 먼저 가장 mid 값에서 mid보다 key가 작은 값을의 몫을 value에 곱해가며 반복문을 돌려줌 (sum = mid의 value + (반복문) mid%3 = 1.5*value + mid%2 =2 *value)
 * 5. sum이 만약 전체 M보다 클경우 왼쪽 부분 탐색
 * 6. sum이 만약 전체 M보다 작을경우 오른쪽 부분 탐색
 * 7. 3-6번을 반복해 준 뒤 만약 M==sum이 같아지는 시기가 있으면 그때의 key를 return
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
		int N = Integer.parseInt(st.nextToken()); // 심사대 수
		int M = Integer.parseInt(st.nextToken()); // 상근이와 친구들의 수
		
		Map<Integer, Integer> map = new HashMap<>();
		long Max = 0; // 이 부분때문에 틀림 !!!
		
		/* 예시 케이스
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
				if(sum >= M) break; // 이 부분 없어도 됨!!
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
