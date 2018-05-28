package zakharov.nikolay.com.materialdesignbutton;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.ChangeBounds;
import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabButton;
    TextView mTextView;
    static boolean isOpen = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PathInterpolator p_interpolator = new PathInterpolator(.5f,1.0f);
        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);
        final Scene scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this);

        final Scene scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this);

        mTextView = findViewById(R.id.textView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (isOpen){
                    isOpen = false;
                    // опишем свой аналог AutoTransition
                    TransitionSet set = new TransitionSet();
                    set.addTransition(new Fade());
                    set.addTransition(new ChangeBounds());
                    // выполняться они будут одновременно
                    set.setOrdering(TransitionSet.ORDERING_TOGETHER);
                    // уставим свою длительность анимации
                    set.setDuration(300);
                    // и изменим Interpolator
                    set.setInterpolator(new AccelerateInterpolator());
                    TransitionManager.go(scene2, set);
                }
                isOpen = true;
                mTextView.setElevation(20);

                // опишем свой аналог AutoTransition
                TransitionSet set = new TransitionSet();
                set.addTransition(new Fade());
                set.addTransition(new ChangeBounds());
                // выполняться они будут одновременно
                set.setOrdering(TransitionSet.ORDERING_TOGETHER);
                // уставим свою длительность анимации
                set.setDuration(300);
                // и изменим Interpolator
                set.setInterpolator(new AccelerateInterpolator());
                TransitionManager.go(scene1, set);
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
}
