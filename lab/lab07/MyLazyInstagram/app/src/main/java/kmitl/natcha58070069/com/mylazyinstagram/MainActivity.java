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
import kmitl.natcha58070069.com.mylazyinstagram.api.PostModel;
import kmitl.natcha58070069.com.mylazyinstagram.api.MylazyInstagramApi;
import kmitl.natcha58070069.com.mylazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //check user view (1 = Grid view, 2 = List view)
    private int mark = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //android , nature , cartoon
        getUserProfile("nature");

    }

    private void getUserProfile(final String name) {
        //Builder Pattern
        OkHttpClient client = new OkHttpClient.Builder().build();

        //ScalarsConverterFactory = get only string value(code is same func as Gson but Scalar)
//                .addConverterFactory(ScalarsConverterFactory.create())


        //GsonConverterFactory = change info from JSON to Obj
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(MylazyInstagramApi.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Factory: New obj by retrofit from Interface LazyInstagramApi
        final MylazyInstagramApi myLazyInstagramApi = retrofit.create(MylazyInstagramApi.class);

        //List View Button
        ImageView icon_list = findViewById(R.id.icon_list);
        icon_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark = 2;
                Toast.makeText(getBaseContext(), "LIST", Toast.LENGTH_LONG).show();

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
        });

        //Grid View Button
        ImageView icon_grid = findViewById(R.id.icon_grid);
        icon_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark = 1;
                Toast.makeText(getBaseContext(), "GRID", Toast.LENGTH_LONG).show();

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
        });

        //Connect UserProfile (Call) & default is Grid view
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

        ImageView icon_more = findViewById(R.id.icon_more);
        icon_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setItems(new CharSequence[]{"android", "nature", "cartoon"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                getUserProfile("android");
                                dialogInterface.dismiss();
                                break;
                            case 1:
                                getUserProfile("nature");
                                dialogInterface.dismiss();
                                break;
                            case 2:
                                getUserProfile("cartoon");
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                });
                builder.show();
            }
        });

    }



    @Override
    public void onClick(View view) {

    }

    private void display(UserProfile userProfile) {

        //USER PROFILE ZONE
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
        textBio.setText("" + userProfile.getBio());

        ImageView imageUser = findViewById(R.id.imageProfile);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageUser);

        //POST IMAGE ZONE
        RecyclerView list = findViewById(R.id.list);
        if (mark == 2) {
            list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        } else {
            list.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        }
        PostAdapter adapter = new PostAdapter(MainActivity.this);
        adapter.setData(userProfile.getPosts());
        list.setAdapter(adapter);
    }

}
