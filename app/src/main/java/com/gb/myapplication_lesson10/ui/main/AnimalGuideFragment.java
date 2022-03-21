package com.gb.myapplication_lesson10.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gb.myapplication_lesson10.R;
import com.gb.myapplication_lesson10.publisher.Observer;
import com.gb.myapplication_lesson10.repository.CardData;
import com.gb.myapplication_lesson10.repository.CardsSource;
import com.gb.myapplication_lesson10.repository.LocalRepositoryImpl;
import com.gb.myapplication_lesson10.ui.editor.CardFragment;

import java.util.Calendar;

public class AnimalGuideFragment extends Fragment implements OnItemClickListener {
    CardsSource data;
    RecyclerView recyclerView;

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
        setHasOptionsMenu(true);
        initRadioGroup(view);

    }

    static final int SOURCE_ARRAY = 1;
    static final int SOURCE_SP = 2;
    static final int SOURCE_GF = 3;

    static String KEY_SP_S1 = "key_s1";
    static String KEY_SP_CELL_C1 = "key_cell_c1";

    private void initRadioGroup(View view) {
        view.findViewById(R.id.sourceArrays).setOnClickListener(listener);
        view.findViewById(R.id.sourceSp).setOnClickListener(listener);
        view.findViewById(R.id.sourceGF).setOnClickListener(listener);
        switch (getCurrentSource()) {
            case SOURCE_ARRAY:
                ((RadioButton) view.findViewById(R.id.sourceArrays)).setChecked(true);
                break;
            case SOURCE_SP:
                ((RadioButton) view.findViewById(R.id.sourceSp)).setChecked(true);
                break;
            case SOURCE_GF:
                ((RadioButton) view.findViewById(R.id.sourceGF)).setChecked(true);
                break;
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.sourceArrays:
                    setCurrentSource(SOURCE_ARRAY);
                    break;
                case R.id.sourceSp:
                    setCurrentSource(SOURCE_SP);
                    break;
                case R.id.sourceGF:
                    setCurrentSource(SOURCE_GF);
                    break;

            }
        }
    };

    void setCurrentSource(int currentSource) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(KEY_SP_S1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_SP_CELL_C1, currentSource);
        editor.apply();
    }

    int getCurrentSource() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(KEY_SP_S1, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_SP_CELL_C1, SOURCE_ARRAY);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.cards_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
                data.adCardData(new CardData("NewCard " + data.size(), "description NewCard " +
                        data.size(), R.drawable.animals, false, Calendar.getInstance().getTime()));
                animalGuideAdapter.notifyItemInserted(data.size() - 1);
                recyclerView.smoothScrollToPosition(data.size() - 1);

                break;
            }
            case R.id.action_clear: {
                data.clearCards();
                animalGuideAdapter.notifyDataSetChanged();
                break;

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int menuPosition = animalGuideAdapter.getMenuPosition();
        switch (item.getItemId()) {
            case R.id.action_update: {
                /*data.updateCardData(menuPosition,new CardData("UpdatedCard "+data.size(),"description UpdatedCard " +
                        data.size(),data.getCardData(menuPosition).getPicture(),false, Calendar.getInstance().getTime()));
                animalGuideAdapter.notifyItemChanged(menuPosition);*/
                Observer observer = new Observer() {
                    @Override
                    public void receiveMassage(CardData cardData) {
                        ((MainActivity) requireActivity()).getPublisher().unSubScribe(this);
                        data.updateCardData(menuPosition, cardData);
                        animalGuideAdapter.notifyItemChanged(menuPosition);
                    }
                };
                ((MainActivity) requireActivity()).getPublisher().subScribe(observer);
                ((MainActivity) requireActivity()).getNavigation().addFragment(CardFragment.newInstance(data.getCardData(menuPosition)), true);
            }
            case R.id.action_delete: {
                data.deleteCardData(menuPosition);
                animalGuideAdapter.notifyItemRemoved(menuPosition);
                break;

            }
        }
        return super.onContextItemSelected(item);
    }

    void initAdapter() {
        animalGuideAdapter = new AnimalGuideAdapter(this);
        switch (getCurrentSource()) {
            case SOURCE_ARRAY:
                data = new LocalRepositoryImpl(requireContext().getResources()).init();
                break;
            case SOURCE_SP:
                //data = new LocalSharedPreferencesRepositoryImpl(requireContext().getResources()).init();
                break;
            case SOURCE_GF:
                //data = new LocalFireStoreRepositoryImpl(requireContext().getResources()).init();
                break;
        }

        data = new LocalRepositoryImpl(requireContext().getResources()).init();
        animalGuideAdapter.setData(data);
        animalGuideAdapter.setOnItemClickListener(this);
    }

    void initRecycle(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(animalGuideAdapter);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        // animator.setChangeDuration(3000);
        animator.setRemoveDuration(3000);
        recyclerView.setItemAnimator(animator);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    String[] getData() {
        String[] data = getResources().getStringArray(R.array.titles);
        return data;
    }

    @Override
    public void onItemClick(int position) {
        String[] data = getData();
        Toast.makeText(requireContext(), "Нажали на " + data[position], Toast.LENGTH_SHORT).show();
    }
}