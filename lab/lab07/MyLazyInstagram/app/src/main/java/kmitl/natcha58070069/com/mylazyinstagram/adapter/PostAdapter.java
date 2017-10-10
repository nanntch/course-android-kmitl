package kmitl.natcha58070069.com.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.natcha58070069.com.mylazyinstagram.R;
import kmitl.natcha58070069.com.mylazyinstagram.api.PostModel;

/**
 * Created by Nacha on 08-Oct-17.
 */

//Holder item is empty wait for receive image
class Holder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView textLike;
    public TextView textComment;

    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        textLike = itemView.findViewById(R.id.textLike);
        textComment = itemView.findViewById(R.id.textComment);
    }
}

//Data for put in Holder
public class PostAdapter extends RecyclerView.Adapter<Holder> {
    //for check user view (1 = Grid view, 2 = List view)
    private int mark = 1;

    private Context context;
    private List<PostModel> data;

    public PostAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<PostModel> data, int mark) {
        this.data = data;
        this.mark = mark;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create image waiting for put in Holder

        if (mark == 2) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, null);
            Holder holder = new Holder(itemView);
            return holder;
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, null);
            Holder holder = new Holder(itemView);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        String imageUrl = data.get(position).getUrl();
        Glide.with(context).load(imageUrl).into(holder.image);
        //load image from URL and show

        TextView textLike = holder.textLike;
        textLike.setText(data.get(position).getLike().toString());

        TextView textComment = holder.textComment;
        textComment.setText(data.get(position).getComment().toString());
    }

    //How much data?
    @Override
    public int getItemCount() {
        return data.size();
    }
}