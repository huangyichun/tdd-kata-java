package org.coach.tdd.template;

public class LifeGame {

    private static final int LIVE = 1;
    private static final int DIED = 0;
//    private int[][] map = new int[8][8];
    private int[][] neighbors = new int[8][8];

    public int[][] nextStatus(int[][] currentMap) {
        for (int i = 0; i < currentMap.length; ++i) {
            for (int j = 0; j < currentMap[0].length; ++j) {
                neighbors[i][j] = countNeighborsLiveSize(currentMap, i, j);
            }
        }
        for (int i = 0; i < currentMap.length; ++i) {
            for (int j = 0; j < currentMap[0].length; ++j) {
                currentMap[i][j] = isLifeOrDie(currentMap[i][j], neighbors[i][j]);
            }
        }
        return currentMap;
    }

    public int isLifeOrDie(int currentStatus, int lifeNum) {
        if (lifeNum == 3)
            return LIVE;
        else if(lifeNum == 2 && currentStatus == LIVE)
            return LIVE;
        return DIED;
    }

    public int countNeighborsLiveSize(int[][] map, int x, int y){
        int count = 0;
        if(x-1 >= 0 && y-1 >= 0 && map[x-1][y-1] == LIVE) ++count;
        if(x+1 < map.length && y+1 < map[0].length && map[x+1][y+1] == LIVE) ++count;
        if(x+1 < map.length && y-1 >= 0 && map[x+1][y-1] == LIVE) ++count;
        if(x-1 >=0 && y+1 < map[0].length && map[x-1][y+1] == LIVE) ++count;
        if(y+1 < map[0].length && map[x][y+1] == LIVE) ++count;
        if(y-1 >= 0 && map[x][y-1] == LIVE) ++count;
        if(x-1 >=0 && map[x-1][y] == LIVE) ++count;
        if(x+1 < map.length && map[x+1][y] == LIVE) ++count;
        return count;
    }

}
