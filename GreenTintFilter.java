/**
 * An image filter to make the image display a tint of green only.
 * 
 * @author Ariel Wong-Edwin
 * @version 1.0
 */
public class GreenTintFilter extends Filter
{
    /**
     * Constructor for objects of class DarkerFilter.
     * @param name The name of the filter.
     */
    public GreenTintFilter(String name)
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
                int red = (rgb >> 16) & 0xFF;; //Applying the red tint
                int green = Math.min(((rgb >> 8) & 0xFF) + 100, 255);
                int blue = rgb & 0xFF;
                image.setRGB(x, y, (red << 16) | (green << 8) | blue);
            }
        }
    }
}