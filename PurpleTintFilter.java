
/**
 * An image filter to make the image display a tint of purple to the image.
 * 
 * @author Ariel Wong-Edwin
 * @version 1.0
 */
public class PurpleTintFilter extends Filter
{
    /**
     * Constructor for objects of class DarkerFilter.
     * @param name The name of the filter.
     */
    public PurpleTintFilter(String name)
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
                
                int red = ((rgb >> 16) & 0xFF);; //Applying the red tint
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                
                red = Math.min(red + 100, 255);  // Add 100 to red, cap at 255
                blue = Math.min(blue + 100, 255); // Add 100 to blue, cap at 255
                green = Math.max(green - 50, 0); 
                
                int purpleTintedRgb = (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, purpleTintedRgb);
            }
        }
    }
}