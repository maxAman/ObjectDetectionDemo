package tensorflow.mobikul.webkul.objectdetectiondemo.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import tensorflow.mobikul.webkul.objectdetectiondemo.R;
import tensorflow.mobikul.webkul.objectdetectiondemo.fragments.CustomBottomSheetDialogFragment;
import tensorflow.mobikul.webkul.objectdetectiondemo.tensorflow.ClassifierActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openObjectDetection(View v) {
        Intent i = new Intent(MainActivity.this, ClassifierActivity.class);
        startActivity(i);
    }

    public void openBottomSheet(View v) {
        CustomBottomSheetDialogFragment bottomSheetBehavior = new CustomBottomSheetDialogFragment();
        bottomSheetBehavior.show(getSupportFragmentManager(), "My Dialog");
    }
}
