package kmitl.natcha58070069.com.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import kmitl.natcha58070069.com.mylazyinstagram.MainActivity;
import kmitl.natcha58070069.com.mylazyinstagram.R;
import kmitl.natcha58070069.com.mylazyinstagram.api.PostModel;

import static kmitl.natcha58070069.com.mylazyinstagram.R.id.textComment;
import static kmitl.natcha58070069.com.mylazyinstagram.R.id.textLike;

/**
 * Created by Nacha on 08-Oct-17.
 */

//holder item ไว้ โล่งๆ รอรูปมา
class Holder extends RecyclerView.ViewHolder{


    public ImageView image;
    public TextView textLike;
    public TextView textComment;


    public Holder(View itemView){
        super(itemView);
        image = itemView.findViewById(R.id.image);
        textLike = itemView.findViewById(R.id.textLike);
        textComment = itemView.findViewById(R.id.textComment);

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String imageUrl = data.get(position).getUrl();
        Glide.with(context).load(imageUrl).into(holder.image);
        //load image from URL and show

        TextView textLike = holder.textLike;
        textLike.setText("" + data.get(position).getLike());

        TextView textComment = holder.textComment;
        textComment.setText("" + data.get(position).getComment());
    }

    //เรามีข้อมูลเท่าไหร่
    @Override
    public int getItemCount() {
        return data.size();
    }
}