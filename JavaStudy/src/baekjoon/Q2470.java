// 두 용액 (백준 2470번)

// 투포인터 반대방향 알고리즘 이용

// 먼저 정렬을 한다.
// 좌측 끝과 우측 끝의 합을 구한다.
// 만약 합이 양수일 경우 우측 끝의 인덱스를 -1 해준다.
// 합이 음수일 경우 좌측 끝의 인덱스를 +1 해준다.
// 이때의 합의 절댓값이 이전 값의 절댓값보다 작은 경우 갱신해준다.

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2470 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[count];
		
		for(int i=0; i<count; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}
		
		Arrays.sort(arr);
		
		int left = 0;
		int right = count-1;
		int min = Math.abs(arr[left] + arr[right]);
		int min_left = arr[left];
		int min_right = arr[right];
		
		while(left < right) {
			System.out.println(left + " " + right);
			System.out.println("값 : " + min_left + " " + min_right);
			int v1 = arr[left] + arr[right];   // 두 용액의 합
	
			if(v1 == 0) {
				min = 0;
				min_left = arr[left];
				min_right = arr[right];
				break;
			}
			else if(v1 > 0) {
				right--;
			}
			else {
				left++;
			}
			
			int v2 = Math.abs(arr[left] + arr[right]); // 두 용액의 합의 절댓값
			
			if(v2 < min) {
				if(left!=right) { // (** 주의) 이 부분 조건 중요
					min = v2;
					min_left = arr[left];
					min_right = arr[right];
				}
			}
		}
		
		System.out.print(min_left + " " + min_right);
	}
}
