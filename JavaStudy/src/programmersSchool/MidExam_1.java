package programmersSchool;

import java.util.*;


public class MidExam_1 {
	public static void main(String[] args) {
		// String[][] booked = {{"09:55", "hae"}, {"10:05", "jee"}, {"10:17", "hayeo"}};
		// String[][] unbooked = {{"10:04", "hee"}, {"10:19", "a"}, {"10:21", "b"}, {"14:00", "c"}, {"14:07", "eom"}};
		String[][] booked = {{"09:50", "a"}, {"10:20", "b"}};
		String[][] unbooked = {{"10:05", "c"}, {"10:15", "d"}, {"10:25", "e"}};
		System.out.println(Arrays.toString(solution(booked, unbooked)));
	}
    public static class Type implements Comparable<Type>{
        private String name;
        private int minutes;
        private boolean check; // true : ����, false : ���� X

        public Type(String name, int minutes, boolean check) {
            this.name = name;
            this.minutes = minutes;
            this.check = check;
        }

        @Override
        public int compareTo(Type t) {
            if(this.minutes == t.minutes) {
                if(this.check) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return this.minutes - t.minutes;
            }
        }

    }
    public static String[] solution(String[][] booked, String[][] unbooked) {
        ArrayList<String> result = new ArrayList<>();
        PriorityQueue<Type> pq = new PriorityQueue<>(); // ������ + �������� ���� ���
        Queue<Type> q = new LinkedList<>(); // �߰� �����

        // ������ ��� ����
        for(int i=0; i<booked.length; i++) {
            String time = booked[i][0];
            String name = booked[i][1];

            String[] str = time.split(":");
            int hour = Integer.parseInt(str[0]);
            int min = Integer.parseInt(str[1]);

            pq.add(new Type(name, (hour*60 + min), true));
        }

        // �������� ���� ��� ����
        for(int i=0; i<unbooked.length; i++) {
            String time = unbooked[i][0];
            String name = unbooked[i][1];

            String[] str = time.split(":");
            int hour = Integer.parseInt(str[0]);
            int min = Integer.parseInt(str[1]);

            pq.add(new Type(name, (hour*60 + min), false));
        }

        Type t = pq.poll(); // ���� �տ� �ִ°� ����
        result.add(t.name);
        int finished = t.minutes + 10;

        while(!pq.isEmpty()) {
            Type t1 = pq.poll();
            if(t1.minutes <= finished) {
                if(t1.check) {
                    result.add(t1.name);
                    finished = finished+10;
                } else{
                    q.add(new Type(t1.name, t1.minutes, false));
                }
            } else{
                while(!q.isEmpty()) {
                	Type t2 = q.poll();         	
                	result.add(t2.name);
                	finished = finished + 10;
                }
                
                result.add(t1.name);
                finished = t1.minutes+10;
            }
        }
        
        while(!q.isEmpty()) {
        	Type t2 = q.poll();         	
        	result.add(t2.name);
        }
        
        String[] answer = new String[result.size()];
        int idx=0;
        
        for(String s : result) {
            answer[idx++] = s;
        }

        return answer;
    }

}
