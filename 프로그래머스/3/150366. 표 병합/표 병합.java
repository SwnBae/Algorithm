import java.util.*;

class Group {
    Set<Integer> coordinates;
    String word;
    
    Group(String word, int r, int c) {
        coordinates = new HashSet<>();
        this.word = word;
        
        addCordinate(r, c);
    }
    
    Group(int r, int c) {
        coordinates = new HashSet<>();
        this.word = null;
        
        addCordinate(r, c);
    }
    
    public void init() {
        word = null;
        coordinates = new HashSet<>();
    }
    
    public void merge(int id, Group slave, int[][] space) {
        for(int num : slave.coordinates) {
            coordinates.add(num);
            
            space[num / 100][num % 100] = id;
        }
    }
    
    public void addCordinate(int r, int c) {
        coordinates.add(r * 100 + c);
    }
    
    public void removeCordinate(int r, int c) {
        coordinates.remove(r * 100 + c);
    }
    
}

class Solution {
    int groupId;
    int[][] space;
    
    Map<Integer, Group> groupMap;
    Map<String, Set<Integer>> cords;
    
    List<String> answer;
    
    public String[] solution(String[] commands) {
        groupId = 1;
        space = new int[51][51];
        groupMap = new HashMap<>();
        cords = new HashMap<>();
        answer = new ArrayList<>();
        
        for(String cmd : commands) {
            String[] command = cmd.split(" ");
            
            if(command[0].equals("UPDATE")) {
                if(command.length == 4) {
                    update(Integer.parseInt(command[1]), Integer.parseInt(command[2]), command[3]);
                } else {
                    update(command[1], command[2]);
                }
            } else if(command[0].equals("MERGE")) {
                merge(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]));
            } else if(command[0].equals("UNMERGE")) {
                unMerge(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
            } else {
                print(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
            }
        }
        
        return answer.toArray(new String[0]);
    }
    
    public void update(int r, int c, String value) {
        int id = space[r][c];
        
        if(!groupMap.containsKey(id)) {
            id = groupId++;
            space[r][c] = id;
            groupMap.put(id, new Group(value, r, c));
            joinMap(id, value);
        } else {
            if(cords.containsKey(groupMap.get(id).word)) {
                cords.get(groupMap.get(id).word).remove(id);
                
                if(cords.get(groupMap.get(id).word).isEmpty()) {
                    cords.remove(groupMap.get(id).word);
                }
            }
            
            groupMap.get(id).word = value;
            joinMap(id, value);
        }
    }
    
    public void joinMap(int id, String value) {
        if(cords.get(value) == null) {
            cords.put(value, new HashSet<>());
        }
        
        cords.get(value).add(id);
    }
    
    public void update(String value1, String value2) {
        Set<Integer> ids = cords.get(value1);
        if(ids == null) return;
        
        for(int tmpId : cords.get(value1)) {
            groupMap.get(tmpId).word = value2;
        }
        
        Set<Integer> tmpSet = cords.remove(value1);
        
        cords.computeIfAbsent(value2, k -> new HashSet<>())
         .addAll(tmpSet);
    }
    
public void merge(int r1, int c1, int r2, int c2) {
    int id1 = space[r1][c1];
    int id2 = space[r2][c2];
    
    if(r1 == r2 && c1 == c2) return;
    
    // 빈 셀 처리
    if(!groupMap.containsKey(id1)) {
        id1 = groupId++;
        space[r1][c1] = id1;  // 추가!
        groupMap.put(id1, new Group(r1, c1));
    }
    
    if(!groupMap.containsKey(id2)) {
        id2 = groupId++;
        space[r2][c2] = id2;  // 추가!
        groupMap.put(id2, new Group(r2, c2));
    }
    
    // 이미 병합된 경우
    if(id1 == id2) return;
    
    Group master = groupMap.get(id1);
    Group slave = groupMap.get(id2);
    
    master.merge(id1, slave, space);
    
    // slave의 word 처리
    if (slave.word != null) {
        Set<Integer> slaveSet = cords.get(slave.word);
        if (slaveSet != null) {
            slaveSet.remove(id2);
            if (slaveSet.isEmpty()) cords.remove(slave.word);
        }
    }

    // master의 word 결정
    if (master.word == null && slave.word != null) {
        master.word = slave.word;
        cords.computeIfAbsent(slave.word, k -> new HashSet<>()).add(id1);
    }
    
    groupMap.remove(id2);
}
    
    public void unMerge(int r, int c) {
    int id = space[r][c];
    if(id == 0 || !groupMap.containsKey(id)) return;
    
    Group group = groupMap.get(id);
    String savedWord = group.word;
    
    // 1. 모든 병합된 좌표를 초기화
    for(int coord : group.coordinates) {
        space[coord / 100][coord % 100] = 0;
    }
    
    // 2. (r,c) 위치만 값을 유지하며 새 그룹 생성
    int newId = groupId++;
    space[r][c] = newId;
    groupMap.put(newId, new Group(savedWord, r, c));
    
    // 3. cords 맵 정리
    if(savedWord != null) {
        if(cords.containsKey(savedWord)) {
            cords.get(savedWord).remove(id);
            if(cords.get(savedWord).isEmpty()) {
                cords.remove(savedWord);
            }
        }
        cords.computeIfAbsent(savedWord, k -> new HashSet<>()).add(newId);
    }
    
    groupMap.remove(id);
}
    
    public void print(int r, int c) {
        if((!groupMap.containsKey(space[r][c])) || space[r][c] == 0) {
            answer.add("EMPTY");
            return;
        }
        
        String word = groupMap.get(space[r][c]).word;
        answer.add(word == null ? "EMPTY" : word);
    }

}