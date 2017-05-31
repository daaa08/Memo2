package com.example.da08.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Da08 on 2017. 5. 30..
 */

// 파일 읽기
public class FileUtil {
    private static final String TAG = "FileUtil";

    // 파일 읽기
    public static String read(Context context, String filename){
        String result = "";

        try {
            // 1 스트림을 열고
            FileInputStream fis = context.openFileInput(filename);
            // 2 래퍼가 필요할 경우는 사용 = 문자열 캐릭터셋을 변환해 주는 역할
            // 생략가능
            // 3 버퍼를 씌워서 속도를 향상시킨 후 한줄씩 읽어서 result 결과값에 계속 더해준다
            BufferedInputStream bis = new BufferedInputStream(fis);

            byte buffer[] = new byte[1024];  // 몇글자씩 읽어올지 정해줄 수 있음

            int count = 0;  // 버퍼 단위로 읽어왔는데 글자가 많아서 1024씩 일어왔다가 현재 내가 읽어올 글자가 몇글자인지 확인

            while(( count = bis.read(buffer)) != -1 ){  // 버퍼에 담기고 카운드에 몇글자인지 알려줌

                String data = new String(buffer, 0, count); // 0부터 카운트까지의 바이트만 스트링으로 변환 (100개만 있으면 100개만 변환..)

                result = result + data;
            }

            // 스트림을  닫는다 (다만 역순으로해야 데이터 손실이 없음)
            bis.close();
            fis.close();
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }


        /*

        // 로직을 짧게하기위해서 한글자씩 저장 보통은 버퍼로 사용함
        try {
            // 1 스트림을 열어야 함
            FileInputStream fis = openFileInput(filename);
            // 2 스트림을 통해서 데이터를 읽고 저장
            char oneword;
           while( char oneword = (char) fis.read() ){

            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
        // 3 스트림을 받아준다

        */

        return result;

    }

    // 파일 첫줄만 읽기
    public static final String readFirstLine(Context context, String filename){
        String result = "";
        try {
            // 1 스트림을 연다
            FileInputStream fis = context.openFileInput(filename);
            // 2 버퍼드리더에 담기 위해서 스트림을 리더에서 읽을 수 있는
            InputStreamReader isr = new InputStreamReader(fis);
            // 2 텍스트 파일을 읽기 위한 리더 계열의 버퍼에 담고
            BufferedReader br = new BufferedReader(isr);
            // 3 한줄을 읽은 후 리저트에 저장
            result = br.readLine();
//            String oneLine = br.readLine();
            // 5 스트림을 닫는다
            fis.close();
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
        return  result;
    }

    // 파일에 쓰기
    public static void write(Context context,String filename, String value){
        // 1  스트림을 열어야 함
        try {
            FileOutputStream fos = context.openFileOutput(filename, MODE_PRIVATE);  // 스트림을 열어주고 받아주는 역할
            // 2 스트림을 통해서 데이터를 씀
            fos.write(value.getBytes());    // byte 배열로 변환
            fos.close();
            Log.i(TAG, "=============================== 파일 생성된거");

            // 3 스트림을 닫아줌

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

    }

}
