// 덩치 (백준 7568번)

// 1. for문을 두개 이용해 자기 자신을 제외한 모든 사람을 비교한다.
// 2. 자기 자신보다 키와 몸무게가 모두가 더 높은 사람을 카운트 한다.
// 3. 해단 카운트+1 한 값을 그 사람의 인덱스 위치에 매칭 시켜 배열에 넣어준다.
// 4. 해당 배열을 공백문자와 함께 출력

package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q7568 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int arr[][] = new int[count][2];
		
		for(int i=0; i<count; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.valueOf(st.nextToken());
			arr[i][1] = Integer.valueOf(st.nextToken());
		}
		
		int answer[] = new int[count];
		
		for(int i=0; i<count; i++) {
			int idx = 0;
			for(int j=0; j<count; j++) {
				if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) idx++;
			}
			answer[i] = idx+1;
		}
		
		for(int i=0; i < count; i++) {
			System.out.print(answer[i] + " ");
		}
	}
}
