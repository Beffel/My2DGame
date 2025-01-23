package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX; // Final Variable so the players Screen position doesn't change
    public final int screenY; // Final Variable so the players Screen position doesn't change
    int standCounter = 0;
    public boolean attackCanceled = false;

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

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23; // Player starting point
        worldY = gp.tileSize * 21; // Player starting point

//        worldX = gp.tileSize * 10;
//        worldY = gp.tileSize * 13;

        speed = 4;
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6;  // 6 life means 3 hearts
        life = maxLife; // Players current life
        strength = 1; // The more strength the player has, the more damage he deals.
        dexterity = 1; // The more dexterity the player has, the less damage he receives.
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack();  // the total attack value is decided by strength and weapon
        defense = getDefense(); // the total defense value is decided by dexterity and shield
    }

    public int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {

        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {

        attackUp1 = setup("/player/attacking sprites/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/attacking sprites/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/attacking sprites/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/attacking sprites/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/attacking sprites/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/attacking sprites/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/attacking sprites/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/attacking sprites/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    public void update() {

        if (attacking) {
            attacking();
        }

        else if (keyH.upPressed == true || keyH.downPressed == true ||
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
            collisionOn = false;
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



            // IF COLISSION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false && !keyH.enterPressed) {
                switch(direction) {
                    case "up":  worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right":  worldX += speed; break;
                }
            }

            if (keyH.enterPressed && !attackCanceled) {
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
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

    public void attacking() {

        spriteCounter++;

        if (spriteCounter <= 5) {
            // This shows attack image 1 in the first 5 frames
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            // This shows image2 in the nextx 20 frames
            spriteNum = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the AttackArea
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += gp.tileSize; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += gp.tileSize; break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // After checking collision, we restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }

        if (spriteCounter > 25) {
            // here it gets reset, attacking animation gets finished
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {

        if (i != 999) {  // if the index is not 999 then the player has touched an object, if it is 999 the player hasn't touched an object

        }
    }

    public void interactNPC(int i) {

        if (gp.keyH.enterPressed) {

            if (i != 999) {  // if the index is not 999 then the player has touched an NPC, if it is 999 the player hasn't touched an NPC
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }

        }
    }

    public void contactMonster(int i) {

        if (i != 999) {

            if (!invincible) {
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monster[i].invincible) {

                gp.playSE(5);
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction) {
            case "up":
                if(!attacking) {
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}
                }
                if(attacking){
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1) {image = attackUp1;}
                    if(spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if(!attacking) {
                    if(spriteNum == 1) {image = down1;}
                    if(spriteNum == 2) {image = down2;}
                }
                if(attacking){
                    if(spriteNum == 1) {image = attackDown1;}
                    if(spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if(!attacking) {
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2) {image = left2;}
                }
                if(attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1) {image = attackLeft1;}
                    if(spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if(!attacking) {
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2) {image = right2;}
                }
                if(attacking){
                    if(spriteNum == 1) {image = attackRight1;}
                    if(spriteNum == 2) {image = attackRight2;}
                }
                break;
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null);

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:" + invincibleCounter, 10, 400);
    }
}
