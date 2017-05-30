package com.example.da08.memo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.da08.util.FileUtil;


public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    public static final String DOC_KEY_NAME = "document_id";
    FloatingActionButton btnSave;  // 버튼
    EditText edittxt;  // 입력 위젯

    String document_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 호출한 액티비티에서 인텐트에 아무것도 넘기지 않으면 번들이 0이 되기때문에
        // 널 호출시 익셉션이 발생함 때문에 번들의 널 체크를 해줘야 함
        if( bundle != null ) {
           document_id =  bundle.getString(DOC_KEY_NAME);
        }
        // 1. 소스코드와 위젯을 연결
        edittxt = (EditText) findViewById(R.id.edittxt);
        btnSave = (FloatingActionButton) findViewById(R.id.btnSave);

        // 2. 버튼이 클릭되면 메모를 저장
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               writeContent();

            }
        });
        // 3. 화면에서 사용할 데이터가 있으면 체크해서 출력
        loadContent();
    }

    private void writeContent(){
        // 1. 메모의 내용을 가져옴
        String content = edittxt.getText().toString();

        // 2. 파일 이름 생성
        String filename = "MEMO"+System.currentTimeMillis()+".txt";

        // 다큐먼트 아이디가 있으면 파일을 새로 만들지 않고 기존의 이름을 사용해서 수정
        if( !document_id.equals("")){
            filename = document_id;
        }
        // 3. 메모를 파일에 저장
        FileUtil.write(this, filename, content);

        finish();
    }

    private void loadContent(){
        // 도큐먼트 아이디가 있을때 화면에 출력
        if(!"".equals(document_id)) {
            String memo = FileUtil.read(getBaseContext(), document_id);
            edittxt.setText(memo);
        }
    }

}
