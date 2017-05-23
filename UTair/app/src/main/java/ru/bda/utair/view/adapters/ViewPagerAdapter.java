package ru.bda.utair.view.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.ArrayList;
import java.util.List;

import ru.bda.utair.R;
import ru.bda.utair.model.event.DataTownsEvent;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final DataTownsEvent data;
    private int[] imageResId = {R.drawable.tab_forward, R.drawable.tab_return};
    private String[] tabTitles = {"Туда", "Обратно"};
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, DataTownsEvent data, Context context) {
        super(fm);
        this.context = context;
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString("   " + tabTitles[position]);
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }
}
