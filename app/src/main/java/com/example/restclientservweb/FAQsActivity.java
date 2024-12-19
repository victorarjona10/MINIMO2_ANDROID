package com.example.restclientservweb;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FAQsActivity extends AppCompatActivity {
    private ApiService apiService;
    private RecyclerView recyclerView;
    private FAQAdapter faqAdapter;
    private List<FAQs> faqList;
    private List<FAQs> faqListPrueba;
    private FAQAdapter faqAdapterPrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_faqs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button buttonShow = findViewById(R.id.buttonShow);

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);

                Preguntas_y_respuestas();
            }
        });


    }

    private void Preguntas_y_respuestas() {
        Call<List<FAQs>> call = apiService.getFAQs();
        call.enqueue(new Callback<List<FAQs>>() {
            @Override
            public void onResponse(Call<List<FAQs>> call, Response<List<FAQs>> response) {
                if (response.isSuccessful()) {
                    faqList = response.body();
                    faqAdapter = new FAQAdapter(faqList);
                    recyclerView.setAdapter(faqAdapter);
                    Toast.makeText(FAQsActivity.this, "RESPONSE SUSCCESFUL", Toast.LENGTH_SHORT).show();

                }
                else
                {
//                    faqListPrueba = new ArrayList<>();
//                    faqListPrueba.add(new FAQs("2023-01-01", "Question 1", "Answer 1", "Sender 1"));
//                    faqListPrueba.add(new FAQs("2023-01-02", "Question 2", "Answer 2", "Sender 2"));
//                    faqAdapterPrueba = new FAQAdapter(faqListPrueba);
//                    recyclerView.setAdapter(faqAdapterPrueba);
//                    faqList = response.body();
//                    faqAdapter = new FAQAdapter(faqList);
//                    recyclerView.setAdapter(faqAdapter);
                    Toast.makeText(FAQsActivity.this, "RESPONSE NO SUSCECFUL failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<FAQs>> call, Throwable t) {
                // Handle failure
                Toast.makeText(FAQsActivity.this, "ON FAILURE", Toast.LENGTH_SHORT).show();

            }
        });
    }

}