package natcha58070069.kmitl.com.mylazyinstagram1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import natcha58070069.kmitl.com.mylazyinstagram1.MainActivity;
import natcha58070069.kmitl.com.mylazyinstagram1.R;

/**
 * Created by student on 10/6/2017 AD.
 */

//holder item ไว้ โล่งๆ รอรูปมา
class Holder extends RecyclerView.ViewHolder{

    public ImageView image;

    public Holder(View itemView){
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
    }
}

//มีข้อมูลอะไร
public class PostAdapter extends RecyclerView.Adapter<Holder>{

    String[] data = {
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n1.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n2.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n3.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n4.jpg"
    };

    private Context context;
    public PostAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //สร้างอิมเมจขึ้นมา รอใส่ใน Holder
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.post_item, null, false);

        Holder holder = new Holder(itemView); //smooth & use lettle memory
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(data[position]).into(image);
        //load image from URL and show

    }

    //เรามีข้อมูลเท่าไหร่
    @Override
    public int getItemCount() {
        return data.length;
    }
}
