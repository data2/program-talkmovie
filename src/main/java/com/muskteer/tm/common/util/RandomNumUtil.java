package com.muskteer.tm.common.util;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.Random;

public class RandomNumUtil {
    private ByteArrayInputStream image;// 图像
    private String str;// 验证码

    private RandomNumUtil() {
        init();// 初始化属性
    }

    /*
     * 取得RandomNumUtil实例
     */
    public static RandomNumUtil Instance() {
        return new RandomNumUtil();
    }

    /**
     * @Summary 获取begin - end 之间的整数
     * @Author PAN
     * @Since 2013-8-1
     */
    private static int randomInt(int begin, int end) {
        SecureRandom random = new SecureRandom();
        return begin + random.nextInt(end - begin);
    }

    /*
     * 取得验证码图片
     */
    public ByteArrayInputStream getImage() {
        return this.image;
    }

    /*
     * 取得图片的验证码
     */
    public String getString() {
        return this.str;
    }

    public String getStr() {
        return str;
    }

    private void init() {
        // 在内存中创建图象
        int width = 105, height = 30;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(new Color(252, 252, 252));
        g.fillRect(0, 0, width, height);

        //画正弦干扰线
        g.setColor(new Color(randomInt(50, 200), randomInt(50, 200), randomInt(50, 200)));
        g.drawRect(1, 1, width - 2, height - 2);

        // 设定字体
        g.setFont(new Font(null, Font.ITALIC + Font.BOLD, 22));
        // 取随机产生的认证码(6位数字)
        String sRand = "";
        for (int i = 0; i < 6; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 16 * i + 6, 25);
        }
        // 赋值验证码
        this.str = sRand;

        // 图象生效
        g.dispose();
        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageOutputStream imageOut = ImageIO
                    .createImageOutputStream(output);
            ImageIO.write(image, "JPEG", imageOut);
            imageOut.close();
            input = new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
            System.out.println("验证码图片产生出现错误：" + e.toString());
        }

        this.image = input;/* 赋值图像 */
    }

    /*
     * 给定范围获得随机颜色
     */
    @SuppressWarnings({"unused"})
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}