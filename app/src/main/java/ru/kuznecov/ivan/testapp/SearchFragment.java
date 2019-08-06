package ru.kuznecov.ivan.testapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class SearchFragment extends Fragment {

    private OnFragmentSearchListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final EditText text = (EditText)view.findViewById(R.id.input);
        Button btnSearch = (Button)view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = text.getText().toString();
                if (listener != null)
                    listener.clickBtnSearch(name);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentSearchListener) {
            listener = (OnFragmentSearchListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentSearchListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    public interface OnFragmentSearchListener {
        void clickBtnSearch(String name);
    }
}
