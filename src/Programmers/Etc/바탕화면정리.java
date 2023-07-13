package Programmers.Etc;

// 18:10 ~ 18:19
public class 바탕화면정리 {
    // .: 빈칸
    // #: 파일 존재
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];

        int lux = -1, luy = -1;
        int rdx = -1, rdy = -1;

        for(int i = 0; i < wallpaper.length; i++) {
            for(int j = 0; j < wallpaper[0].length(); j++) {
                if(wallpaper[i].charAt(j) == '#') {
                    if(lux == -1 || lux > i) lux = i;
                    if(luy == -1 || luy > j) luy = j;
                    if(rdx == -1 || rdx < i) rdx = i;
                    if(rdy == -1 || rdy < j) rdy = j;
                }
            }
        }

        return new int[] {lux, luy, rdx + 1, rdy + 1};
    }
}