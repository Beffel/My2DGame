package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    public void setDialogue() {

        dialogues[0] = "Hello Traveler!";
        dialogues[1] = "So you've come to this island for \nit's treasure?";
        dialogues[2] = "I used to be a Wizard but now... \nI'm a bit too old for adventures.";
        dialogues[3] = "Farewell and good luck, Traveler!";
//        dialogues[0] = "OK krass";
//        dialogues[1] = "OK krass, OK krass, OK krass, \nOK krass, OK krass";
//        dialogues[2] = "OK krass, OK krass, OK krass, \nOK krass, OK krass, \nOK krass, OK krass, OK krass, \nOK krass, OK krass";
//        dialogues[3] = "OK krass, OK krass, OK krass, \nOK krass, OK krass, \nOK krass, OK krass, OK krass, \nOK krass, OK krass, OK krass";
    }

    // this method sets the Characters movement behavior
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

    public void speak() {

        // Character Specific stuff
        super.speak();
    }
}

