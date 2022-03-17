package com.gb.myapplication_lesson10.ui.editor;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.gb.myapplication_lesson10.R;
import com.gb.myapplication_lesson10.repository.CardData;
import com.gb.myapplication_lesson10.ui.main.MainActivity;

import java.util.Calendar;

public class CardFragment extends Fragment {

    CardData cardData;
    Calendar calendar;
    private DatePicker datePicker;

    public static CardFragment newInstance(CardData cardData) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putParcelable("cardDate", cardData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitView(view);
    }

    private void InitView(@NonNull View view) {
        SetContent(view);
        setListener(view);
        extractDate(view);
        Save(view);
     
    }

    private void SetContent(@NonNull View view) {
        if (getArguments() != null) {
            cardData = getArguments().getParcelable("cardDate");
            ((EditText) view.findViewById(R.id.inputTitle)).setText(cardData.getTitle());
            ((EditText) view.findViewById(R.id.inputDescription)).setText(cardData.getDescription());

            calendar = Calendar.getInstance();
            calendar.setTime(cardData.getDate());
            ((DatePicker) view.findViewById(R.id.inputDate)).init(calendar.get(Calendar.YEAR) - 1,
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    null);

        }
    }

    private void setListener(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((DatePicker) view.findViewById(R.id.inputDate)).setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    calendar.set(Calendar.YEAR, i);
                    calendar.set(Calendar.MONTH, i1);
                    calendar.set(Calendar.DAY_OF_MONTH, i2);
                }
            });
        }
    }
    private void extractDate(@NonNull View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            DatePicker datePicker = ((DatePicker) view.findViewById(R.id.inputDate));
            calendar.set(Calendar.YEAR, datePicker.getYear());
            calendar.set(Calendar.MONTH, datePicker.getMonth());
            calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        }
        cardData.setDate(calendar.getTime());
    }

    private void Save(@NonNull View view) {
        view.findViewById(R.id.btnSave).setOnClickListener(it -> {
            cardData.setTitle(((EditText) view.findViewById(R.id.inputTitle)).getText().toString());
            cardData.setDescription(((EditText) view.findViewById(R.id.inputDescription)).getText().toString());

            ((MainActivity) requireActivity()).getPublisher().sendMassage(cardData);
            ((MainActivity) requireActivity()).getSupportFragmentManager().popBackStack();
        });
    }


}