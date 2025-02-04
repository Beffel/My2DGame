package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_GreenSLime extends Entity {

    GamePanel gp;

    public MON_GreenSLime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

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

    public void update() {

        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if (!onPath && tileDistance < 5) {

            // Randomized slime aggro when getting to close, about 50%
            int i = new Random().nextInt(100) + 1;
            if (i > 50) {
                onPath = true;
            }
        }
        // when the player is more than 20 tiles away, the slime looses aggro
        if (onPath && tileDistance > 20) {
            onPath = false;
        }
    }

    public void setAction() {

        if (onPath) {


            // NPC Goal is the Player position
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);

            // if the number hits 100 the slime will shoot a projectile
            int i = new Random().nextInt(200) + 1;
            if (i > 197 && !projectile.alive && shotAvailableCounter == 30) {
                projectile.set(worldX, worldY, direction, true, this);
//                gp.projectileList.add(projectile);

                // CHECK VACANCY
                for (int ii = 0; ii < gp.projectile[1].length; ii++) {
                    if (gp.projectile[gp.currentMap][ii] == null) {
                        gp.projectile[gp.currentMap][ii] = projectile;
                        break;

                    }
                }
                shotAvailableCounter = 0;
            }
        }
        else {
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


    }

    public void damageReaction() {
        actionLockCounter = 0;
//        direction = gp.player.direction;
        onPath = true;
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
