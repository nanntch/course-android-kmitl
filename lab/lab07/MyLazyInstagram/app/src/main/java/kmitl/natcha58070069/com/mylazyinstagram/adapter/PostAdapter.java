package kmitl.natcha58070069.com.mylazyinstagram.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.natcha58070069.com.mylazyinstagram.R;

/**
 * Created by Nacha on 08-Oct-17.
 */

//holder item ไว้ โล่งๆ รอรูปมา
class Holder extends RecyclerView.ViewHolder{


    public ImageView image;
    public Object imageView;

    public Holder(View itemView){
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }
}

//มีข้อมูลอะไร
public class PostAdapter extends RecyclerView.Adapter<Holder>{

    private Context context;
    private List<PostModel> data;

    public PostAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<PostModel> data){
        this.data = data;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //สร้างอิมเมจขึ้นมา รอใส่ใน Holder
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View itemView = inflater.inflate(R.layout.item, null, false);
//        Holder holder = new Holder(itemView); //smooth & use lettle memory
//        return holder;

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String imageUrl = data.get(position).getUrl();
        Glide.with(context).load(imageUrl).into((ImageView) holder.image);
        //load image from URL and show
    }

    //เรามีข้อมูลเท่าไหร่
    @Override
    public int getItemCount() {
        return data.size();
    }
}