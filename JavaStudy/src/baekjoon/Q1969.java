// DNA (���� 1969��)

// 1. ���ڿ��� �� �ε��� ���� A, C, G, T �߿� ���� ���� ���� ���ĺ��� ã�´�.
// 2. Max ���� ���� ���ĺ��� �����ϰ� �������� value ���� ���ؼ� �عְŸ��� �����Ѵ�.
// 3. ���� ������ ���� ������ ���ĺ����� ���� ���ĺ��� ����Ѵ�.
// 4. �׷��� ����� �عְŸ��� ���ĺ� ���ڿ��� �������� ������ش�.

package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class Q1969 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // DNA�� ��
		int M = Integer.parseInt(st.nextToken()); // ���ڿ��� ����
		
		String str_arr[] = new String[M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			str_arr[i] = str;
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String dna = "";
		int h_distance = 0;
		
		for(int i=0; i<M; i++) {
			int a=0, c=0, g=0, t=0;
			map.put("A", 0);
			map.put("C", 0);
			map.put("G", 0);
			map.put("T", 0);
			
			for(int j=0; j<N; j++) {
				switch(str_arr[j].charAt(i)) {
					case 'A': a++; break;
					case 'C': c++; break;
					case 'G': g++; break;
					default : t++; break;
				}
			}
			map.put("A", a); map.put("C", c); map.put("A", g); map.put("A", t);
			
			Entry<String, Integer> maxEntry =null;
			
			Set<Entry<String, Integer>> entrySet = map.entrySet();
			for(Entry<String, Integer> entry : entrySet) {
				if(maxEntry == null || entry.getValue()> maxEntry.getValue()) {
					maxEntry = entry;
				}
			}
			
			System.out.println(maxEntry.getKey() + " : " + maxEntry.getValue());
		}
		
	}
}
