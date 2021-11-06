package UteCo;

public class test2 {
	public static void main(String args[]) {
		String[] log = {"02:03", "02:08", "11:00", "14:00", "22:00", "22:45", "22:46", "23:59"};
		System.out.print(solution(log));
	}
	
	public static String solution(String[] log) {
		String answer = "";
		
		int startHour, startMinute, endHour, endMinute;
		int startTotal = 0;
		int endTotal = 0;
		int sum = 0;
		
		for(int i=0; i<log.length; i++) {
			if(i%2 == 0) {
				String[] parts = log[i].split(":");
				startHour = 60*Integer.parseInt(parts[0]);
				startMinute = Integer.parseInt(parts[1]);
				startTotal = startHour + startMinute;
			}
			else {
				String[] parts2 = log[i].split(":");
				endHour = 60*Integer.parseInt(parts2[0]);
				endMinute = Integer.parseInt(parts2[1]);
				endTotal = endHour + endMinute;
				
				int studyTime = endTotal-startTotal;
				
				if(studyTime > 105) sum += 105;
				else if(studyTime < 5) sum += 0;
				else sum += studyTime;
			}
		}
		
		int hour = (sum/60);
		int minute = sum - (60*hour);
		
		if(hour<10) {
			answer += "0" + hour;
		} else answer += hour;
		
		if(minute<10) {
			answer += ":0" + minute; 
		} else answer += ":" + minute;
		
        return answer;
	}
}
