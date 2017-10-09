package kmitl.natcha58070069.com.mylazyinstagram;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private UserProfile userProfile;
    private int mark = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile("android");

//        PostAdapter postAdapter = new PostAdapter(this);

//        RecyclerView recyclerView = findViewById(R.id.list);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); //อยากให้ลิสออกมาเป็นแบบไหน
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(postAdapter);

    }

    private void getUserProfile(final String name) {

        //Builder Pattern
        OkHttpClient client = new OkHttpClient.Builder().build();

        //ScalarsConverterFactory มีแต่ค่าออกมา
//                .addConverterFactory(ScalarsConverterFactory.create())


        //GsonConverterFactory เปลี่ยนข้อมูลจาก JSON มาเป็น Obj ให้
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(MylazyInstagramApi.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Factory: New obj by retrofit from Interface LazyInstagramApi
        final MylazyInstagramApi myLazyInstagramApi = retrofit.create(MylazyInstagramApi.class);

        //Connected

//        Call<String> call = lazyInstagramApi.getProfile(name);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if(response.isSuccessful()){
//                    String result = response.body(); //ได้ผลลัพธ์ออกมาเลยป็น Scala
//                    TextView textView = (TextView) findViewById(R.id.textView);
//                    textView.setText(result);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });

        ImageView icon_list = findViewById(R.id.icon_list);
        icon_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark = 2;
                Toast.makeText(
                        getBaseContext(), "LIST",
                        Toast.LENGTH_LONG)
                        .show();

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

        ImageView icon_grid = findViewById(R.id.icon_grid);
        icon_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark = 1;
                Toast.makeText(
                        getBaseContext(), "GRID",
                        Toast.LENGTH_LONG)
                        .show();

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

//        Call<UserProfile> call = myLazyInstagramApi.getProfile(name);
//        call.enqueue(new retrofit2.Callback<UserProfile>() {
//            @Override
//            public void onResponse(retrofit2.Call<UserProfile> call, retrofit2.Response<UserProfile> response) {
//                if (response.isSuccessful()) {
//                 display(response.body());
//                }
//            }
//            @Override
//            public void onFailure(Call<UserProfile> call, Throwable t) {}
//        });
    }

    @Override
    public void onClick(View view) {

    }

    private void display(UserProfile userProfile) {
        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@" + userProfile.getUser());
        //มันเรียกเฉยๆเหมือน Scalar ตอนแรกไม่ได้เพราะเป็น Obj ต้อง .get เพื่อใช้

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

        if (mark == 2) {
            RecyclerView list = findViewById(R.id.list);
            list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            PostAdapter adapter = new PostAdapter(MainActivity.this);
            adapter.setData(userProfile.getPosts());
            list.setAdapter(adapter);
        } else {
            RecyclerView list = findViewById(R.id.list);
            list.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            PostAdapter adapter = new PostAdapter(MainActivity.this);
            adapter.setData(userProfile.getPosts());
            list.setAdapter(adapter);
        }
    }
}
