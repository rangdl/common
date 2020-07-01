package com.example.common.utils.core;

import java.io.*;
import java.util.Objects;

/**
 * @ClassName ObjectUtils
 * @Description TODO
 * @Author rdl
 * @Date 2019/12/5 17:18
 * @Version 1.0
 **/
public class ObjectUtils {
    /**
     * @Author rdl
     * @Description 克隆对象 需要实现序列化接口
     * @Date 2019/12/5 17:29
     * @Param [obj]
     * @return T
     **/
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        ByteArrayInputStream bin = null;
        ObjectInputStream ois = null;
        T t = null;
        try {
            oos = new ObjectOutputStream(bout);
            oos.writeObject(obj);
            bin =new ByteArrayInputStream(bout.toByteArray());
            ois = new ObjectInputStream(bin);
            t = (T) ois.readObject();
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                bout.close();
                if (!Objects.isNull(oos)) {
                    oos.close();
                }
                if (!Objects.isNull(bin)) {
                    bin.close();
                }
                if (!Objects.isNull(ois)) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}
