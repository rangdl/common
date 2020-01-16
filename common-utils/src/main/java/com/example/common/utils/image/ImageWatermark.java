package com.example.common.utils.image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.imageio.plugins.common.ImageUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;

/**
 * @ClassName Image
 * @Description TODO
 * @Author rdl
 * @Date 2020/1/15 17:10
 * @Version 1.0
 **/
public class ImageWatermark {
    /**
     * 几种常见的图片格式
     */
    public static String IMAGE_TYPE_GIF = "gif";// 图形交换格式
    public static String IMAGE_TYPE_JPG = "jpg";// 联合照片专家组
    public static String IMAGE_TYPE_JPEG = "jpeg";// 联合照片专家组
    public static String IMAGE_TYPE_BMP = "bmp";// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
    public static String IMAGE_TYPE_PNG = "png";// 可移植网络图形
    public static String IMAGE_TYPE_PSD = "psd";// Photoshop的专用格式Photoshop
    private static BASE64Encoder base64en = new BASE64Encoder();
    private static BASE64Decoder base64de = new BASE64Decoder();

    // 水印图片base64码
    private static String shuiyingImg = "";

    /**
     * @Description TODO 根据base64码加上水印后返回新的base64码
     * @Param [srcStr] 照片base64字符串 UdateLocalStandClassDatajava2UdateLocalStandClassDatajava2
     * @return java.lang.String
     * @Author rdl
     * @Date 2020/1/16 15:49
     **/
    public final static String addWatermark(String srcStr) {
        // 根据传递的base64图片的大小来决定水印图片的大小。
        // 处理透明信息
        String newStr = "";
        float alpha = 0.3F;
        int srcWidth = 0;
        int srcHeight = 0;
        byte[] b;
        try {
            b = base64de.decodeBuffer(srcStr);
            InputStream is = new java.io.ByteArrayInputStream(b);
            BufferedImage src = ImageIO.read(is);

            if (src != null) {
                srcWidth = src.getWidth(null);
                srcHeight = src.getHeight(null);
                if (srcWidth <= 0 || srcHeight <= 0)
                    return null;
                // 根据原始图片变换水印图片的尺寸
                BufferedImage waterMark = resize(shuiyingImg, srcWidth, srcHeight);
                /* 添加水印 */
                BufferedImage img = new java.awt.image.BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_USHORT_565_RGB);
                // 创建画板
                Graphics2D graph = img.createGraphics();
                // 把原图印到图板上
                graph.drawImage(src, null, 0, 0);
                // 设置透明度,alpha
                graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
                // 画水印图片
                graph.drawImage(waterMark, null, 0, 0);
                /* 把图片转换为字节 */
                ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
                encoder.encode(img);
                byte[] resultByte = jpegOutputStream.toByteArray();
                ImageIO.write(img, "jpg", new File("C:/Users/apple/Desktop/20170106213913407.jpg"));
                System.out.println("加水印完成");
                graph.dispose();
                // System.out.println(base64en.encode(resultByte));
                newStr = base64en.encode(resultByte);
            } else {
                System.out.println(11);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newStr;
    }

    /**
     * @Description TODO 根据图片大小，自动变化水印图片大小。
     * @param src
     * @param w	    原图片宽度
     * @param h        元图片高度
     * @return java.awt.image.BufferedImage 返回image
     * @Author rdl
     * @Date 2020/1/16 16:11
     **/
    public static BufferedImage resize(String src, int w, int h) {
        byte[] b;
        // 加载内存中的水印图片
        try {
            b = base64de.decodeBuffer(src);
            InputStream is = new java.io.ByteArrayInputStream(b);
            BufferedImage img = ImageIO.read(is);
            // 获得适合的缩放比率，即以在规定缩略尺寸中完整显示图片内容的同时又保证最大的缩放比率
            // 根据比例画出缓存图像
            BufferedImage mini = new java.awt.image.BufferedImage(w, h, BufferedImage.TYPE_USHORT_565_RGB);
            Graphics2D gmini = mini.createGraphics();
            gmini.setBackground(Color.WHITE);
            // 让生成的图片按相同的比例变换
            gmini.clearRect(0, 0, w, h);

            AffineTransform trans = new AffineTransform();
            // 长和宽同时变换
            trans.scale((double) w / img.getWidth(), (double) h / img.getHeight());
            gmini.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.7f));
            AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
            gmini.drawImage(img, op, 0, 0);
            gmini.dispose();
            return mini;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String imgFile ="C:/Users/rdl/Pictures/Screenshots/QQ截图20200108142030.png";
        String imgFile2 ="C:/Users/rdl/Pictures/Screenshots/123.png";

        float alpha = 0.5f;
        pressText("呵呵哒",imgFile,imgFile2,Font.DIALOG,Font.PLAIN,Color.DARK_GRAY ,18,10,20,alpha);
    }

