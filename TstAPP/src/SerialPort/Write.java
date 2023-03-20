package com.example.SerialPort;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Write {
    /**
     * 是向COM3发送数据
     */
    public static void ControlByModBus(byte[] hello) {
        //定义变量
        CommPortIdentifier  com3 = null;
        SerialPort  serialCom3 = null;
        try {
            //获取com3端口
            com3 = CommPortIdentifier.getPortIdentifier("COM5");
            //打开com3端口
            serialCom3 = (SerialPort) com3.open("Com3Writer",1000);
            try {
                serialCom3.setSerialPortParams(4800, //波特率
                        SerialPort.DATABITS_8, //数据位 8
                        SerialPort.STOPBITS_1,//停止位1
                        SerialPort.PARITY_NONE);//奇偶位0
            } catch (UnsupportedCommOperationException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            //写入数据
            //获取串口输出流对象
            OutputStream outputStream = serialCom3.getOutputStream();
            //通过串口的输出流向串口写数据
            //byte hello[] = new byte[] {0x01,0x06,0x00, (byte) 0xB9,0x00,0x01,(byte)0x99,(byte)0xEF };
            outputStream.write(hello);
            InputStream is = serialCom3.getInputStream();
            int receivedData = is.read();

            System.out.println(receivedData);
            /*
             * 使用输出流往串口写数据的时候必须将数据转换为byte数组格式或int格式，
             *  当另一个串口接收到数据之后再根据双方约定的规则，对数据进行解码
             */
            //关闭输出流
            outputStream.flush();
            serialCom3.close();
            //关闭串口
        } catch (NoSuchPortException  e) {
            //找不到串口
            e.printStackTrace();
        }catch (PortInUseException  e) {
            //如果因为端口被占用
            e.printStackTrace();
        }catch (IOException e) {
            //如果获取输出流失败
            e.printStackTrace();
        }
    }
}
