package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX; // Final Variable so the players Screen position doesn't change
    public final int screenY; // Final Variable so the players Screen position doesn't change
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        // Calling the constructor of the superclass of this class
        // If gp is not passed when we instantiate a class, the Entity class can not receive this gp
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize / 2); // Substract a half tile length from both screenX and screenY
        screenY = gp.screenHeight/2 - (gp.tileSize / 2); // Substract a half tile length from both screenX and screenY

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23; // Player starting point
        worldY = gp.tileSize * 21; // Player starting point

//        worldX = gp.tileSize * 10;
//        worldY = gp.tileSize * 13;

        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;  // 6 life means 3 hearts
        life = maxLife; // Players current life
    }

    public void getPlayerImage() {

        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");
    }

    public void update() {

            if (keyH.upPressed == true || keyH.downPressed == true ||
                    keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
                if (keyH.upPressed) {
                    direction = "up";

                } else if (keyH.downPressed) {
                    direction = "down";
                } else if (keyH.leftPressed) {
                    direction = "left";
                } else if (keyH.rightPressed) {
                    direction = "right";
                }

                // CHECK TILE COLISSION
                colissionOn = false;
                gp.cChecker.checkTile(this);

                // CHECK OBJECT COLISSION
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex); // NOte

                // CHECK NPC COLLISSION
                int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
                interactNPC(npcIndex);

                // CHECK MONSTER COLLISION
                int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
                contactMonster(monsterIndex);


                // CHECK EVENT
                gp.eHandler.checkEvent();

                gp.keyH.enterPressed = false;

                // IF COLISSION IS FALSE, PLAYER CAN MOVE
                if(colissionOn == false && !keyH.enterPressed) {
                    switch(direction) {
                        case "up":  worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right":  worldX += speed; break;
                    }
                }

                gp.keyH.enterPressed = false;

                spriteCounter++;
                if(spriteCounter > 12) {
                    if(spriteNum == 1) {
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                    }
            }
            else {  // Set player to stand still position
                standCounter++;
                if (standCounter == 20) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }

            // This needs to be outside of Key if Statement
            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
    }

    public void pickUpObject(int i) {

        if (i != 999) {  // if the index is not 999 then the player has touched an object, if it is 999 the player hasn't touched an object

        }
    }

    public void interactNPC(int i) {

        if (i != 999) {  // if the index is not 999 then the player has touched an NPC, if it is 999 the player hasn't touched an NPC

            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {

        if (i != 999) {

            if (!invincible) {
                life -= 1;
                invincible = true;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize)
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, screenX, screenY,null);

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:" + invincibleCounter, 10, 400);
    }
}
