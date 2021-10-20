package com.example.Service.OnePic;

import com.example.Service.Rotate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component(value = "one")
public class One {

    public void OnePic(String personfile,String backfile,double size,double degree) throws IOException {
        File file_src = new File(backfile);
        Image back = ImageIO.read(file_src);

        File file_dest = new File(personfile);
        BufferedImage bufferedImage_temp = ImageIO.read(file_dest);
        Image person = Rotate.rotateImage(bufferedImage_temp,degree);
        //人像已被旋转

        int width  = back.getWidth(null);
        int height = back.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = bufferedImage.createGraphics();

        graphics2D.drawImage(back.getScaledInstance(width,height,Image.SCALE_SMOOTH),0,0,null);
        int width_p = (int) (person.getWidth(null) * size);
        int height_p = (int) (person.getHeight(null) * size);

        int pos_width = (int) ( (width - width_p) / 2);
        int pos_height = (int)( (height - height_p) / 2);

        graphics2D.drawImage(person.getScaledInstance(width_p,height_p,Image.SCALE_SMOOTH),pos_width,pos_height,null);
        graphics2D.dispose();
        ImageIO.write(bufferedImage,"JPG",new File(personfile));

    }





}
