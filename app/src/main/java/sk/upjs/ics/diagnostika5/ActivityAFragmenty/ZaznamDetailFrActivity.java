package sk.upjs.ics.diagnostika5.ActivityAFragmenty;

/*
Zdroje:
http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
*/

import android.app.AlertDialog;
import android.content.AsyncQueryHandler;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import sk.upjs.ics.diagnostika5.PagerAdapter;
import sk.upjs.ics.diagnostika5.R;
import sk.upjs.ics.diagnostika5.Zaznam;
import sk.upjs.ics.diagnostika5.databaza.Provider;

public class ZaznamDetailFrActivity extends AppCompatActivity {

    private Zaznam zaznam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaznam_detail_fr);

        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState != null){
            zaznam = (Zaznam) savedInstanceState.getSerializable("zaznam");
        }else if ((bundle != null) && (zaznam == null)){
            zaznam = (Zaznam) bundle.getSerializable("zaznam");
        }else{
            zaznam = new Zaznam();
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Hodnoty"));
        tabLayout.addTab(tabLayout.newTab().setText("Analýza"));
        tabLayout.addTab(tabLayout.newTab().setText("Graf"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(),zaznam);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("zaznam",zaznam);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_zaznam_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.odstranZaznamMenu){

            new AlertDialog.Builder(this)
                    .setMessage("Chcete odstrániť tento záznam?")
                    .setTitle("Odstránenie záznamu")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            odstranZaznam(zaznam.get_id());
                        }
                    })
                    .setNegativeButton("Close", null)
                    .show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void odstranZaznam(Integer id) {
        AsyncQueryHandler deleteHandler = new AsyncQueryHandler(getContentResolver()) {
            @Override
            protected void onDeleteComplete(int token, Object cookie, int result) {
                Toast.makeText(ZaznamDetailFrActivity.this, "Záznam bol zmazany", Toast.LENGTH_SHORT)
                        .show();
            }
        };
        Uri selectedNoteUri = ContentUris.withAppendedId(Provider.CONTENT_URI, id);
        deleteHandler.startDelete(0, null, selectedNoteUri,
                null, null);

        Intent intent = new Intent(getApplicationContext(),ZaznamListActivity.class);
        startActivity(intent);
    }
}
