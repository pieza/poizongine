package com.poizon.engine.resources;

import com.poizon.engine.audio.SoundClip;
import com.poizon.engine.graphics.Font;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.ImageTile;

import java.util.HashMap;
import java.util.Map;

/**
 * Resources manager.
 *
 * @author Jose Ulloa
 */
public class Resources {
    public static final Map<String, Image> sprites = new HashMap<>();
    public static final Map<String, ImageTile> spritesheets = new HashMap<>();
    public static final Map<String, SoundClip> sounds = new HashMap<>();
    public static final Map<String, Font> fonts = new HashMap<>();
}
