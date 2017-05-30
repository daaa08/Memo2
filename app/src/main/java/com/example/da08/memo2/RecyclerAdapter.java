package com.example.da08.memo2;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.da08.memo2.domain.Memo;

import java.util.ArrayList;


/**
 * Created by Da08 on 2017. 5. 30..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{

    ArrayList<Memo> datas;
    public RecyclerAdapter( ArrayList<Memo> datas){

        this.datas = datas;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 인플레이터로 엑스엠엘을 호출해서 뷰 인스턴스 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_res,parent,false);
        return new Holder(view);
    }

    // 셀이 그려질때 호출되는 함수로 데이를 각 홀더에 세팅해줌
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // cell의 위치에 맞는 데이터를 꺼내고
        Memo memo = datas.get(position);
        // 홀더에 그 값을 셋팅한다
        holder.setTitle(memo.getContent());
        holder.setDate(memo.getDate());
        holder.setDocumentid(memo.getId());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    // 리스트에 셀 하나 단위의 뷰를 정리하는 뷰홀더 클래스
    class Holder extends RecyclerView.ViewHolder{
        // 뷰홀더에서 사용할 레이아웃 xml내의 위젯을 선언
        private TextView title;
        private TextView date;
        // 셀이 화면에 그려질때 뷰홀더에 세팅해중 차일이름을 저장할 변수 선언
        private String documentid;
        public Holder(View itemview){
            super(itemview);
            title = (TextView) itemview.findViewById(R.id.textTitle);
            date = (TextView) itemview.findViewById(R.id.textDate);
            // 셀이 온클릭 되었을때 디테일 액티비티를 호출하면서 파일이름을 두튜먼트 아이디에 담아서 넘겨줌
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.DOC_KEY_NAME,documentid);
                    view.getContext().startActivity(intent);
                }
            });
        }
        // 홀더에 속성값을 셋팅하는 setter 함수
        public void setTitle(String value){
            title.setText(value);
        }
        public  void setDate(String value){
            date.setText(value);
        }
        public void  setDocumentid(String value){
            documentid =  value;
        }

    }

}
