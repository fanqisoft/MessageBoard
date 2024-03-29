package cn.coreqi.controller;

import cn.coreqi.entities.ImageCode;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.coreqi.constants.ValidateConstants;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class ValidateController {

    @RequestMapping("code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = createImageCode(request);
        HttpSession session = request.getSession();
        session.setAttribute(ValidateConstants.SESSION_KEY,imageCode);

        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        //response.setDateHeader("Expires", 0);

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
        encoder.encode(imageCode.getImage());

        //ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    private ImageCode createImageCode(HttpServletRequest request) {
        int width = 67;
        int height = 23;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200,250));
        g.fillRect(0,0,width,height);
        g.setFont(new Font("Times New Roman",Font.ITALIC,20));
        g.setColor(getRandColor(160,200));
        for (int i = 0;i < 155; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }
        String sRand = "";
        for(int i = 0;i < 4; i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
            g.drawString(rand,13 * i + 6,16);
        }
        g.dispose();
        return new ImageCode(image,sRand,60);
    }

    /**
     * 生成随机背景条纹
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255){
            fc = 255;
        }
        if(bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }
}
