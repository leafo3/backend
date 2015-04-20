package com.spaceapps.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Chuy on 4/12/2015.
 */
public class ImageUtils {

    private static final String PROCESSED_IMAGE_NAME = "_processed.jpg";
    private static final double RAD = 0.3;
    private static final double EPS = 80;

    public static double processImage(String path)throws IOException{
        Object[] resultArray = new Object[2];
        File file = new File(path);
        BufferedImage bImage = ImageIO.read(file);

        int N = bImage.getHeight(), M = bImage.getWidth();
        int[] pixel;
        float [][]r = new float[N][M];
        float [][]g = new float[N][M];
        float [][]b = new float[N][M];

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                pixel = bImage.getRaster().getPixel(j, i, new int[3]);
                r[i][j] = pixel[0];
                g[i][j] = pixel[1];
                b[i][j] = pixel[2];
            }
        }

        float[] hr = histAcum(r, N, M);
        float[] hg = histAcum(g, N, M);
        float[] hb = histAcum(b, N, M);

        float[][] sr = new float[N][M];
        float[][] sg = new float[N][M];
        float[][] sb = new float[N][M];
        float[][] s = new float[N][M];
        //System.out.printf(r[512][279] + "");
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                //System.out.println("i: " + i + ", j: " + j);
                sr[i][j] = hr[(int)r[i][j]];
                sg[i][j] = hg[(int)g[i][j]];
                sb[i][j] = hb[(int)b[i][j]];
            }
        }

        //get color components
        int[] firstPixel = bImage.getRaster().getPixel(0, 0, new int[3]);
        float x = firstPixel[0]; //red
        float y = firstPixel[1]; //green
        float z = firstPixel[2]; //blue
        //falseColor
        int damage = 0;
        int total = 0;
        double sum = 0;
        double sqrt = 0;

        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                sum = ((double)(Math.pow(sr[i][j], 2))) + ((double)Math.pow(sg[i][j], 2)) + ((double)Math.pow(sb[i][j], 2));
                sqrt = Math.sqrt(sum);
                if(sqrt < RAD){
                    damage ++;
                    s[i][j] = 0;
                }
                else if(Math.sqrt(((double)Math.pow(sr[i][j] - 1, 2)) + ((double)Math.pow(sg[i][j] - 1, 2)) + ((double)Math.pow(sb[i][j] - 1, 2))) < RAD)
                    s[i][j] = 1;
                else
                    s[i][j] = 2;

                sum = (Math.pow((double)r[i][j] - x, 2)) + (Math.pow((double)g[i][j] - y, 2)) + (Math.pow((double)b[i][j] - z, 2));
                if(Math.sqrt(sum) >= EPS)
                    //s[i][j] = 0;
                    total ++;
            }
        }
        //print array
        /**for(int i = 0; i < s.length; i ++)
         for(int j = 0; j < s[0].length; j ++)
         System.out.println("Value: " + s[i][j]);
         **/
        //create new image
        BufferedImage result = new BufferedImage(M, N, BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < N; i ++)
            for(int j = 0; j < M; j ++){
                int rgb = 0;
                switch((int)s[i][j]){
                    case 0:
                        //blue
                        rgb = Color.blue.getRGB();
                        break;
                    case 1:
                        //red
                        rgb = Color.red.getRGB();
                        break;
                    case 2:
                        //black
                        rgb = Color.red.getRGB();
                        break;
                }
                result.setRGB(j, i, rgb);
            }

        //write the new image
        File resultFile = new File(path + "_result.jpg");
        ImageIO.write(result, "jpg", resultFile);
        double percentage = ((double)damage/ (double)total) * 100f;
        return percentage;
    }

    private static float[] histAcum(float[][] array, int n, int m){
        float[] h = new float[256];
        float[] hr = new float[256];
        for(int i = 0; i <= 255; i ++){
            h[i] = 0f;
            hr[i] = 0f;
        }

        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                h[(int)array[i][j]] ++;
            }
        }
        float sum = 0;
        for(int i = 0; i <= 255; i ++)
            sum += h[i];
        for(int i = 0; i <= 255; i ++)
            h[i] /= sum;
        for(int i = 0; i <= 255; i ++)
            for(int j = 0; j<= i; j ++)
                hr[i] += h[j];

        return hr;
    }
}
