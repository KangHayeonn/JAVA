// 전화번호 목록 (백준 5052번)

/* [ 알고리즘 ]
 * 
 * 1. '접두어'라는 거에 초점을 맞춘다.
 * 2. 모든 번호를 정렬해준다.
 * 3. 인덱스 앞 인덱스가 뒤 인덱스에 포함되는지 비교해준다.
 * 4. 만약 앞 인덱스가 뒤 인덱스 길이보다 길 경우는 접두어일 수 없으니깐 미리 제외
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
				//if(arr[i+1].contains(arr[i])) check=false; // 이렇게 구할 경우 접두어가 아닌 경우도 모두 NO 출력됨
				if(arr[i+1].indexOf(arr[i])==0) check=false;
			}
		}
		if(!check) sb.append("NO").append("\n");
		else sb.append("YES").append("\n");
	}
}
