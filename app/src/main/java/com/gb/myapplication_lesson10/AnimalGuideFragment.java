package com.gb.myapplication_lesson10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class AnimalGuideFragment extends Fragment implements OnItemClickListener {

    AnimalGuideAdapter animalGuideAdapter;

    public static AnimalGuideFragment newInstance() {
        AnimalGuideFragment fragment = new AnimalGuideFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animal_guide, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initRecycle(view);

    }
    void initAdapter(){
        animalGuideAdapter = new AnimalGuideAdapter();
        animalGuideAdapter.setData(getData());
        animalGuideAdapter.setOnItemClickListener(this);
    }
        void initRecycle(View view){
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(animalGuideAdapter);

    }

    String[] getData(){
        String[] data = getResources().getStringArray(R.array.titles);
        return data;
    }

    @Override
    public void onItemClick(int position) {
        String[] data = getData();
        Toast.makeText(requireContext(), "Нажали на " + data[position], Toast.LENGTH_SHORT).show();
    }
}