    /**
     * @Description TODO 缩放图像（按比例缩放）
     * @param srcImageFile      源图像文件地址
     * @param result            缩放后的图像地址
     * @param scale             缩放比例
     * @param flag              缩放选择:true 放大; false 缩小;
     * @return void
     * @Author rdl
     * @Date 2020/1/16 16:07
     **/
    public final static void scale(String srcImageFile, String result, int scale, boolean flag) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
            if (flag) {// 放大
                width = width * scale;
                height = height * scale;
            } else {// 缩小
                width = width / scale;
                height = height / scale;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description TODO 缩放图像（按高度和宽度缩放）
     * @param srcImageFile	源图像文件地址
     * @param result	        缩放后的图像地址
     * @param height	        缩放后的高度
     * @param width	        缩放后的宽度
     * @param bb              比例不对时是否需要补白：true为补白; false为不补白;
     * @return void
     * @Author rdl
     * @Date 2020/1/16 16:09
     **/
    public final static void scale(String srcImageFile, String result, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; // 缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {// 补白
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description TODO 图像切割(按指定起点坐标和宽高切割)
     * @param srcImageFile	源图像地址
     * @param result	        切片后的图像地址
     * @param x	            目标切片起点坐标X
     * @param y	            目标切片起点坐标Y
     * @param width	        目标切片宽度
     * @param height          目标切片高度
     * @return void
     * @Author rdl
     * @Date 2020/1/16 16:09
     **/
    public final static void cut(String srcImageFile, String result, int x, int y, int width, int height) {
        try {
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight(); // 源图宽度
            int srcHeight = bi.getWidth(); // 源图高度
            if (srcWidth > 0 && srcHeight > 0) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
                Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
                g.dispose();
                // 输出为文件
                ImageIO.write(tag, "JPEG", new File(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description TODO 图像切割（指定切片的行数和列数）
     * @param srcImageFile 源图像地址
     * @param descDir      切片目标文件夹
     * @param rows         目标切片行数。默认2，必须是范围 [1, 20] 之内
     * @param cols         目标切片列数。默认2，必须是范围 [1, 20] 之内
     * @return void
     * @Author rdl
     * @Date 2020/1/16 16:13
     **/
    public final static void cut(String srcImageFile, String descDir, int rows, int cols) {
        try {
            if (rows <= 0 || rows > 20)
                rows = 2; // 切片行数
            if (cols <= 0 || cols > 20)
                cols = 2; // 切片列数
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight(); // 源图宽度
            int srcHeight = bi.getWidth(); // 源图高度
            if (srcWidth > 0 && srcHeight > 0) {
                Image img;
                ImageFilter cropFilter;
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                int destWidth = srcWidth; // 每张切片的宽度
                int destHeight = srcHeight; // 每张切片的高度
                // 计算切片的宽度和高度
                if (srcWidth % cols == 0) {
                    destWidth = srcWidth / cols;
                } else {
                    destWidth = (int) Math.floor(srcWidth / cols) + 1;
                }
                if (srcHeight % rows == 0) {
                    destHeight = srcHeight / rows;
                } else {
                    destHeight = (int) Math.floor(srcWidth / rows) + 1;
                }
                // 循环建立切片
                // 改进的想法:是否可用多线程加快切割速度
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        // 四个参数分别为图像起点坐标和宽高
                        // 即: CropImageFilter(int x,int y,int width,int height)
                        cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
                        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                        BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                        Graphics g = tag.getGraphics();
                        g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                        g.dispose();
                        // 输出为文件
                        ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i + "_c" + j + ".jpg"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description TODO 图像切割（指定切片的宽度和高度）
     * @param srcImageFile 源图像地址
     * @param descDir       切片目标文件夹
     * @param destWidth    目标切片宽度。默认200
     * @param destHeight   目标切片高度。默认150
     * @return void
     * @Author rdl
     * @Date 2020/1/16 16:14
     **/
    public final static void cut3(String srcImageFile, String descDir, int destWidth, int destHeight) {
        try {
            if (destWidth <= 0)
                destWidth = 200; // 切片宽度
            if (destHeight <= 0)
                destHeight = 150; // 切片高度
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight(); // 源图宽度
            int srcHeight = bi.getWidth(); // 源图高度
            if (srcWidth > destWidth && srcHeight > destHeight) {
                Image img;
                ImageFilter cropFilter;
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                int cols = 0; // 切片横向数量
                int rows = 0; // 切片纵向数量
                // 计算切片的横向和纵向数量
                if (srcWidth % destWidth == 0) {
                    cols = srcWidth / destWidth;
                } else {
                    cols = (int) Math.floor(srcWidth / destWidth) + 1;
                }
                if (srcHeight % destHeight == 0) {
                    rows = srcHeight / destHeight;
                } else {
                    rows = (int) Math.floor(srcHeight / destHeight) + 1;
                }
                // 循环建立切片
                // 改进的想法:是否可用多线程加快切割速度
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        // 四个参数分别为图像起点坐标和宽高
                        // 即: CropImageFilter(int x,int y,int width,int height)
                        cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
                        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                        BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                        Graphics g = tag.getGraphics();
                        g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                        g.dispose();
                        // 输出为文件
                        ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i + "_c" + j + ".jpg"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description TODO 图像类型转换：GIF->JPG、GIF->PNG、PNG->JPG、PNG->GIF(X)、BMP->PNG
     * @param srcImageFile  源图像地址
     * @param formatName    包含格式非正式名称的 String：如JPG、JPEG、GIF等
     * @param destImageFile 目标图像地址
     * @return void
     * @Author rdl
     * @Date 2020/1/16 16:15
     **/
    public final static void convert(String srcImageFile, String formatName, String destImageFile) {
        try {
            File f = new File(srcImageFile);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, formatName, new File(destImageFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description TODO 彩色转为黑白
     * @param srcImageFile  源图像地址
     * @param destImageFile 目标图像地址
     * @return void
     * @Author rdl
     * @Date 2020/1/16 16:16
     **/
    public final static void gray(String srcImageFile, String destImageFile) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, "JPEG", new File(destImageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给图片添加文字水印
     *  @param pressText     水印文字
     * @param srcImageFile  源图像地址
     * @param destImageFile 目标图像地址
     * @param fontName      水印的字体名称
     * @param fontStyle     水印的字体样式
     * @param color         水印的字体颜色
     * @param fontSize      水印的字体大小
     * @param x             修正值
     * @param y             修正值
     * @param alpha         透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
     */
    public final static void pressText(String pressText, String srcImageFile, String destImageFile, String fontName, int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null); // GGGGGG
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 在指定坐标绘制水印文字 (800 -5*20)/2 -730
            /*
             * g.drawString(pressText, (width - (getLength(pressText) *
             * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
             */
            g.drawString(pressText, x, (height - fontSize) / 2 + y);
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// 输出到文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给图片添加文字水印
     *
     * @param pressText     水印文字
     * @param srcImageFile  源图像地址
     * @param destImageFile 目标图像地址
     * @param fontName      字体名称
     * @param fontStyle     字体样式
     * @param color         字体颜色
     * @param fontSize      字体大小
     * @param x             修正值
     * @param y             修正值
     * @param alpha         透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
     */
    public final static void pressText2(String pressText, String srcImageFile, String destImageFile, String fontName, int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 在指定坐标绘制水印文字
            g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description TODO  给图片添加矩形框
     * @param srcImageFile	源图像地址
     * @param destImageFile 目标图像地址
     * @return void
     * @Author rdl
     * @Date 2020/1/16 17:31
     **/
    public final static void pressText3(String srcImageFile, String destImageFile) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null); // GGGGGG
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(new Color(255, 255, 255, 138));
            g.fillRect(width * 2 / 3, height - 140, width / 3, 140);// 开始宽高，填充宽高
            g.dispose();
            ImageIO.write(image, "JPEG", new File(destImageFile));// 输出到文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给图片添加文字水印自动换行
     *
     * @param pressText     水印文字
     * @param srcImageFile  源图像地址
     * @param destImageFile 目标图像地址
     * @param fontName      水印的字体名称
     * @param fontStyle     水印的字体样式
     * @param color         水印的字体颜色
     * @param fontSize      水印的字体大小
     * @param x             修正值
     * @param y             修正值
     * @param alpha         透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
     */
    public static int pressText4(String pressText, String srcImageFile, String destImageFile, String fontName, int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
        int newHeight = 1;
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null); // GGGGGG
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // g.drawString(pressText,x, (height - fontSize) / 2 + y);
            newHeight = ImageWatermark.iteratorwrite(pressText, (height - fontSize) / 2 + y, x, fontSize, width * 2 / 3, g);
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// 输出到文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newHeight;
    }

    /**
     * 迭代一句文本
     *
     * @param iter        迭代内容
     * @param y           y轴位置
     * @param fontSize    y轴增量
     * @param srcImgWidth 图片宽度
     *                    // * @param state
     *                    // *            状态（情况）
     * @param x           x轴起始位置
     * @param srcImgWidth 阴影图片宽度
     * @param g           画笔
     * @return 返回当前高度
     */
    public static int iteratorwrite(String iter, int y, int x, int fontSize, int srcImgWidth, Graphics2D g) {

        // 文字叠加,自动换行叠加
        int tempX = x;
        int tempY = y;
        int tempCharLen = 0;// 单字符长度
        int tempLineLen = 20;// 单行字符总长度临时计算
        StringBuffer sb = new StringBuffer();
        int d = 1;
        for (int i = 0; i < iter.length(); i++) {
            char tempChar = iter.charAt(i);
            tempCharLen = getCharLen(tempChar, g);

            tempLineLen += tempCharLen;
            tempX = x;
            if (tempLineLen >= srcImgWidth) {//
                // 长度已经满一行,进行文字叠加
                if (d > 1) {
                    tempX = tempX + 90;
                }
                d++;

                g.drawString(sb.toString(), tempX, tempY);
                sb.delete(0, sb.length());// 清空内容,重新追加
                tempY += fontSize;
                tempLineLen = 20;
            }
            sb.append(tempChar);// 追加字符
        }
        if (d == 1) {
            g.drawString(sb.toString(), tempX, tempY);// 最后叠加余下的文字
        } else {
            g.drawString(sb.toString(), tempX + 90, tempY);// 最后叠加余下的文字
        }
        tempY += fontSize;
        return d + 1;
    }

    /**
     * 根据文字判断其长度
     *
     * @param c 文字
     * @param g 画笔
     * @return
     */
    public static int getCharLen(char c, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charWidth(c);
    }

    /**
     * 给图片添加图片水印
     *
     * @param pressImg      水印图片
     * @param srcImageFile  源图像地址
     * @param destImageFile 目标图像地址
     * @param x             修正值。 默认在中间
     * @param y             修正值。 默认在中间
     * @param alpha         透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
     */
    public final static void pressImage(String pressImg, String srcImageFile, String destImageFile, int x, int y, float alpha) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            // 水印文件
            Image src_biao = ImageIO.read(new File(pressImg));
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao, null);
            // 水印文件结束
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算text的长度（一个中文算两个字符）
     *
     * @param text
     * @return
     */
    public final static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }

    /**
     * @param path 图片路径
     * @return
     * @Descriptionmap 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @Date 2015-01-26
     */
    public static String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * @param base64 图片Base64数据
     * @param path   图片路径
     * @return
     * @Descriptionmap 对字节数组字符串进行Base64解码并生成图片
     * @Date 2015-01-26
     */
    public static boolean base64ToImage(String base64, String path) {// 对字节数组字符串进行Base64解码并生成图片
        if (base64 == null) { // 图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(base64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
