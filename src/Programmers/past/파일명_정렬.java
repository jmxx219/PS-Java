package Programmers.past;

import java.util.*;

// Pass(25m)
class 파일명_정렬 {
    // 파일명에 포함된 숫자를 반영한 정렬 기능을 저장소 관리 프로그램에 구현
    // 영문 대소문자, 숫자, 공백(" "), 마침표("."), 빼기 부호("-")

    static class File implements Comparable<File> {
        public int index;
        public String filename;
        public String head;
        public int number;

        public File(int index, String filename){
            this.index = index;
            this.filename = filename;
            init();
        }

        private void init() {
            int N = this.filename.length();
            char[] fn = this.filename.toCharArray();

            int numIndex = 0;
            while(numIndex < N && !Character.isDigit(fn[numIndex])) {
                numIndex += 1;
            }
            int tailIndex = numIndex;
            while(tailIndex < N && Character.isDigit(fn[tailIndex])) {
                tailIndex += 1;
            }

            this.head = this.filename.substring(0, numIndex).toLowerCase();
            this.number = Integer.parseInt(this.filename.substring(numIndex, tailIndex));

        }

        @Override
        public int compareTo(File f) {
            if(this.head.equals(f.head)) {
                if(this.number == f.number) {
                    return this.index - f.index;
                }
                return this.number - f.number;
            }
            return this.head.compareTo(f.head);
        }
    }

    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();

        for(int i = 0; i < files.length; i++) {
            fileList.add(new File(i, files[i]));
        }

        Collections.sort(fileList);

        String[] answer = new String[files.length];
        for(int i = 0; i < fileList.size(); i++) {
            answer[i] = fileList.get(i).filename;
        }

        return answer;
    }
}