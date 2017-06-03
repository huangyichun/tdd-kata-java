package org.coach.tdd.template;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

/**
 * life game test .
 */
public class LifeGameTest extends JFrame{

    private final int LIVE = 1;
    private final int DIED = 0;
    LifeGame lifeGame;
    private int[][] maps = new int[8][8];
    @Before
    public void initLifeGameParameter(){
        lifeGame = new LifeGame();
    }

    @Test
    public void shouldReturnRightStatusInputMapOneLivePoint(){
        maps[2][2] = 1;
        int[][] map = lifeGame.nextStatus(maps);
        Assert.assertEquals(maps[2][2], DIED);
    }

    @Test
    public void shouldReturnRightStatusInputMapForthLivePoint(){
        maps[2][2] = 1;
        maps[2][3] = 1;
        maps[3][2] = 1;
        maps[3][3] = 1;
        int[][] map = lifeGame.nextStatus(maps);
        Assert.assertEquals(maps[2][2], LIVE);
        Assert.assertEquals(maps[2][3], LIVE);
        Assert.assertEquals(maps[3][2], LIVE);
        Assert.assertEquals(maps[3][3], LIVE);
    }

    @Test
    public void shouldReturnLiveInputLifeAndLifeNum3(){
        int status = lifeGame.isLifeOrDie(LIVE, 3);
        Assert.assertEquals(status, LIVE);
    }

    @Test
    public void shouldReturnDieInputDieAndLifeNum2(){
        int status = lifeGame.isLifeOrDie(DIED, 2);
        Assert.assertEquals(status, DIED);
    }

    @Test
    public void giveSecondXYCountNeighborsLiveSize(){
        maps[4][4] = 1;
        maps[5][4] = 1;
        int count = lifeGame.countNeighborsLiveSize(maps,5, 5);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void giveOneXYCountNeighborsLiveSize(){
        maps[5][4] = 1;
        int count = lifeGame.countNeighborsLiveSize(maps,5, 5);
        Assert.assertEquals(count, 1);
    }

    @Test
    public void giveBorderXYCountNeighborsLiveSize(){
        maps[0][1] = 1;
        maps[1][0] = 1;
        maps[1][1] = 1;
        int count = lifeGame.countNeighborsLiveSize(maps,0, 0);
        Assert.assertEquals(count, 3);
    }


}
