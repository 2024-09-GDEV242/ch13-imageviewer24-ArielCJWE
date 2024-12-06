/**
 * An image filter to make the image display four time at a 
 * franction of the original size, displaying one original image,
 * then one with a red, blue and green tint. Whilst also flipping 
 * it in different ways as well.
 * 
 * @author Ariel Wong-Edwin
 * @version 1.0
 */
public class FlippedWarholFilter extends Filter
{
    /**
     * Constructor for objects of class DarkerFilter.
     * @param name The name of the filter.
     */
    public FlippedWarholFilter(String name)
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
        int height = image.getHeight() / 2;
        int width = image.getWidth() / 2;
        
        OFImage original = cloneImage(image);

        // Create tinted images
        OFImage redTint = cloneImage(image);
        applyRedTint(redTint);
        redTint = flipHorizontally(redTint);

        OFImage greenTint = cloneImage(image);
        applyGreenTint(greenTint);
        greenTint = flipVertically(greenTint);

        OFImage blueTint = cloneImage(image);
        applyBlueTint(blueTint);
        blueTint = flipBoth(blueTint);
        
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                //Top-left is the original image
                image.setRGB(x, y, original.getRGB(x * 2, y * 2));
                
                // Top-right is the red tinted image
                image.setRGB(x + width, y, redTint.getRGB(x * 2, y * 2));
                
                // Bottom-left is the green tinted image
                image.setRGB(x, y + height, greenTint.getRGB(x * 2, y * 2));
                
                // Bottom-right is the blue tinted image
                image.setRGB(x + width, y + height, blueTint.getRGB(x * 2, y * 2));
            }
        }
    }
    private OFImage cloneImage(OFImage source) {
        OFImage clone = new OFImage(source.getWidth(), source.getHeight());
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                clone.setRGB(x, y, source.getRGB(x, y));
            }
        }
        return clone;
    }
    
    /**
     * Flip an image horizontally.
     */
    private OFImage flipHorizontally(OFImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        OFImage flipped = cloneImage(image);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                flipped.setRGB(width - 1 - x, y, image.getRGB(x, y));
            }
        }
        return flipped;
    }

    /**
     * Flip an image vertically.
     */
    private OFImage flipVertically(OFImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        OFImage flipped = cloneImage(image);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                flipped.setRGB(x, height - 1 - y, image.getRGB(x, y));
            }
        }
        return flipped;
    }

    /**
     * Flip an image both horizontally and vertically.
     */
    private OFImage flipBoth(OFImage image) {
        return flipVertically(flipHorizontally(image));
    }

    /**
     * Apply red tint to the image.
     */
    private void applyRedTint(OFImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int red = Math.min(((rgb >> 16) & 0xFF) + 100, 255);
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                image.setRGB(x, y, (red << 16) | (green << 8) | blue);
            }
        }
    }

    /**
     * Apply green tint to the image.
     */
    private void applyGreenTint(OFImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = Math.min(((rgb >> 8) & 0xFF) + 100, 255);
                int blue = rgb & 0xFF;
                image.setRGB(x, y, (red << 16) | (green << 8) | blue);
            }
        }
    }

    /**
     * Apply blue tint to the image.
     */
    private void applyBlueTint(OFImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = Math.min((rgb & 0xFF) + 100, 255);
                image.setRGB(x, y, (red << 16) | (green << 8) | blue);
            }
        }
    }
}
