/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eMart.pojo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author ashis
 */
public class BarCode {
    public static void createImage(String image_name, String myString)
    {
        try{
            Code128Bean code128=new Code128Bean();
            code128.setHeight(15f);
            
            ByteArrayOutputStream bao=new ByteArrayOutputStream();
            BitmapCanvasProvider canvas=new BitmapCanvasProvider(bao,"image/x-png",300,BufferedImage.TYPE_BYTE_BINARY,false,0);
            
            code128.generateBarcode(canvas, myString);
            canvas.finish();
            
            String userId=System.getProperty("user.dir");
            FileOutputStream fos=new FileOutputStream(userId+"\\BarCode\\"+image_name);
            fos.write(bao.toByteArray());
            fos.flush();
            fos.close();
            
        }
        catch(Exception e){
            System.out.println("Exception in generating the barcode"+e.getMessage());
        }
    }
}
