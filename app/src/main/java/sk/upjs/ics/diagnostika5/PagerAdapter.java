package sk.upjs.ics.diagnostika5;

/*
Zdroje:
http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
*/

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import sk.upjs.ics.diagnostika5.ActivityAFragmenty.GrafFragment;
import sk.upjs.ics.diagnostika5.ActivityAFragmenty.HodnotyFragment;
import sk.upjs.ics.diagnostika5.ActivityAFragmenty.PriemeryMapaFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private Zaznam zaznam;

        public PagerAdapter(FragmentManager fm, int NumOfTabs, Zaznam zaznam) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
            this.zaznam = zaznam;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    HodnotyFragment hodnotyFragment = HodnotyFragment.newInstance(zaznam);
                    return hodnotyFragment;
                case 1:
                    PriemeryMapaFragment priemeryMapaFragment = PriemeryMapaFragment.newInstance(zaznam);
                    return priemeryMapaFragment;
                case 2:
                    GrafFragment grafFragment = GrafFragment.newInstance(zaznam);
                    return grafFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

