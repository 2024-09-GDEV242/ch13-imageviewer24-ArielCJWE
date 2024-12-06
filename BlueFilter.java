/**
 * An image filter to make the image display in grayscale blues only.
 * 
 * @author Ariel Wong-Edwin
 * @version 1.0
 */
public class BlueFilter extends Filter
{
    /**
     * Constructor for objects of class DarkerFilter.
     * @param name The name of the filter.
     */
    public BlueFilter(String name)
    {
        super(name);
    }

    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public void apply(OFImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int blue = rgb & 0xFF;; //Extracting the blue channel
                int gray = blue; //Grayscale based on blues
                image.setRGB(x, y, (gray << 16) | (gray << 8) | gray);
            }
        }
    }
}
