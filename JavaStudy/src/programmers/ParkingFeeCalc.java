package programmers;

import java.util.*;

public class ParkingFeeCalc {
	public static void main(String args[]) {
		ParkingFeeCalc p = new ParkingFeeCalc();
		int[] fees = {180, 5000, 10, 600}; // �⺻ �ð�, �⺻ ���, ���� �ð�, ���� ���
		String[] records = {
				"05:34 5961 IN", 
				"06:00 0000 IN", 
				"06:34 0000 OUT", 
				"07:59 5961 OUT", 
				"07:59 0148 IN", 
				"18:59 0000 IN", 
				"19:09 0148 OUT", 
				"22:59 5961 IN", 
				"23:00 5961 OUT"	
		};
		System.out.println(Arrays.toString(p.solution(fees, records)));
	}
	public int[] solution(int[] fees, String[] records) {
        Map<String, String> entranceHours = new HashMap<>(); // ������ȣ : �����ð�
		Map<String, Integer> useHours = new HashMap<>();     // ������ȣ : �̿�ð�(��)
		
		int basicHour = fees[0]; // �⺻ �ð�(��)
		int basicFee = fees[1];  // �⺻ ���(��)
		int unitTime = fees[2];  // ���� �ð�(��)
		int unitFee = fees[3];   // ���� ���(��)
		
        String[] record, inTimeList, outTimeList;
        String time, carNum, isIn, inTime;
		
		int recordsLen = records.length, inTimeToMinutes, outTimeToMinutes, useTime;
        
		for(int i=0; i<recordsLen; i++) {
			record = records[i].split(" ");
			time = record[0];   // ����/���� �ð�
			carNum = record[1]; // ������ȣ 
			isIn = record[2];   // IN/OUT
			
			if(entranceHours.containsKey(carNum)) {
				// ������ ������ ���
				if("OUT".equals(isIn)) {
					inTime = entranceHours.get(carNum); // �����ð�
					// �����ð� - �����ð� ��� (������ ������ ����� ����)
					inTimeList = inTime.split(":");
					inTimeToMinutes = Integer.parseInt(inTimeList[0])*60 + Integer.parseInt(inTimeList[1]);
					outTimeList = time.split(":");
					outTimeToMinutes = Integer.parseInt(outTimeList[0])*60 + Integer.parseInt(outTimeList[1]);
					
					useTime = outTimeToMinutes - inTimeToMinutes; //  ���ð�
					if(useHours.containsKey(carNum)) {
						useHours.put(carNum, useHours.get(carNum) + useTime);
					} else {
						useHours.put(carNum, useTime);
					}
					entranceHours.remove(carNum);
				}
			} else {
				// ������ �������� ���� ���
				if("IN".equals(isIn)) {
					entranceHours.put(carNum, time);
				}
			}
		}
		
		for(String key : entranceHours.keySet()) {
			inTimeList = entranceHours.get(key).split(":");
			inTimeToMinutes = Integer.parseInt(inTimeList[0])*60 + Integer.parseInt(inTimeList[1]);
			outTimeToMinutes = (23*60 + 59); // 23:59
			
			useTime = outTimeToMinutes - inTimeToMinutes; //  ���ð�
			if(useHours.containsKey(key)) {
				useHours.put(key, useHours.get(key) + useTime);
			} else {
				useHours.put(key, useTime);
			}
			entranceHours.remove(key);
		}
		
		SortedSet<String> carSet = new TreeSet<>();
		Map<String, Integer> totalFee = new HashMap<>();
        
		for(String key : useHours.keySet()) {
			useTime = useHours.get(key);
			
			if(useTime <= basicHour) {
				totalFee.put(key, basicFee);
			} else {
				int fee = basicFee + (int)Math.ceil((float)(useTime-basicHour)/unitTime) * unitFee;
				totalFee.put(key, fee);
			}
			
			carSet.add(key);
		}
		
		int idx = 0;
		int[] answer = new int[carSet.size()];
		for(String s : carSet) {
			answer[idx++] = totalFee.get(s);
		}
		
		
        return answer;
    }
}
