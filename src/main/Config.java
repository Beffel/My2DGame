package main;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void safeConfig() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            // FULL SCREEN
            if (gp.fullScreenOn) {
                bw.write("Full Screen On");
            }
            if (!gp.fullScreenOn) {
                bw.write("Full Screen Off");
            }
            bw.newLine();

            // MUSIC VOLUME
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            // SE VOLUME
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            // CREATE TEMPORARY STRING
            String s = br.readLine();

            // FULL SCREEN
            if (s.equals("Full Screen On")) {
                gp.fullScreenOn = true;
            }
            if (s.equals("Full Screen Off")) {
                gp.fullScreenOn = false;
            }

            // MUSIC VOLUME
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            // SE VOLUME
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
