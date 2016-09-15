package boriszeus.whatcaniplay;



import android.app.AlertDialog;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;


import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class ListOfGamesActivity extends AppCompatActivity {

    private ListView drawerList;
    private ArrayList<GamesInfo> gInfo;
    private void removeGames(ArrayList<GamesInfo> gInfo,int video,int cpu,int ram ) {

        Iterator<GamesInfo> it = gInfo.iterator();
        while (it.hasNext()) {

            GamesInfo info = it.next();
            if (info.getVideocard()>video) {
                it.remove();
            } else if (info.getCpu()>cpu){
                it.remove();
            } else if (info.getRam()>ram){
                it.remove();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_games);


        drawerList =(ListView)findViewById(R.id.sideMenuDrawer);
        ArrayAdapter<CharSequence> drawerAdapter = ArrayAdapter.createFromResource(this,R.array.string_array_menu,android.R.layout.simple_list_item_1);
        drawerList.setAdapter(drawerAdapter);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        Intent myIntent = new Intent(ListOfGamesActivity.this,PcConfigActivity.class);
                        final int result=1;
                        startActivityForResult(myIntent,result);
                        break;
                    case 2:
                        break;

                }
            }
        });

        GamesDataBase gamesdb = new GamesDataBase(this);
        SQLiteDatabase myGamesDB=gamesdb.getReadableDatabase();
        gInfo = new ArrayList<GamesInfo>();

        Cursor cr = myGamesDB.rawQuery("SELECT * FROM games ORDER BY name ASC;",null);
        cr.moveToFirst();
        do {
            String name = cr.getString(cr.getColumnIndex("name"));
            String descr = cr.getString(cr.getColumnIndex("description"));
            String imgLink = cr.getString(cr.getColumnIndex("imagelink"));
            Integer video = cr.getInt(cr.getColumnIndex("videocard"));
            Integer cpu = cr.getInt(cr.getColumnIndex("cpu"));
            Integer ram = cr.getInt(cr.getColumnIndex("ram"));
            Integer year = cr.getInt(cr.getColumnIndex("releaseyear"));
            Integer score = cr.getInt(cr.getColumnIndex("metascore"));
            String steam = cr.getString(cr.getColumnIndex("steamlink"));
            String gog = cr.getString(cr.getColumnIndex("goglink"));


            gInfo.add(new GamesInfo(name,descr,imgLink,video,cpu,ram,year,score,steam,gog));

        } while (cr.moveToNext());
        cr.close();



        //ListAdapter theAdapter = new ArrayAdapter<GamesInfo>(this,android.R.layout.simple_list_item_1,gInfo);

        final ListAdapter theAdapter = new GamesListAdapter(this,gInfo);
        final ListView gamesListView = (ListView)findViewById(R.id.gamesListView);
        gamesListView.setAdapter(theAdapter);

        gamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListOfGamesActivity.this,GameInfoActivity.class);
                GamesInfo currentGame = (GamesInfo) gamesListView.getAdapter().getItem(position);
                intent.putExtra("current_game",currentGame);
                startActivity(intent);
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = data.getExtras();
        int videoRating = extras.getInt("video",0);
        int cpuRating = extras.getInt("cpu",0);
        int ram = extras.getInt("ram",0);
        ArrayList<GamesInfo> gInfo2=new ArrayList<GamesInfo>(gInfo);
        removeGames(gInfo2,videoRating,cpuRating,ram);

        ListAdapter theAdapter = new GamesListAdapter(this, gInfo2);
        ListView gamesListView = (ListView) findViewById(R.id.gamesListView);
        gamesListView.setAdapter(theAdapter);

    }







}


