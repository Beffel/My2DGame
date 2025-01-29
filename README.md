# My2DGame

## 📌 Beschreibung
**My2DGame** ist ein 2D-Rollenspiel, das verschiedene Gameplay-Elemente wie Gegner, Power-Ups, Fähigkeiten, NPCs und unterschiedliche Interaktionen bietet. Das Hauptziel dieses Projekts ist es, Objektorientierung (OOP) in **Java** besser zu verstehen und praktische Erfahrung in der Spieleentwicklung zu sammeln.

⚠️ **Hinweis:** Dieses Spiel basiert auf dem **YouTube-Tutorial von RyiSnow**. Alle Credits für das ursprüngliche Konzept gehen an ihn.

---

## 🛠 Technologien & Abhängigkeiten
- **Programmiersprache:** Java
- **Grafik:** Graphics2D
- **GUI:** JFrame
- **Sound:** Java Sound API
- **Externe Bibliotheken/APIs:** (Noch nicht überprüft – Falls vorhanden, bitte ergänzen)

---

## 📂 Projektstruktur
My2DGame/    
├── .idea/ # IntelliJ IDEA-Projektdateien    
│ ├── misc.xml    
│ ├── modules.xml    
│ ├── uiDesigner.xml    
│ ├── vcs.xml    
│ ├── workspace.xml    
├── out/ # Kompilierte Dateien (sollte ignoriert werden)    
├── res/ # Ressourcen wie Fonts, Maps, Sounds, etc.    
│ ├── font/    
│ ├── maps/    
│ ├── monster/    
│ ├── npc/    
│ ├── objects/    
│ ├── player/    
│ ├── projectile/    
│ ├── sound/    
│ ├── tiles/    
│ ├── tiles_interactive/    
├── src/ # Quellcode des Spiels    
│ ├── entity/ # Spieler, NPCs, Gegner, Projektile    
│ │ ├── Entity.java    
│ │ ├── NPC_OldMan.java    
│ │ ├── Particle.java    
│ │ ├── Player.java    
│ │ ├── Projectile.java    
│ ├── main/ # Kernklassen (Game Loop, UI, Events)    
│ │ ├── AssetSetter.java    
│ │ ├── CollisionChecker.java    
│ │ ├── EventHandler.java    
│ │ ├── EventRect.java    
│ │ ├── GamePanel.java    
│ │ ├── KeyHandler.java    
│ │ ├── Main.java    
│ │ ├── Sound.java    
│ │ ├── UI.java    
│ │ ├── UtilityTool.java    
│ ├── tile_interactive/ # Interaktive Objekte (z. B. Bäume, Hindernisse)    
│ │ ├── InteractiveTile.java    
│ │ ├── IT_DryTree.java    
│ │ ├── IT_Trunk.java    
│ ├── tiles/ # Tile-Management    
│ │ ├── Tile.java    
│ │ ├── TileManager.java    
├── .gitignore # Dateien, die nicht getrackt werden sollen    
├── My2DGame.iml # IntelliJ Projektdatei    
├── README.md # Diese Datei    

---

## 🎮 Gameplay & Features

### **🕹 Steuerung**
- **W, A, S, D** – Spielerbewegung
- **Enter** – Angriff
- **P** – Pause
- **C** – Inventar öffnen/schließen
- **W, A, S, D** im Inventar – Navigation
- **Enter** im Inventar – Gegenstand auswählen
- **T** – Player Position & Draw Time anzeigen

### **📌 Aktuelle Features**
✅ Bewegung & Kollisionsabfrage  
✅ Sound & Musik  
✅ Kampfanimationen  
✅ Health & Mana Leiste  
✅ Mehrere Gegenstände & Inventarsystem  
✅ Unterschiedliche Gegner & NPCs  

### **🔮 Zukünftige Features (geplant)**
⏳ Blocken mit Schild  
⏳ Graben mit Schaufel  
⏳ Boote für Wasserpassagen  

---
 
## 📺 Screens & Game States
Das Spiel enthält aktuell folgende Screens:
- **Title Screen**
- **Pause Menu**
- **Game Screen**
- **Inventory Screen**
- **Fullscreen-Modus verfügbar**

---

## 🚀 Installation & Start
### **1️⃣ Voraussetzungen**
- Java Development Kit (JDK 17 oder höher)
- IntelliJ IDEA oder eine andere IDE mit Java-Unterstützung

### **2️⃣ Spiel starten**
1. **Repository klonen:**  
   ```bash
   git clone https://github.com/Beffel/My2DGame.git

## 🔗 Quellen & Credits
- **Tutorial von RyiSnow:** [YouTube-Kanal](https://www.youtube.com/c/RyiSnow)  
- **Java Dokumentation:** [docs.oracle.com](https://docs.oracle.com/en/java/)  
- **GitHub Repository:** [Hier Link zu deinem Repo einfügen]  

