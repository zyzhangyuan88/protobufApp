package com.example.zhangyuan.protobuftest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import static android.content.ContentValues.TAG;

public class ProtobufTestTask extends AsyncTask<Void, Void, Void> {
    private static final int BUFFER_LEN = 8192;

    private void doEncodeTest(String[] names, int times) {
        long startTime = System.nanoTime();
        AddressBookProtobuf.encodeTest(names, times);
        long protobufTime = System.nanoTime();
        protobufTime = protobufTime - startTime;

        startTime = System.nanoTime();
        AddressBookJson.encodeTest(names, times);
        long jsonTime = System.nanoTime();
        jsonTime = jsonTime - startTime;
        Log.i(TAG, String.format("%-20s%-20s%-20s%-20s", "ProtobufTime", String.valueOf(protobufTime),
                "JsonTime", String.valueOf(jsonTime)));
    }

    private void doEncodeTest10(int times) {
        doEncodeTest(TestUtils.sTestNames10, times);
    }

    private void doEncodeTest50(int times) {
        doEncodeTest(TestUtils.sTestNames50, times);
    }

    private void doEncodeTest100(int times) {
        doEncodeTest(TestUtils.sTestNames100, times);
    }

    private void doEncodeTest(int times) {
        doEncodeTest10(times);
        doEncodeTest50(times);
        doEncodeTest100(times);
    }

    private void compress(InputStream is, OutputStream os)
            throws Exception {

        GZIPOutputStream gos = new GZIPOutputStream(os);

        int count;
        byte data[] = new byte[BUFFER_LEN];
        while ((count = is.read(data, 0, BUFFER_LEN)) != -1) {
            gos.write(data, 0, count);
        }

        gos.finish();
        gos.close();
    }

    private void doDecodeTest(String[] names, int times) {
        byte[] protobufBytes = AddressBookProtobuf.encodeTest(names);
        ByteArrayInputStream bais = new ByteArrayInputStream(protobufBytes);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            compress(bais, baos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, String.format("%-20s%-20s%-20s%-20s", "Protobuf Length", String.valueOf(protobufBytes.length),
                "Protobuf(GZIP) Length", String.valueOf(baos.toByteArray().length)));

        bais = new ByteArrayInputStream(protobufBytes);
        long startTime = System.nanoTime();
        AddressBookProtobuf.decodeTest(bais, times);
        long protobufTime = System.nanoTime();
        protobufTime = protobufTime - startTime;

        String jsonStr = AddressBookJson.encodeTest(names);
        ByteArrayInputStream jsonBais = new ByteArrayInputStream(jsonStr.getBytes());
        ByteArrayOutputStream jsonBaos = new ByteArrayOutputStream();
        try {
            compress(jsonBais, jsonBaos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, String.format("%-20s%-20s%-20s%-20s", "Json Length", String.valueOf(jsonStr.getBytes().length),
                "Json(GZIP) Length", String.valueOf(jsonBaos.toByteArray().length)));

        startTime = System.nanoTime();
        AddressBookJson.decodeTest(jsonStr, times);
        long jsonTime = System.nanoTime();
        jsonTime = jsonTime - startTime;

        Log.i(TAG, String.format("%-20s%-20s%-20s%-20s", "ProtobufTime", String.valueOf(protobufTime),
                "JsonTime", String.valueOf(jsonTime)));
    }

    private void doDecodeTest10(int times) {
        doDecodeTest(TestUtils.sTestNames10, times);
    }

    private void doDecodeTest50(int times) {
        doDecodeTest(TestUtils.sTestNames50, times);
    }

    private void doDecodeTest100(int times) {
        doDecodeTest(TestUtils.sTestNames100, times);
    }

    private void doDecodeTest(int times) {
        doDecodeTest10(times);
        doDecodeTest50(times);
        doDecodeTest100(times);
    }

    @Override
    protected Void doInBackground(Void... params) {
        TestUtils.initTest();
        doEncodeTest(5000);

        doDecodeTest(5000);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}

