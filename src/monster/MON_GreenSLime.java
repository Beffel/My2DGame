package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_GreenSLime extends Entity {

    GamePanel gp;

    public MON_GreenSLime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 20;
        life = maxLife;

        // Solid area of the Slime
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        // the two images down1 and down2 are used for all directions
        up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

            // If the Subclass has the same method, it takes the priority, in this case this is the same method like in the Entity class.
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number from 1 to 100

            // The npc has a 1/4 chance to go up, down, right or left
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
