package kmitl.natcha58070069.com.mylazyinstagram;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import kmitl.natcha58070069.com.mylazyinstagram.adapter.PostAdapter;
import kmitl.natcha58070069.com.mylazyinstagram.api.MylazyInstagramApi;
import kmitl.natcha58070069.com.mylazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int mark = 1; //mark = for check user view (1 = Grid view, 2 = List view)
    private String user = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile(user);

        //LIST VIEW BUTTON
        ImageView listView = findViewById(R.id.icon_list);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark = 2;
//                Toast.makeText(getBaseContext(), "LIST", Toast.LENGTH_LONG).show();
                getUserProfile(user);
            }
        });

        //GRID VIEW BUTTON
        ImageView gridView = findViewById(R.id.icon_grid);
        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark = 1;
//                Toast.makeText(getBaseContext(), "GRID", Toast.LENGTH_LONG).show();
                getUserProfile(user);
            }
        });

        //MORE FUNCTION BUTTON = change account
        ImageView more = findViewById(R.id.icon_more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setItems(new CharSequence[]{"android", "nature", "cartoon"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                getUserProfile("android") ;
                                user = "android";
                                dialogInterface.dismiss();
                                break;
                            case 1:
                                getUserProfile("nature");
                                user = "nature";
                                dialogInterface.dismiss();
                                break;
                            case 2:
                                getUserProfile("cartoon");
                                user = "cartoon";
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
    }

    private void getUserProfile(final String name) {
        //Builder Pattern
        OkHttpClient client = new OkHttpClient.Builder().build();

        //GsonConverterFactory = change info from JSON to Obj
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(MylazyInstagramApi.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Factory: New obj by retrofit from Interface LazyInstagramApi
        final MylazyInstagramApi myLazyInstagramApi = retrofit.create(MylazyInstagramApi.class);

        //Connect UserProfile (Call)
        Call<UserProfile> call = myLazyInstagramApi.getProfile(name);
        call.enqueue(new retrofit2.Callback<UserProfile>() {
            @Override
            public void onResponse(retrofit2.Call<UserProfile> call, retrofit2.Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    display(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    private void display(UserProfile userProfile) {

        //USER PROFILE AREA
        //getObj by .get is GSON != Scarlar
        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@" + userProfile.getUser());

        TextView textPost = findViewById(R.id.textPost);
        textPost.setText("Post\n" + userProfile.getPost());

        TextView textFollowing = findViewById(R.id.textFollowing);
        textFollowing.setText("Following\n" + userProfile.getFollowing());

        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText("Follower\n" + userProfile.getFollower());

        TextView textBio = findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio().toString());

        //How to call image from GSON to ImageView
        ImageView imageUser = findViewById(R.id.imageProfile);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageUser);

        //POST IMAGE AREA
        RecyclerView list = findViewById(R.id.list);
        if (mark == 2) {
            list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        } else {
            list.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        }

        //POST ADAPTER (SET DATA)
        PostAdapter adapter = new PostAdapter(MainActivity.this);
        adapter.setData(userProfile.getPosts(), mark);
        list.setAdapter(adapter);
    }

}
