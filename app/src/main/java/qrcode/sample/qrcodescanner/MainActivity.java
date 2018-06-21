package qrcode.sample.qrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import qrcode.sample.qrcodescanner.zxing.ToolbarCaptureActivity;

public class MainActivity extends AppCompatActivity {

    //View Objects
    private TextView textViewScanResult;

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View objects
        textViewScanResult = (TextView) findViewById(R.id.text_view_scan_result);

        //intializing scan object
        qrScan = new IntentIntegrator(this);
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data

                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                textViewScanResult.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 默认横向启动扫描页画
     * @param view
     */
    public void startQRScanHorizonal(View view) {
        qrScan.initiateScan();
    }

    /**
     * 启动纵向扫描页面
     * @param view
     */
    public void startQRScanVertical(View view) {
        new IntentIntegrator(this).setCaptureActivity(ToolbarCaptureActivity.class).initiateScan();
    }

//    public void scanContinuous(View view) {
//        Intent intent = new Intent(this, ContinuousCaptureActivity.class);
//        startActivity(intent);
//    }

    /**
     * 扫描页面可以横竖屏转换
     * @param view
     */
    public void startQRScanOrientation(View view) {
        qrScan.initiateScan();
    }
}
