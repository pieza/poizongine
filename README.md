# poizongine
Game engine

<a href="https://github.com/pieza/poizongine/workflows/build">
  <img src="https://github.com/pieza/poizongine/workflows/build/badge.svg" alt="build status"></a>
        
<a href="https://github.com/Pieza/poizongine/issues">
  <img alt="GitHub issues" src="https://img.shields.io/github/issues/Pieza/poizongine"></a>
      
<a href="https://github.com/Pieza/poizongine/stargazers">
  <img alt="GitHub stars" src="https://img.shields.io/github/stars/Pieza/poizongine"></a>

## Installing
Use jitPack: https://github.com/jitpack/jitpack.io

### Gradle
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

```gradle
dependencies {
  implementation 'com.github.pieza:poizongine:Tag'
}
```

### Maven
```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.pieza</groupId>
    <artifactId>poizongine</artifactId>
    <version>Tag</version>
</dependency>
```


## Getting started

### Initialize game
```java
// run game with default settings
Game game = new Game();
game.start();
```

### Setting up game
```java
Game game = new Game();
game.settings.setDebug(true);
game.settings.setScale(4f);
game.settings.getScreenHeight(400);
game.settings.setScreenWidth(800);
game.settings.setFullScreen(false);
game.start();
```
Optional using Settings class:
```java
Game game = new Game(new Settings(800, 400, 60, 4f, false, false, false));
game.start();
```
## Scenes

### Creating scenes
```java
import com.poizon.engine.Game;
import com.poizon.engine.scenes.GameScene;

public class WorldScene extends GameScene {

    public WorldScene() {
        objects.put("PLAYER", new Player());
    }

    @Override
    public void update(Game game) {
      // do something
    }

    @Override
    public void render(Game game) {
      // do something
    }
}
```

### Adding scenes

```
game.addScene("WORLD", new WorldScene());
```

### Selecting scene
```java
game.setScene("TEST");
```

### Selecting scene
```java
game.setScene("TEST");
```

## Game Objects

### Creating game object
```java
import com.poizon.engine.Game;
import com.poizon.engine.audio.SoundClip;
import com.poizon.engine.graphics.ImageTile;

import java.awt.event.KeyEvent;

public class Player extends GameObject {
    private SoundClip sound = new SoundClip("/audio/shot.wav");

    public Player() {
        sprite = new ImageTile("/sprites/player.png", 19, 21);
    }

    @Override
    public void update(Game game) {
        if(game.input.isKeyDown(KeyEvent.VK_SPACE)) {
            sound.play();
        }

        if(game.input.isKey(KeyEvent.VK_D)) {
            positionX += 2;
        }

        if(game.input.isKey(KeyEvent.VK_A)) {
            positionX -= 2;
        }

        if(game.input.isKey(KeyEvent.VK_W)) {
            positionY -= 2;
        }

        if(game.input.isKey(KeyEvent.VK_S)) {
            positionY += 2;
        }
    }
}
```
