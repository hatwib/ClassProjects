package Enhancement;


import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;

public class MyWriter
{
    public static boolean writeImage(String inputFileName, String outputFileName, int[][][] imageData)
    {
        BufferedImage inputImage = MyReader.readImageIntoBufferedImage( inputFileName );
    

        BufferedImage outputImage = new BufferedImage( inputImage.getWidth(), inputImage.getHeight(),
                                                       inputImage.getType() );
        WritableRaster outputRaster, inputRaster;
        inputRaster = inputImage.getRaster();
        outputRaster = inputRaster.createCompatibleWritableRaster();

        int[] pixelData = new int[ outputRaster.getNumBands() ];
        System.out.println("Write Image ..... " );
        int height, width;
        height = outputRaster.getHeight();
        width = outputRaster.getWidth();

        for ( int y = 0; y < height; y++ )
            for ( int x = 0; x < width; x++ )
            {
                for ( int i = 0; i < 3; i++ )
                {
                    pixelData[ i ] = imageData[i][y][x];
                }
                outputRaster.setPixel(x, y, pixelData );
            }
        outputImage.setData( outputRaster );

        File outputFile = new File( outputFileName );
        try
        {
            if ( !ImageIO.write( outputImage, "jpg", outputFile ))
            {
                System.out.println("Could not find image format for output image.");
                return false;
            }
        }
        catch ( Exception e )
        {
            System.out.println("Could not write output file.");
            return false;
        }
        return true;
    }
}
