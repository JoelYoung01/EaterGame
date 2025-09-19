Eater Game

## Requirements
- Java JDK 8+ installed and on PATH
- Windows PowerShell or Command Prompt

## Run (using existing compiled classes)
Run these from the project root so images load correctly.

```powershell
java -cp out\production\Eater_Game com.company.RunGame
```

## Compile then run
```powershell
mkdir -Force out\production\Eater_Game
javac -d out\production\Eater_Game -cp src src\com\company\*.java
java -cp out\production\Eater_Game com.company.RunGame
```

## Notes
- The game loads images via relative paths (e.g., `images/...`). Always run from the project root.
- Window size: 1080x720; resizable.
- If you see missing graphics or a blank window, verify your working directory and that the `images` folder exists.

## Controls
- Arrow keys: Move player

## IDE (optional)
If using an IDE, set the main class to `com.company.RunGame` and the working directory to the project root.


