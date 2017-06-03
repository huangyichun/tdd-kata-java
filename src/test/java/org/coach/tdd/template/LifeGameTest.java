package org.coach.tdd.template;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * life game test
 */
public class LifeGameTest {

    private final static int LIVE = 1;
    private final static int DIED = 0;
    LifeGame lifeGame;
    int[][] map;
    @Before
    public void initLifeGameParameter(){
        lifeGame = new LifeGame();
        map = new int[8][8];
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
        map[4][4] = 1;
        map[5][4] = 1;
        int count = lifeGame.countNeighborsLiveSize(map,5, 5);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void giveOneXYCountNeighborsLiveSize(){
        map[5][4] = 1;
        int count = lifeGame.countNeighborsLiveSize(map,5, 5);
        Assert.assertEquals(count, 1);
    }

    @Test
    public void giveBorderXYCountNeighborsLiveSize(){
        map[0][1] = 1;
        map[1][0] = 1;
        map[1][1] = 1;
        int count = lifeGame.countNeighborsLiveSize(map,0, 0);
        Assert.assertEquals(count, 3);
    }


}
