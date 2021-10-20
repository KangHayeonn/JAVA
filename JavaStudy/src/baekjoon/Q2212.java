// 센서 (백준 2212번)

// 1. 주어지는 센서 N 을 정렬
// 2. 각 센서 두개씩 거리를 계산하고 최댓값은 삭제
// 3. 최댓값 삭제 갯수는 집중국 K일때 K-1
// 4. 그리고 모든 수를 더해서 출력

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2212 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		StringTokenizer N = new StringTokenizer(br.readLine(), " ");
		int arr[] = new int[count];
		
		for(int i=0; i<count; i++) {
			arr[i] = Integer.parseInt(N.nextToken());
		}
		
		Arrays.sort(arr);
		
		int distance[] = new int[count-1];
		for(int i=0; i<count-1; i++) {
			distance[i] = arr[i+1] - arr[i];
		}
		
		Arrays.sort(distance);
		
		int sum = 0;
		
		for(int i=0; i <= distance.length-K ; i++) {
			sum += distance[i];
		}
		
		//System.out.println(Arrays.toString(distance));
		System.out.print(sum);
	}
}
