package boriszeus.whatcaniplay;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class GamesListAdapter extends ArrayAdapter<GamesInfo> {

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

    private final ArrayList<GamesInfo> gInfo;
    public GamesListAdapter(Context context, ArrayList<GamesInfo> values) {
        super(context,R.layout.games_list_element, values);
        this.gInfo=values;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.games_list_element,parent,false);



        TextView nameTextView = (TextView)theView.findViewById(R.id.gamename);
        TextView scoreTextView = (TextView)theView.findViewById(R.id.gamescore);
        nameTextView.setText(gInfo.get(position).getName());
        scoreTextView.setText(String.format("%d",gInfo.get(position).getMetascore()));





        new DownloadImageTask(theView).execute(gInfo.get(position).getImageLink());
        return theView;

    }
}
