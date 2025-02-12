package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gp;
    public static final String objName = "Key";

    public OBJ_Key(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIt opens a door.";
        price = 20;
        stackable = true;

    }

    public void setDialogue() {

        dialogues[0][0] = "You use the " + name + " and open the door.";

        dialogues[1][0] = "That won't work!";

    }

    public boolean use(Entity entity) {

        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            setDialogue();
            startDialogue(this, 0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            setDialogue();
            startDialogue(this, 1);
            return false;
        }
    }
}
