package com.ysk.codeplatform.base.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.NoArgsConstructor;

/**
 * 描述
 *  <dependency>
 *             <groupId>io.protostuff</groupId>
 *             <artifactId>protostuff-runtime</artifactId>
 *             <version>1.4.0</version>
 *         </dependency>
 *
 * @author Y.S.K
 * @date 2018/7/25 11:08
 */
@NoArgsConstructor
public class ProtoBufUtil {
    public static <T> byte[] serializer(T o) {
        Schema schema = RuntimeSchema.getSchema(o.getClass());
        return ProtobufIOUtil.toByteArray(o, schema, LinkedBuffer.allocate(LinkedBuffer.MIN_BUFFER_SIZE));
    }

    public static <T> T deserializer(byte[] bytes, Class<T> clazz) {

        T obj = null;
        try {
            obj = clazz.newInstance();
            Schema schema = RuntimeSchema.getSchema(obj.getClass());
            ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

