package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Bat extends Entity {
    GamePanel gp;

    public MON_Bat(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Bat";
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 3;
        life = maxLife;
        attack = 4;
        defense = 0;
        exp = 3;

        // Solid area of the Bat
        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        // the two images down1 and down2 are used for all directions
        up1 = setup("/monster/bat/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/bat/bat_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/bat/bat_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/bat/bat_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/bat/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/bat/bat_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/bat/bat_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/bat/bat_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

        if (onPath) {

//            // Check if it stops chasing
//            checkStopChasingOrNot(gp.player, 18, 100);
//
//            // Search the direction to go
//            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
//
//            // Check if it shoots a projectile
//            checkShootOrNot(200, 30);
        }
        else {
            // check if it starts chasing
//            checkStartChasingOrNot(gp.player, 5, 100);
//
//            // get a random direction
            getRandomDirection(10);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
//        direction = gp.player.direction;
//        onPath = true;
    }

    public void checkDrop() {

        // CAST A DIE?
        int i = new Random().nextInt(100) + 1;

        // SET THE MONSTER DROP
        // 50% of the time it drops a coin
        if (i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        // 25% of the time it drops a heart
        if (i >= 50 && i <= 75) {
            dropItem(new OBJ_Heart(gp));
        }
        // 25% of the time it drops a mana crystal
        if (i > 75) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
