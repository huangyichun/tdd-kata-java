package org.coach.tdd.template;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LifeGame extends AbstractLifeGame {

    private static final int LIVE = 1;
    private static final int DIED = 0;
    protected Point[] points = new Point[8];
    private int tableSize = 50;
    private int gridSize = 15;
    private Color lifeCell = new Color(204, 0, 0);
    private Color grid = new Color(102, 255, 0);
    private int[][] currentMap;
    private int[][] neighbors;
    private Thread controlThread;
    private int delay;
    private boolean isRun;



    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        while (Thread.currentThread() == controlThread) {
            if (isRun == true) {
                nextStatus(currentMap);
                repaint();
            }
            try {
                currentTime += delay;
                Thread.sleep(Math.max(currentTime - System.currentTimeMillis(), 0));
            } catch (InterruptedException e) {
                System.out.println("thread is end");
                break;
            }
        }
    }

    @Override
    public void init() {
        controlThread = new Thread(this);
        initPoint();
        initProperties();
        isRun = false;
        this.setSize(800, 800);
        setBackground(Color.yellow);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    public void initPoint() {
        points[0] = new Point(-1, -1);
        points[1] = new Point(-1, 0);
        points[2] = new Point(-1, 1);
        points[3] = new Point(0, -1);
        points[4] = new Point(0, 1);
        points[5] = new Point(1, 0);
        points[6] = new Point(1, -1);
        points[7] = new Point(1, 1);
    }

    public void initProperties() {
        Properties properties = new Properties();
        File file = new File("E:\\workspace\\tdd-kata-java\\src\\lifeGame.properties");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
            delay = Integer.parseInt(properties.getProperty("delayTime"));
            tableSize = Integer.parseInt(properties.getProperty("default_size"));
            gridSize = Integer.parseInt(properties.getProperty("grid_size"));
            currentMap = new int[tableSize][tableSize];
            neighbors = new int[tableSize][tableSize];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Graphics g) {
        for (int x = 0; x < tableSize; x++) {
            for (int y = 0; y < tableSize; y++) {
                g.setColor(currentMap[x][y] == 1 ? lifeCell : grid);
                g.fillRect(x * gridSize, y * gridSize, gridSize - 1, gridSize - 1);
            }
        }
    }

    @Override
    public void start() {
        controlThread.start();
    }

    @Override
    public void stop() {
        controlThread = null;
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int cellX = e.getX() / gridSize;
        int cellY = e.getY() / gridSize;
        currentMap[cellX][cellY] = !e.isControlDown() ? 1 : 0;
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        this.mousePressed(e);
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == ' ') {
            isRun = !isRun;
            repaint();
        }
    }

    public void keyReleased(KeyEvent e) {
    }


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
        int isLive = 0;
        if (Integer.valueOf(3).equals(lifeNum)) {
            isLive = LIVE;
        } else if (Integer.valueOf(2).equals(lifeNum) && currentStatus == LIVE) {
            isLive = LIVE;
        }
        return isLive;
    }

    public int countNeighborsLiveSize(int[][] map, int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; ++i) {
            int r = points[i].x + x;
            int l = points[i].y + y;
            if (r >= 0 && r < map.length && l >= 0 && l < map.length && map[r][l] == LIVE) {
                ++count;
            }
        }
        return count;
    }
}
