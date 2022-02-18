// 신고 결과 받기 (프로그래머스 LEVEL1 - Kakao)

/* [ 알고리즘 ]
 * 
 * 1. report를 입력받을 때 중복을 제거하고 배열에 저장
 * 2. id_list는 key, value로 저장하기 위해 map을 사용
 * 3. 1번을 반복문을 돌면서 해당 report를 카운트 해서 map에 저장
 * 4. value중에 k값 이상인 것을 세서 새로운 배열에 저장
 * 
 */
package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReportResult {
	public static void main(String args[]) {
		// String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		// String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
		
		String[] id_list = {"con", "ryan"};
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
		
		int k = 2;
		
		System.out.println(solution(id_list, report, k));
	}
	
	public static int[] solution(String[] id_list, String[] report, int k) {
		
		// 배열 -> Set (중복 제거)
		Set<String> set = new HashSet<String>(Arrays.asList(report));
		System.out.println(set);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Integer> mail = new HashMap<String, Integer>();
		
		for(int i=0; i<id_list.length; i++) {
			String str = id_list[i];
			map.put(str, 0);
			mail.put(str, 0);
		}
		
		for(String r: set) {
			String[] rList = r.split(" ");
			String report_id = rList[1];
			
			System.out.println("value : " + map.get(report_id));
			map.put(report_id, map.get(report_id)+1);
		}
		
		for(String key : map.keySet()) {
			if(map.get(key) >= k ) {
				for(String s : set) {
					String[] sList = s.split(" ");
					String user_id = sList[0];
					String report_id = sList[1];
					
					if(key.equals(report_id)) {
						mail.put(user_id,mail.get(user_id)+1);
					}
 				}
			}
			System.out.println("key : " + key + " value : " + map.get(key));
		}
		
		int[] answer = new int[mail.size()];
		int i = 0;
		for(String s : mail.keySet()) {
			answer[i] = mail.get(s);
			i++;
		}
		
		System.out.println(Arrays.toString(answer));
		
		return answer;
	}
}
