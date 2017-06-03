package org.coach.tdd.template;

/**
 * life game development
 */
public class LifeGame {

    private final static int LIVE = 1;
    private final static int DIED = 0;
    private int[][] map = new int[8][8];
    private int[][] neighbores = new int[8][8];

    /**
     * next status change
     * @param map grid
     * @return  grid
     */
    public int[][] changeStatus(int[][] map) {
        for(int i=0; i<map.length; ++i){
            for(int j=0; j<map[0].length; ++j){
                neighbores[i][j] = countNeighborsLiveSize(map, i, j);
            }
        }
        for(int i=0; i<map.length; ++i){
            for(int j=0; j< map[0].length; ++j){

            }
        }
        return null;
    }

    /**
     * according lifeNum check the point is life or die
     * @param currentStatus current status
     * @param lifeNum   life numbers
     * @return status
     */
    public int isLifeOrDie(int currentStatus, int lifeNum){

        if(lifeNum == 3)
            return LIVE;
        else if(lifeNum == 2 && currentStatus == LIVE)
            return LIVE;
        return DIED;
    }

    /**
     * count neighbors live size
     * @param map grid
     * @param x x
     * @param y y
     * @return  size
     */
    public int countNeighborsLiveSize(int[][] map, int x, int y){
        int count = 0;
        if(x-1 >=0 && y-1 >= 0 && map[x-1][y-1] == LIVE) ++count;
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
