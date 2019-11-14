package Enhancement;



import javax.imageio.ImageIO;
import java.io.*;
import java.util.Arrays;
import java.awt.image.*;
import java.awt.Color;


public class MyReader
{

    public static BufferedImage readImageIntoBufferedImage(String fileName)
    {
        BufferedImage image = null;


        if ( !fileName.endsWith(".jpg") )
        {
            System.out.println("This is not a jpg file.");
            return null;
        }

        try {
            // Read from a file
            File file = new File(fileName);
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Could not open file.");
            return null;
        }
        return image;
    }

    // This method gives the number of bands (number of color channels) in for an input image
    public static int[] imgDimension(String fileName){
        BufferedImage image = null;
        int[] stat = new int[3];

        image = readImageIntoBufferedImage(fileName);
        WritableRaster raster = image.getRaster();

        stat[0] = raster.getNumBands();
        stat[1] = raster.getHeight();
        stat[2] = raster.getWidth();

        return stat;
    } 


    public static int[][][] getRGB(String fileName )
    {
        BufferedImage image = null;
        int height, width, numbands;

        image = readImageIntoBufferedImage( fileName );

        WritableRaster raster = image.getRaster();

        int[] pixelValues = new int[ raster.getNumBands() ];
        height = raster.getHeight();
        width = raster.getWidth();
        numbands = raster.getNumBands();

        if (numbands < 3)
        {
            System.out.println("Cannot be converted: Not an RGB image");
            return null;
        }
        int rasterValues[][][] = new int[numbands][height][width];

        for ( int x = 0; x < width; x++ )
            for( int y = 0; y <height; y++ )
            {
                pixelValues = raster.getPixel( x, y, pixelValues);

                rasterValues[0][y][x] = pixelValues[0];
                rasterValues[1][y][x] = pixelValues[1];
                rasterValues[2][y][x] = pixelValues[2];
            }

        return rasterValues;
    }
    
    //Convert RGB to  0.21 R + 0.72 G + 0.07 B
    public static int[][][] convertRGBtoLuminosity(String fileName )
    {
        BufferedImage image = null;
        int height, width, numbands;

        image = readImageIntoBufferedImage( fileName );

        WritableRaster raster = image.getRaster();

        int[] pixelValues = new int[ raster.getNumBands() ];
        height = raster.getHeight();
        width = raster.getWidth();
        numbands = raster.getNumBands();

        if (numbands < 3)
        {
            System.out.println("Cannot be converted: Not an RGB image");
            return null;
        }

        //0.21 R + 0.72 G + 0.07 B
        int rasterValues[][][] = new int[numbands][height][width];

        for ( int x = 0; x < width; x++ )
            for( int y = 0; y <height; y++ )
            {
                pixelValues = raster.getPixel( x, y, pixelValues);
                
                int[] A = pixelValues;
                Arrays.sort(A);
//                double X = (A[A.length-1]+A[0])/2;
                
//                double Y = (X  + X + X)/3;
                
//                double Z = (0.299*Y + 0.587*Y + 0.114*Y); 
                
//                double X = (0.299*pixelValues[0]  + 0.587*pixelValues[1]  + 0.114*pixelValues[2]);
                double X = (0.21*pixelValues[0]  + 0.72*pixelValues[1]  + 0.07*pixelValues[2]); 
                
                
               
                // Convert RGB pixelValues to CIE XYZ values
                int CIEX = (int) (X);
                int CIEY = (int) (X);
                int CIEZ = (int) (X);

                rasterValues[0][y][x] = CIEX;
                rasterValues[1][y][x] = CIEY;
                rasterValues[2][y][x] = CIEZ;
                
//                rasterValues[0][y][x] = pixelValues[0];
//                rasterValues[1][y][x] = pixelValues[1];
//                rasterValues[2][y][x] = pixelValues[2];
            }

        return rasterValues;
    }
    
    //Convert RGB to  0.21 R + 0.72 G + 0.07 B
    public static BufferedImage convertRGBtoGamma(String fileName , double gamma)
    {
        BufferedImage image = null;
        int height, width, numbands;

        image = readImageIntoBufferedImage( fileName );

        WritableRaster raster = image.getRaster();
       
        int[] pixelValues = new int[ raster.getNumBands() ];
        int alpha, red, green, blue;
        int newPixel;
        
        height = raster.getHeight();
        width = raster.getWidth();
        numbands = raster.getNumBands();
        BufferedImage gamma_cor = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        


        //allocate our two dimensional array. 0.21 R + 0.72 G + 0.07 B
        int rasterValues[][][] = new int[numbands][height][width];
        double gamma_new = 1 / gamma;
        for ( int x = 0; x < width; x++ )
            for( int y = 0; y <height; y++ )
            {
                pixelValues = raster.getPixel( x, y, pixelValues);
                
              alpha = new Color(image.getRGB(x, y)).getAlpha();
              red = new Color(image.getRGB(x, y)).getRed();
              green = new Color(image.getRGB(x, y)).getGreen();
              blue = new Color(image.getRGB(x, y)).getBlue();
              
              
              red = (int) (255 * (Math.pow((double) red / (double) 255, gamma_new)));
              green = (int) (255 * (Math.pow((double) green / (double) 255, gamma_new)));
              blue = (int) (255 * (Math.pow((double) blue / (double) 255, gamma_new)));
   
              // Return back to original format
              newPixel = colorToRGB(alpha, red, green, blue);
   
              // Write pixels into image
              gamma_cor.setRGB(x, y, newPixel);                

            }

        return gamma_cor;
    }
    
    // Convert R, G, B, Alpha to standard 8 bit
    private static int colorToRGB(int alpha, int red, int green, int blue) {
 
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;
 
        return newPixel;
 
    }

}
