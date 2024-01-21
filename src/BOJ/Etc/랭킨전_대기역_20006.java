package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 랭킨전_대기역_20006 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int P;
    private static int M;
    private static List<List<Player>> rooms;

    static class Player implements Comparable<Player>{
        int level;
        String name;
        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rooms = new ArrayList<>();
        while(P-- > 0) {
            st = new StringTokenizer(br.readLine());
            Player currPlayer = new Player(Integer.parseInt(st.nextToken()), st.nextToken());
            boolean entered = false;
            for(int i = 0; i < rooms.size(); i++) {
                if(rooms.get(i).size() >= M) continue;
                Player st = rooms.get(i).get(0);
                if(st.level - 10 <= currPlayer.level && currPlayer.level <= st.level + 10) {
                    rooms.get(i).add(currPlayer);
                    entered = true;
                    break;
                }
            }
            if(!entered) {
                rooms.add(new ArrayList<>());
                rooms.get(rooms.size() - 1).add(currPlayer);
            }
        }

        for(int i = 0; i < rooms.size(); i++) {
            List<Player> room = rooms.get(i);
            if(room.size() == M) System.out.println("Started!");
            else System.out.println("Waiting!");
            Collections.sort(room);
            for(Player p : room) {
                System.out.println(p.level + " " + p.name);
            }
        }

    }

}
