package Programmers.past;

public class 택배_배달과_수거하기 {
    public int findEndIndex(int[] houses) {
        for(int i = houses.length - 1; i >= 0; i--) {
            if(houses[i] > 0) return i;

        }
        return -1; // 모두 배달 또는 수거완료
    }

    public void deliveryPickUp(int cap, int startIndex, int[] houses) {
        for(int i = startIndex; i >= 0; i--) {
            if(houses[i] == 0) continue;

            if(cap >= houses[i]) {
                cap -= houses[i];
                houses[i] = 0;
            }
            else if(cap < houses[i]) {
                houses[i] -= cap;
                cap = 0;
            }

            if(cap == 0) break;
        }
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0L;
        int dIndex = deliveries.length - 1;
        int pIndex = pickups.length - 1;


        while(dIndex >= 0 || pIndex >= 0) {
            while(dIndex >= 0 && deliveries[dIndex] == 0) dIndex -= 1;
            while(pIndex >= 0 && pickups[pIndex] == 0) pIndex -= 1;

            int d = Math.max(dIndex, pIndex);

            if(dIndex != -1) deliveryPickUp(cap, Math.min(dIndex, d), deliveries);
            if(pIndex != -1) deliveryPickUp(cap, Math.min(pIndex, d), pickups);

            answer += (d + 1) * 2;
        }

        return answer;
    }
}
