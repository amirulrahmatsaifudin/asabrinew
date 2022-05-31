package com.example.asabri.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.asabri.R;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        fragmentManager = getSupportFragmentManager();

        final PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getDataforOnboarding());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, paperOnboardingFragment);
        fragmentTransaction.commit();
    }

    private ArrayList<PaperOnboardingPage> getDataforOnboarding() {

        // the first string is to show the main title ,
        // second is to show the message below the
        // title, then color of background is passed ,
        // then the image to show on the screen is passed
        // and at last icon to navigate from one screen to other
        PaperOnboardingPage source = new PaperOnboardingPage("Biometric Verify", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Odio vitae faucibus tempus scelerisque blandit id pretium\n" +
                " faucibus nec.s",  R.drawable.background,R.drawable.boarding1, R.drawable.indicatoronboarding);
        PaperOnboardingPage source1 = new PaperOnboardingPage("Fastest Approval", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Odio vitae faucibus tempus scelerisque blandit id pretium\n" +
                " faucibus nec.",  R.drawable.background,R.drawable.boarding2, R.drawable.indicatoronboarding);
        PaperOnboardingPage source2 = new PaperOnboardingPage("Online Request", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Odio vitae faucibus tempus scelerisque blandit id pretium\n" +
                " faucibus nec. ", R.drawable.background ,R.drawable.boarding3, R.drawable.indicatoronboarding);

        // array list is used to store
        // data of onbaording screen
        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();

        // all the sources(data to show on screens)
        // are added to array list
        elements.add(source);
        elements.add(source1);
        elements.add(source2);
        return elements;
    }
}

