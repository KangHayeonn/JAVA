// 위장 ( 프로그래머스 LEVEL2 )

// 알고리즘
// 각 clothes 원소의 경우 : clothes당 하나씩 입는 경우와 안입는경우
// 즉, clothes의 원소의개수 + 1 (안입는경우)
// 그래서 clothes의 종류 마다 위의 공식을 넣어 모두 곱해주면
// 전체 다 안입는 경우가 하나 생긴다.
// 이때, 스파이는 하루에 최소 한 개 이상은 입어야 하므로 전체에서 -1을 해준다.
// clothes의 종류 key, 각각의 원소의 갯수 value (HashMap)

package programmers;

import java.util.HashMap;

public class camouflage {
	public static void main(String args[]) {
		camouflage s = new camouflage();
		String[][] clothes = new String[][]{
				{"yellowhat", "headgear"}, 
				{"bluesunglasses", "eyewear"}, 
				{"green_turban", "headgear"}
		};
		System.out.println(s.solution(clothes)); // 이차원배열을 인수로 넘겨주는 방법 아직 미지수
	}
	
	public int solution(String[][] clothes) {
		int answer = 1;
        HashMap <String, Integer> hashmap = new HashMap<String, Integer>();
        for(int i=0; i<clothes.length; i++){
            if(hashmap.containsKey(clothes[i][1])){
                int num = hashmap.get(clothes[i][1]);
                hashmap.replace(clothes[i][1], num= num+1);
            }
            else {
                hashmap.put(clothes[i][1], 1);
            }
        }
        /*
        hashmap.forEach((key, value) -> {
            System.out.println(key + " : " + value);
            //answer += value; // lambda-error
            // (error) local variables referenced from a lambda expression must be final or effectively final. 
        });*/

        for(String key : hashmap.keySet()){
            answer *= hashmap.get(key)+1;
        }

        return answer-1;
	}
}
