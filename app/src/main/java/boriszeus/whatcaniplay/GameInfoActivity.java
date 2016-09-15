package boriszeus.whatcaniplay;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class GameInfoActivity extends AppCompatActivity {
    private TextView metascoreView;
    private TextView descriptionView;
    private ImageView steamImage;
    private ImageView gogImage;
    private GamesInfo currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);


        currentGame = (GamesInfo)getIntent().getParcelableExtra("current_game");
        new DownloadImageTask(collapsingToolbar).execute(currentGame.getImageLink());
        collapsingToolbar.setTitle(currentGame.getName());
        metascoreView=(TextView)findViewById(R.id.game_score_info);
        metascoreView.setText("MetaScore: "+currentGame.getMetascore().toString());
        descriptionView=(TextView)findViewById(R.id.game_description_info);
        descriptionView.setText(Html.fromHtml(currentGame.getDescription()));



    }

    public void goToSteamClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(currentGame.getSteamLink()));
        startActivity(intent);
    }

    public void goToGogClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(currentGame.getGogLink()));
        startActivity(intent);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Drawable> {
        View theView;

        public DownloadImageTask(View theView) {
            this.theView = theView;
        }

        protected Drawable doInBackground(String... urls) {
            String urldisplay = urls[0];
            Drawable mIcon11 = null;
            try {
                InputStream in = (InputStream) new URL(urldisplay).getContent();;
                mIcon11 = Drawable.createFromStream(in,"bg.png");
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            mIcon11.setAlpha(204);
            return mIcon11;
        }

        protected void onPostExecute(Drawable result) {
            theView.setBackground(result);
        }
    }

}
