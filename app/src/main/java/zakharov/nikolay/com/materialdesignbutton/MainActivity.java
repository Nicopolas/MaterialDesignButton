package zakharov.nikolay.com.materialdesignbutton;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabButton;
    TextView mTextView;
    static boolean isOpen = false;

    ViewGroup sceneRoot;
    Scene scene1;
    Scene scene2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sceneRoot = (ViewGroup) findViewById(R.id.scene_root);
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this);
        initGUI();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initGUI() {
        mTextView = findViewById(R.id.textView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    isOpen = false;
                    beginAutoTransition(scene2, 200);
                }
                else {
                    isOpen = true;
                    beginAutoTransition(scene1, 200);
                }
            }
        });

        fabButton = findViewById(R.id.fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                fabButton.setElevation(20);
            }
        });
    }

    private void beginAutoTransition(Scene scene, int durability) {
        TransitionSet set = new TransitionSet();
        set.addTransition(new Fade());
        set.addTransition(new ChangeBounds());
        // выполняться они будут одновременно
        set.setOrdering(TransitionSet.ORDERING_TOGETHER);
        // уставим свою длительность анимации
        set.setDuration(durability);
        // и изменим Interpolator
        set.setInterpolator(new AccelerateInterpolator());
        TransitionManager.go(scene, set);
        initGUI();
    }
}
