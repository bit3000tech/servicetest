package com.bit3000.servicetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bit3000.servicetest.services.ItemAgente;
import com.bit3000.servicetest.services.callbacks.IServiceAsyncManager;
import com.bit3000.servicetest.services.dto.ItemDto;
import com.bit3000.servicetest.services.dto.ServiceResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IServiceAsyncManager<ServiceResponse<List<ItemDto>>>{

    @BindView(R.id.lsv_items) ListView mLsvItems;
    private ItemAgente mItemAgente;

    public MainActivity()
    {
        mItemAgente = new ItemAgente();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mItemAgente.obtenerAsync(this);

    }

    @Override
    public void onResponse(final ServiceResponse<List<ItemDto>> response) {

        //String[] values = new String[]{"1","2"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, values);
        ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_2, android.R.id.text1, response.getObj())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(response.getObj().get(position).getNombre());
                text2.setText(response.getObj().get(position).getDetalle());
                return view;
            }
        };
        mLsvItems.setAdapter(adapter);

    }



    @Override
    public void onFailure(String result) {

    }
}
