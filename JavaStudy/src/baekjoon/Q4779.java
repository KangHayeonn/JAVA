// 칸토어 집합 (백준 4779번)

/* [ 알고리즘 ]
 * 
 * 1. 처음 입력받은 N에 대해 3^N개의 -로 이루어진 문자열을 만듦
 * 2. 3등분 한 뒤 가운데 문자열을 공백으로 바꾼다.
 * 3. 나머지 문자열 2개에서 2의 과정을 반복한다.
 * 4. 모든 선의 길이가 1일 때까지 2-3의 과정을 반복
 * 5. 만약 선의 길이가 1이 될 경우 해당 문자열을 출력
 */

// BufferedWriter로 메모리초과 해결
package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q4779 {
	static int N;
	static char[] Line;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		//StringBuilder sb = new StringBuilder("");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while((str=br.readLine())!=null) {
			N = Integer.parseInt(str);
			int num = (int)Math.pow(3, N);
			
			Line = new char[num];
			for(int i=0; i<num; i++) {
				Line[i] = '-';
			}
			
			partition(0, num);
			
			for(int i=0; i<Line.length; i++) {
				bw.write(Line[i]);
			}
			bw.write("\n");
			
			//sb.append(result).append("\n");
		}
		bw.flush();
		//System.out.print(sb);
	}
	public static void partition(int start, int num) {
		
		if(num < 3) return;
		
		/* A B C
		 * 
		 * A : start ~ start+num/3
		 * B : start+num/3 ~ start+num/3*2
		 * C : start+num/3*2 ~ start+num
		 */
		
		partition(start, num/3);
		for(int i=start+num/3; i<start+num/3*2; i++) {
			Line[i] = ' '; // '-' -> ' '
		}
		partition(start+num/3*2, num/3);
	}
}
                            