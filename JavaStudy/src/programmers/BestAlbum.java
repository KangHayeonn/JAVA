// ����Ʈ�ٹ� (���α׷��ӽ� LEVEL3)
package programmers;

import java.util.*;

public class BestAlbum {
	public static class Song {
		String genre;
		int idx, play, count; // idx: ������ȣ, play: ���Ƚ��, count: ���� Ƚ��
		
		public Song(String genre, int idx, int play, int count) {
			this.genre = genre;
			this.idx = idx;
			this.play = play;
			this.count = count;
		}
	}
	public static void main(String args[]) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		
		System.out.println(Arrays.toString(solution(genres, plays)));
 	}
	public static int[] solution(String[] genres, int[] plays) {
		Map<String, Integer> map = new HashMap<>();
		PriorityQueue<Song> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.count != o2.count) return o2.count - o1.count;
			else {
				if(o1.play != o2.play) return o2.play - o1.play;
				else return o1.idx - o2.idx;
			}
		});
		
		String str="";
		int count=0;
		
		for(int i=0; i<genres.length; i++) {
			str = genres[i];
			
			map.put(str, map.getOrDefault(str, 0) + plays[i]);
		}
		
		for(int i=0; i<genres.length; i++) {
			str = genres[i];
			count = map.get(str);
			
			pq.offer(new Song(str, i, plays[i], count));
		}
		
		count = 0;
		str = "";
		ArrayList<Integer> arr = new ArrayList<>();
		
		while(!pq.isEmpty()) {
			Song s = pq.poll();
			
			if(str.equals(s.genre)) {
				count += 1;
				if(count > 2) continue;
			}
			else {
				str = s.genre;
				count = 1;
			}
			
			arr.add(s.idx);
		}
		
		int[] answer = arr.stream()          // Stream<Integer>�� ��ȯ��
				.mapToInt(Integer::intValue) // Integer�� intValue() �޼��带 �����ؼ� ��Ÿ���� int�� ��ڽ���
				.toArray();                  // IntStream�� ���Ҹ� �迭�� ��ȯ��
		return answer;
	}
}
