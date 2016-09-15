package boriszeus.whatcaniplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class PcConfigActivity extends AppCompatActivity {
    private Spinner videocardSpinner;
    private Spinner cpuSpinner;
    private EditText ramEditText;
    private ArrayList<HardwareRatingHelper> nVidiaCards = new ArrayList<>();
    private ArrayList<HardwareRatingHelper> atiCards = new ArrayList<>();
    private ArrayList<HardwareRatingHelper> intelCpu = new ArrayList<>();
    private ArrayList<HardwareRatingHelper> amdCpu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_config);



        nVidiaCards.add(new HardwareRatingHelper("GTX 980",36));
        nVidiaCards.add(new HardwareRatingHelper("GTX 560",29));
        nVidiaCards.add(new HardwareRatingHelper("GTS 8800",24));
        nVidiaCards.add(new HardwareRatingHelper("GT 7300",12));
        nVidiaCards.add(new HardwareRatingHelper("FX 5500",5));

        atiCards.add(new HardwareRatingHelper("R9 Fury",35));
        atiCards.add(new HardwareRatingHelper("R9 280X",33));
        atiCards.add(new HardwareRatingHelper("HD 4870",26));
        atiCards.add(new HardwareRatingHelper("X1800 XT",17));
        atiCards.add(new HardwareRatingHelper("X800 Pro",13));

        intelCpu.add(new HardwareRatingHelper("Core i7 3770",13));
        intelCpu.add(new HardwareRatingHelper("Core i5 3220T",10));
        intelCpu.add(new HardwareRatingHelper("Core 2 Extreme QX6700",8));
        intelCpu.add(new HardwareRatingHelper("Core 2 Duo E5500",3));
        intelCpu.add(new HardwareRatingHelper("Celeron E1500",1));

        amdCpu.add(new HardwareRatingHelper("FX-9590",12));
        amdCpu.add(new HardwareRatingHelper("FX-8120",10));
        amdCpu.add(new HardwareRatingHelper("Phenom II X4 905e",7));
        amdCpu.add(new HardwareRatingHelper("Phenom X4 9500",5));
        amdCpu.add(new HardwareRatingHelper("Athlon X2 6550",3));


    }

    public void onNvidiaRadioButtonClick(View view) {
        videocardSpinner = (Spinner)findViewById(R.id.videoCardSpinner);
        ArrayAdapter<HardwareRatingHelper> videoAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,nVidiaCards);
        videocardSpinner.setAdapter(videoAdapter);
    }

    public void onAtiRadioButtonClick(View view) {
        videocardSpinner = (Spinner)findViewById(R.id.videoCardSpinner);
        ArrayAdapter<HardwareRatingHelper> videoAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,atiCards);
        videocardSpinner.setAdapter(videoAdapter);
    }

    public void onIntelRadioButtonClick(View view) {
        cpuSpinner = (Spinner)findViewById(R.id.cpuSpinner);
        ArrayAdapter<HardwareRatingHelper> cpuAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,intelCpu);
        cpuSpinner.setAdapter(cpuAdapter);
    }

    public void onAmdRadioButtonClick(View view) {
        cpuSpinner = (Spinner)findViewById(R.id.cpuSpinner);
        ArrayAdapter<HardwareRatingHelper> cpuAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,amdCpu);
        cpuSpinner.setAdapter(cpuAdapter);
    }

    public void okButtonClick(View view) {
        HardwareRatingHelper videoSetting = (HardwareRatingHelper)videocardSpinner.getSelectedItem();
        HardwareRatingHelper cpuSetting = (HardwareRatingHelper)cpuSpinner.getSelectedItem();
        ramEditText=(EditText)findViewById(R.id.ramEditText);
        int ram = Integer.parseInt(ramEditText.getText().toString());
        int videoNum = videoSetting.getRating();
        int cpuNum = cpuSetting.getRating();
        Intent goindBack = new Intent();
        Bundle extras = new Bundle();
        extras.putInt("video",videoNum);
        extras.putInt("cpu",cpuNum);
        extras.putInt("ram",ram);
        goindBack.putExtras(extras);
        setResult(RESULT_OK,goindBack);
        finish();

    }
}
