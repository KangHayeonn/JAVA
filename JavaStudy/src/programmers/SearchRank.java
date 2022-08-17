// 순위 검색 (프로그래머스 LEVEL2)

package programmers;

import java.util.*;

public class SearchRank {
	static Map<String, ArrayList<Integer>> map = new HashMap<>();
	static ArrayList<Integer> arr;
	public static void main(String args[]) {
		SearchRank s = new SearchRank();
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		
		System.out.println(Arrays.toString(s.solution(info, query)));
	}
	public int[] solution(String[] info, String[] query) {
		String[] strArr;
		String str;
		
		for(int i=0; i<info.length; i++) {
			strArr = info[i].split(" ");
			combination("", 0, strArr);
		}
		
		List<String> list = new ArrayList<>(map.keySet());
		for(int i=0; i<list.size(); i++) {
			arr = map.get(list.get(i));
			Collections.sort(arr);
		}
		
		int[] answer = new int[query.length];
		for(int i=0; i<query.length; i++) {
			str = query[i].replaceAll(" and ", "");
			strArr = str.split(" ");
			answer[i] = binarySearch(strArr[0], Integer.parseInt(strArr[1]));
		}
		
		return answer;
	}
	// 조합
	public static void combination(String str, int depth, String[] strArr) {
		if(depth == 4) {
			if(!map.containsKey(str)) {
				arr = new ArrayList<Integer>();
				arr.add(Integer.parseInt(strArr[4]));
				map.put(str, arr);
			} else {
				map.get(str).add(Integer.parseInt(strArr[4]));
			}
			return;
		}
		
		combination(str+strArr[depth], depth+1, strArr);
		combination(str+"-", depth+1, strArr);
	}
	// 이분 탐색
	public static int binarySearch(String str, int num) {
		if(!map.containsKey(str)) return 0;
		
		arr = map.get(str);
		int start = 0, end = arr.size()-1, mid = 0;

		while(start <= end) {
			mid = (start + end) / 2;
			
			if(arr.get(mid) < num) {
				start = mid+1;
			} else {
				end = mid-1; 
			}
		}
		
		return arr.size() - start;
	}
}
