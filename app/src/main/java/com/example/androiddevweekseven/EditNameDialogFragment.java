package com.example.androiddevweekseven;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditNameDialogFragment extends DialogFragment {

    private EditText mEditText;
    private TextListener textListener;

    public EditNameDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditNameDialogFragment newInstance(String title) {
        EditNameDialogFragment frag = new EditNameDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_name, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get field from view
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Some title");
        TextView textView = view.findViewById(R.id.lbl_your_name);
        textView.setText(title);
        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // set listener
        textListener = (TextListener) getParentFragment();
    }

    public interface TextListener {
        void useText(String text);
    }

    @Override
    public void onPause() {
        textListener.useText(mEditText.getText().toString());
        super.onPause();
    }
}