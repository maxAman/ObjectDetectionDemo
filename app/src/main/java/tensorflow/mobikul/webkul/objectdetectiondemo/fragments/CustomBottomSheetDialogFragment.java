package tensorflow.mobikul.webkul.objectdetectiondemo.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import tensorflow.mobikul.webkul.objectdetectiondemo.R;
import tensorflow.mobikul.webkul.objectdetectiondemo.tensorflow.ClassifierActivity;

import static tensorflow.mobikul.webkul.objectdetectiondemo.tensorflow.CameraActivity.readyForNextImage;

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.object_search_box, container, false);
        CustomBottomSheetDialogFragment.this.getDialog().setCanceledOnTouchOutside(false);
        ((TextView) v.getRootView().findViewById(R.id.retry)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomBottomSheetDialogFragment.this.getDialog().dismiss();
                readyForNextImage();
            }
        });
        return v;
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        getActivity().finish();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d = super.onCreateDialog(savedInstanceState);
        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                FrameLayout bottomSheet = (FrameLayout) d.findViewById(android.support.design.R.id.design_bottom_sheet);
                BottomSheetBehavior behaviour = BottomSheetBehavior.from(bottomSheet);
                behaviour.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            handleUserExit();
                            dismiss();
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                    }
                });
            }
        });
        return d;
    }

    private void handleUserExit() {
        readyForNextImage();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String data[] = bundle.getStringArray("list");
        for (int i = 0; i < data.length; i++) {
            Button b = new Button(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(8, 8, 8, 8);
            b.setLayoutParams(layoutParams);
            b.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
            b.setText(data[i]);
            b.setPadding(16, 16, 16, 16);
            b.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
            ((FlexboxLayout) v.getRootView().findViewById(R.id.flexbox)).addView(b);
        }
    }
}