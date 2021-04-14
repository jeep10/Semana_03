package com.example.cafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cafe.api.ServiceApi;
import com.example.cafe.entity.User;
import com.example.cafe.util.Connection_Res;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> data = new ArrayList<String>();
    ListView lstUsuario = null;
    ArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstUsuario = findViewById(R.id.idListUsuarios);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);


        lstUsuario.setAdapter(adapter);

        cargaData();
    }

    public void cargaData()
    {
        ServiceApi api = Connection_Res.getConnection().create(ServiceApi.class);
        Call<List<User>> call = api.listaUsuario();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {


                if(response.isSuccessful())
                {
                List<User> users = response.body();
                    for(User x: users){

                        data.add(x.getName());

                    }

                    adapter.notifyDataSetChanged();

                }

            }


            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                t.fillInStackTrace();
                t.fillInStackTrace();
            }
        });

    }

}