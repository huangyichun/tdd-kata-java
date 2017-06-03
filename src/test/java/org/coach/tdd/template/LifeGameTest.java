package org.coach.tdd.template;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * life game test .
 */
public class LifeGameTest {

    private LifeGame lifeGame;
    private final int live = 1;
    private final int died = 0;
    private int[][] maps = new int[8][8];
    @Before
    public void initLifeGameParameter() {
        lifeGame = new LifeGame();
        lifeGame.initPoint();
        lifeGame.initProperties();
        lifeGame.init();
    }

    @Test
    public void shouldReturnRightStatusInputMapOneLivePoint() {

        maps[2][2] = 1;
        int[][] map = lifeGame.nextStatus(maps);
        Assert.assertEquals(map[2][2], died);
    }

    @Test
    public void shouldReturnRightStatusInputMapTwoLivePoint() {

        maps[2][2] = 1;
        maps[2][1] = 1;
        int[][] map = lifeGame.nextStatus(maps);
        Assert.assertEquals(map[2][2], died);
        Assert.assertEquals(map[2][1], died);
    }



    @Test
    public void shouldReturnRightStatusInputMapForthLivePoint() {
        maps[2][2] = 1;
        maps[2][3] = 1;
        maps[3][2] = 1;
        maps[3][3] = 1;
        int[][] map = lifeGame.nextStatus(maps);
        Assert.assertEquals(map[2][2], live);
        Assert.assertEquals(map[2][3], live);
        Assert.assertEquals(map[3][2], live);
        Assert.assertEquals(map[3][3], live);
    }

    @Test
    public void shouldReturnRightStatusInputMapSixLivePoint() {
        maps[2][2] = 1;
        maps[3][1] = 1;
        maps[3][3] = 1;
        maps[4][1] = 1;
        maps[4][3] = 1;
        maps[5][2] = 1;

        int[][] map = lifeGame.nextStatus(maps);
        Assert.assertEquals(map[2][2], live);
        Assert.assertEquals(map[3][1], live);
        Assert.assertEquals(map[3][3], live);
        Assert.assertEquals(map[4][1], live);
        Assert.assertEquals(map[4][3], live);
        Assert.assertEquals(map[5][2], live);
    }

    @Test
    public void shouldReturnLiveInputLifeAndLifeNum3() {
        int status = lifeGame.isLifeOrDie(live, 3);
        Assert.assertEquals(status, live);
    }

    @Test
    public void shouldReturnDieInputDieAndLifeNum2() {
        int status = lifeGame.isLifeOrDie(died, 2);
        Assert.assertEquals(status, died);
    }

    @Test
    public void giveSecondXYCountNeighborsLiveSize() {
        maps[4][4] = 1;
        maps[5][4] = 1;
        int count = lifeGame.countNeighborsLiveSize(maps, 5, 5);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void giveOneXYCountNeighborsLiveSize() {
        maps[5][4] = 1;
        int count = lifeGame.countNeighborsLiveSize(maps, 5, 5);
        Assert.assertEquals(count, 1);
    }

    @Test
    public void giveBorderXYCountNeighborsLiveSize() {
        maps[0][1] = 1;
        maps[1][0] = 1;
        maps[1][1] = 1;
        int count = lifeGame.countNeighborsLiveSize(maps, 0, 0);
        Assert.assertEquals(count, 3);
    }


}
