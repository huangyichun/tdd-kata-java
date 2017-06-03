package org.coach.tdd.template;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * life game test
 */
public class LifeGameTest {

    LifeGame lifeGame;
    @Before
    public void initLifeGameParameter(){
        lifeGame = new LifeGame();
    }

    @Test
    public void giveXYCountNeighborsLiveSize(){
        int[][] map = new int[8][8];
        map[4][4] = 1;
        map[5][4] = 1;
        int count = lifeGame.countNeighborsLiveSize(map,5, 5);
        Assert.assertEquals(count, 2);
    }

}
