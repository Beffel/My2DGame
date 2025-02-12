package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{

    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setup("/objects/potion_red", (int)(gp.tileSize / 1.5), (int)(gp.tileSize / 1.5));
        description = "[Red Potion]\nHeals you by " + value + ".";
        price = 5;
        stackable = true;
    }

    public void setDialogue() {

        dialogues[0][0] = value + " health have been recovered by \ndrinking the " + name + ".";

    }

    public boolean use(Entity entity) {


        startDialogue(this, 0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }
}
