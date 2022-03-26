// 순위 검색 (프로그래머스 LEVEL2 - Kakao)

// 조합, 정렬, Map, 이분탐색
package TestPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingSearch {
	static Map<String, ArrayList<Integer>> map;
	static ArrayList<Integer> arr;
	public static void main(String args[]) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		System.out.println(solution(info, query));
	}
	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		String[][] newInfo = new String[info.length][5];
		map = new HashMap<>();
		
		for(int i=0; i<info.length; i++) {
			String[] s = info[i].split(" ");
			newInfo[i] = s;
		}
		
		for(int i=0; i<newInfo.length; i++) {
			combination("", 0, newInfo[i]);
		}
		
		// map에 저장된 점수를 list 오름차순으로 정렬
		List<String> list = new ArrayList<>(map.keySet());
		for(int i=0; i<list.size(); i++) {
			List<Integer> scoreList = map.get(list.get(i));
			Collections.sort(scoreList);
		}
		
		for(int i=0; i<query.length; i++) {
			query[i] = query[i].replaceAll(" and ", "");
			String[] str = query[i].split(" ");
			int score = Integer.parseInt(str[1]);
			
			answer[i] = search(str[0], score);
		}
		return answer;
	}
	// 조합
	public static void combination(String s, int depth, String[] info) {
		if(depth==4) {
			if(!map.containsKey(s)) {
				arr= new ArrayList<>();
				arr.add(Integer.parseInt(info[4]));
				map.put(s, arr);
			} else {
				map.get(s).add(Integer.parseInt(info[4])); // map에서 value에 새로운 값 추가
			}
			return;
		}
		
		combination(s + info[depth], depth+1, info);
		combination(s + "-", depth+1, info);
	}
	// 이분탐색
	public static int search(String str, int score) {
		if(!map.containsKey(str)) return 0;
		List<Integer> scoreList = map.get(str);
		int start = 0, end = scoreList.size()-1;
		
		while(start <= end) {
			int mid = (start+end)/2;
			if(scoreList.get(mid) < score) {
				start = mid+1;
			} else {
				end = mid-1;
			}
		}
		return scoreList.size()-start; // 해당 점수보다 큰 범위
	}
 }
