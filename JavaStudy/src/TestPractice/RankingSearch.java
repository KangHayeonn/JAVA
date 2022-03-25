// 순위 검색 (프로그래머스 LEVEL2 - Kakao)

// 문자열 정렬 및 문자열 함수
package TestPractice;

import java.util.Arrays;
import java.util.Comparator;

public class RankingSearch {
	public static void main(String args[]) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		System.out.println(solution(info, query));
	}
	public static int[] solution(String[] info, String[] query) {
		int[] answer = {};
		String[][] newInfo = new String[info.length][5];
		
		for(int i=0; i<info.length; i++) {
			System.out.println(info[i]);
			String[] s = info[i].split(" ");
			newInfo[i] = s;
		}
		
		System.out.println(Arrays.deepToString(newInfo));
		
		Arrays.sort(newInfo, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				int a = Integer.parseInt(o1[4]);
				int b = Integer.parseInt(o2[4]);
				return a-b;
			}
		});
		
		System.out.println(Arrays.deepToString(newInfo));
		
		System.out.println("----------------");
		
		for(int i=0; i<query.length; i++) {
			System.out.println(query[i]);
			String[] s = query[i].split(" and ");
			System.out.println(Arrays.toString(s));
			// newInfo[i] = s;
		}
		
		
		return answer;
	}
}
