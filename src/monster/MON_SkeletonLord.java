package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_SkeletonLord extends Entity {

    GamePanel gp;
    public static final String monName = "Skeleton Lord";

    public MON_SkeletonLord(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_monster;
        boss = true;
        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 50;
        knockBackPower = 5;


        // Solid area of the SkeletonLord
        int size = gp.tileSize * 5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;

        getImage();
        getAttackImage();
    }

    public void getImage() {

        int i = 5;

        if (!inRage) {
            // the two images down1 and down2 are used for all directions
            up1 = setup("/monster/skeletonlord/skeletonlordMove/skeletonlord_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/skeletonlord/skeletonlordMove/skeletonlord_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/monster/skeletonlord/skeletonlordMove/skeletonlord_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/skeletonlord/skeletonlordMove/skeletonlord_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/monster/skeletonlord/skeletonlordMove/skeletonlord_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/skeletonlord/skeletonlordMove/skeletonlord_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/monster/skeletonlord/skeletonlordMove/skeletonlord_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/skeletonlord/skeletonlordMove/skeletonlord_right_2", gp.tileSize * i, gp.tileSize * i);
        }
        else {
            // the two images down1 and down2 are used for all directions
            up1 = setup("/monster/skeletonlord/skeletonlordMovePhase2/skeletonlord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/skeletonlord/skeletonlordMovePhase2/skeletonlord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/monster/skeletonlord/skeletonlordMovePhase2/skeletonlord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/skeletonlord/skeletonlordMovePhase2/skeletonlord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/monster/skeletonlord/skeletonlordMovePhase2/skeletonlord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/skeletonlord/skeletonlordMovePhase2/skeletonlord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/monster/skeletonlord/skeletonlordMovePhase2/skeletonlord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/skeletonlord/skeletonlordMovePhase2/skeletonlord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);
        }

    }

    public void getAttackImage() {

        int i = 5;

        if (!inRage) {
            attackUp1 = setup("/monster/skeletonlord/skeletonlordAttack/skeletonlord_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monster/skeletonlord/skeletonlordAttack/skeletonlord_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/monster/skeletonlord/skeletonlordAttack/skeletonlord_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monster/skeletonlord/skeletonlordAttack/skeletonlord_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/monster/skeletonlord/skeletonlordAttack/skeletonlord_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monster/skeletonlord/skeletonlordAttack/skeletonlord_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/monster/skeletonlord/skeletonlordAttack/skeletonlord_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monster/skeletonlord/skeletonlordAttack/skeletonlord_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
        }
        else {

            attackUp1 = setup("/monster/skeletonlord/skeletonlordAttackPhase2/skeletonlord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monster/skeletonlord/skeletonlordAttackPhase2/skeletonlord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/monster/skeletonlord/skeletonlordAttackPhase2/skeletonlord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monster/skeletonlord/skeletonlordAttackPhase2/skeletonlord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/monster/skeletonlord/skeletonlordAttackPhase2/skeletonlord_phase2_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monster/skeletonlord/skeletonlordAttackPhase2/skeletonlord_phase2_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/monster/skeletonlord/skeletonlordAttackPhase2/skeletonlord_phase2_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monster/skeletonlord/skeletonlordAttackPhase2/skeletonlord_phase2_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);
        }

    }

    public void setAction() {

        if (!inRage && life < maxLife / 2) {
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }

        if (getTileDistance(gp.player) < 10) {
            moveTowardPlayer(60);
        }
        else {
            // get a random direction
            getRandomDirection(120);
        }

        // Check if it attacks
        if (!attacking) {
            checkAttackOrNot(60, gp.tileSize * 7, gp.tileSize * 5);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
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
