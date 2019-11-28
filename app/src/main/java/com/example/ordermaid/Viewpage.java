package com.example.ordermaid;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Viewpage extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentlisttitles =  new ArrayList<>();

    public Viewpage(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlisttitles.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentlisttitles.get(position);
    }

    public  void Addfrag(Fragment fragment,String Title){
        fragmentList.add(fragment);
        fragmentlisttitles.add(Title);
    }
}
