// ÆÐ¼Ç¿Õ ½ÅÇØºó (¹éÁØ 9375¹ø)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q9375 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<count; i++) {
			int n = Integer.parseInt(br.readLine());
			int answer = 1;
			HashMap<String, Integer> map = new HashMap<>();
			for(int j=0; j<n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				int value = map.containsKey(b) ? map.get(b) : 0;
				map.put(b, value + 1);
			}
			for(String key: map.keySet()) {
				answer *= map.get(key)+1;
			}
			sb.append(answer-1).append("\n");
		}
		System.out.print(sb);
	}
}
