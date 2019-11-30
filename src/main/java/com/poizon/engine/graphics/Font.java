package com.poizon.engine.graphics;

/**
 * Handle font as an image to render it.
 *
 * @author Jose Ulloa
 */
public class Font {
    private Image fontImage;
    private int[] offsets;
    private int[] widths;

    private final int NUM_CHARACTERS = 59;

    public Font(String path) {
        fontImage = new Image(path);
        offsets = new int[NUM_CHARACTERS];
        widths = new int[NUM_CHARACTERS];

        int unicode = 0;

        for (int i = 0; i < fontImage.getWidth(); i++) {
            if(fontImage.getPixels()[i] == Color.BLUE) {
                offsets[unicode] = i;
            }

            if(fontImage.getPixels()[i] == Color.YELLOW) {
                widths[unicode] = i - offsets[unicode];
                unicode++;
            }
        }
    }

    public Image getFontImage() {
        return fontImage;
    }

    public int[] getOffsets() {
        return offsets;
    }

    public int[] getWidths() {
        return widths;
    }
}
