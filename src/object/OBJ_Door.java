package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {

    GamePanel gp;
    public OBJ_Door(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Door";
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setDialogue() {

        dialogues[0][0] = "You need a key to do this.";
    }

    public void interact() {

        startDialogue(this, 0);
    }
}
