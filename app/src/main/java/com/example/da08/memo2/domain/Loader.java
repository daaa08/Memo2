package com.example.da08.memo2.domain;

import android.content.Context;

import com.example.da08.util.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Da08 on 2017. 5. 30..
 */

public class Loader {
    // 파일이 저장되는 스마트폰의 디렉토리 구조는 (internal Storage) 경로 = /data/data/패키지명/files
    public static final String DIR = "/data/data/com.example.da08.memo2/files";
    // 메모를 저장한 디텍토리를 리스트에서 파일 목록과 해당 파일의 내용 첫 줄, 마지막 수정 일자를 담아서 리턴 해 줌.
    static  ArrayList<Memo> datas = new ArrayList<>();

    public static ArrayList<Memo> getData(Context context){

        // 이전의 데이터를 삭제하고

        datas.clear();
        // 아래에서 데이터를 다시 입력해준다


        // 1.1 목록을 가져올 디렉토리 경로를 파일 클래스로 생성하고
        File dir = new File(DIR);
        // 1.2 파일 클래스에 정의된 리스트파일즈 함수를 이용해서 파일목록을 가져온다
        File files[] = dir.listFiles();
        // 1.3 파일이 하나도 없으면 그냥 리턴
        if(files == null)
            return datas;
        // 2.1 반복문을 돌면서 파일의 내용을 Memo 객체에 담은 후 데이터에 add한다
        for(File file : files){
            // 2.2 파일이면 ( 디렉토리 파일일 경우 안 함
            if(file.isFile()) {
                Memo memo = new Memo();
                // 2.2.1 파일명을 가져와서 담는다
                memo.setId(file.getName());
                // 2.2.2 작성일자를 포멧팅해서 문자열로 담는다
                String formatted = convertLontToDate(file.lastModified());
                memo.setDate(formatted);

                // 2.2.3 파일의 첫줄만 담아와서 담는다
                String firstLine = FileUtil.readFirstLine(context, file.getName());

                memo.setContent(firstLine);
                datas.add(memo);
            }
        }

        return datas;
    }

    public static String convertLontToDate(long value){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
        return sdf.format(value);
    }
}
