package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {

        dialogues[0][0] = "Hello Traveler!";
        dialogues[0][1] = "So you've come to this island for \nit's treasure?";
        dialogues[0][2] = "I used to be a Wizard but now... \nI'm a bit too old for adventures.";
        dialogues[0][3] = "Farewell and good luck, Traveler!";

        dialogues[1][0] = "If you become tired, rest at the water.";
        dialogues[1][1] = "However, the monsters reappear if you rest.\nI don't recall the reason for that.";
        dialogues[1][2] = "Don't forget to rest.";

        dialogues[2][0] = "I wonder how to open that door...";
    }

    // this method sets the Characters movement behavior
    public void setAction() {

        if (onPath) {

//            int goalCol = 12;
//            int goalRow = 9;

            // NPC Goal is the Player position
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);
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

    public void speak() {

        // Character Specific stuff
        facePlayer();
        startDialogue(this, dialogueSet);

//        onPath = true;
    }
}

