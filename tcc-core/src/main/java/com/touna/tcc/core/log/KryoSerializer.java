package com.touna.tcc.core.log;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.Input;

/**
 * Created by chenchaojian on 17/5/31.
 */
public class KryoSerializer implements Serializer {
    private int maxCapacity;
    //spared max capacity
    private final int maxCapacitySpared = 2048;

    public KryoSerializer() {
    }

    @Override
    public byte[] serialize(Object object) {
        if(maxCapacity == 0) maxCapacity = maxCapacitySpared;

        Output output = new Output(maxCapacity);
        try {
            Kryo kryo = new Kryo();
            kryo.writeObject(output, object);

            return output.toBytes();
        }finally {
            output.clear();
        }
    }

    @Override
    public byte[] serialize(Object object, int capacity) {
        Output output = new Output(capacity);
        try {
            Kryo kryo = new Kryo();
            kryo.writeObject(output, object);

            return output.toBytes();
        }finally {
            output.clear();
        }    }

    @Override
    public Object deserialize(byte[] bytes, Class type) {
        Kryo kryo = new Kryo();
        Input input = new Input(bytes);
        return kryo.readObject(input, type);
    }


    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
