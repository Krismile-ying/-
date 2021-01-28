package web.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoYsImp {
    private Image img;
    private int width;
    private int height;
    private String path;
    public void photoYs(String fileName,int w,int h)throws IOException {
        path=fileName;
        File file=new File(fileName);
        img= ImageIO.read(file);
        width=img.getWidth(null);
        height=img.getHeight(null);
        if(width/height>w/h){
            resizeByWidth(w);
        }else{
            resizeByHeight(h);
        }
    }
    public void resizeByWidth(int w)throws IOException{
        int h=(int)(height*w/width);
        resize(w,h);
    }
    public void resizeByHeight(int h)throws IOException{
        int w=(int)(width*h/height);
        resize(w,h);
    }
    public void resize(int w,int h)throws IOException{
        BufferedImage image=new BufferedImage(w,h,BufferedImage.SCALE_SMOOTH);
        image.getGraphics().drawImage(img, 0, 0, w, h, null);
        File destFile=new File(path);
        FileOutputStream out=new FileOutputStream(destFile);
        JPEGImageEncoder encoder= JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image);
        out.close();
    }
}
