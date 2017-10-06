package natcha58070069.kmitl.com.mylazyinstagram1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import natcha58070069.kmitl.com.mylazyinstagram1.Api.LazyInstagramApi;
import natcha58070069.kmitl.com.mylazyinstagram1.Api.UserProfile;
import natcha58070069.kmitl.com.mylazyinstagram1.adapter.PostAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile("nature");

        PostAdapter postAdapter = new PostAdapter(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); //อยากให้ลิสออกมาเป็นแบบไหน
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }

    private void getUserProfile(String name){

        //Builder Pattern

        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();

        //ScalarsConverterFactory มีแต่ค่าออกมา
//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .baseUrl(LazyInstagramApi.BASE_URL)
//                .client(client)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();

        //GsonConverterFactory เปลี่ยนข้อมูลจาก JSON มาเป็น Obj ให้
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(LazyInstagramApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Factory: New obj by retrofit from Interface LazyInstagramApi
        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class);

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


        Call<UserProfile> call = lazyInstagramApi.getProfile(name);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    UserProfile userProfile = response.body();
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText("@"+userProfile.getUser());
                    //มันเรียกเฉยๆเหมือน Scalar ตอนแรกไม่ได้เพราะเป็น Obj ต้อง .get เพื่อใช้

                    ImageView imageUser = (ImageView) findViewById(R.id.imageView);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageUser);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }
}
