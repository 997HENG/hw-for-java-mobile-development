package com.ehappy.mlkitface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int RESULT_LOAD_IMAGE = 2;
    private Bitmap selImage = null;
    private TextView textMsg;
    private ImageView imageView;
    private Button detectFace, takePicture;
    private GraphicOverlay graphicOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textMsg = (TextView) findViewById(R.id.textMsg);
        imageView = (ImageView) findViewById(R.id.imageView);
        detectFace = (Button) findViewById(R.id.detectFace);
        takePicture = (Button) findViewById(R.id.takePicture);
        graphicOverlay = (GraphicOverlay) findViewById(R.id.graphicOverlay);
        requestStoragePermission();
    }

    //相機拍照功能
    public void takePicture(View view) {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePhotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
            textMsg.setText("");  //清除訊息
            graphicOverlay.clear();  //清除輪廓圖形
        }
    }

    //圖庫圖片功能
    public void gallery(View view) {
        Intent selPicIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        selPicIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        if(selPicIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(selPicIntent, RESULT_LOAD_IMAGE);
            textMsg.setText("");  //清除訊息
            graphicOverlay.clear();  //清除輪廓圖形
        }
    }

    //執行startActivityForResult後觸發
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 由相機取得相片
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;  //取得螢幕寬
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null && data.getExtras() != null) {  //相片存在
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");  //相片
            float height = (float)width / imageBitmap.getWidth() * imageBitmap.getHeight();  //計算同比例相片高
            selImage = Bitmap.createScaledBitmap(imageBitmap, width, (int)height, false);  //改變相片尺寸
            imageView.setImageBitmap(selImage);  //顯示相片
            detectFace.setEnabled(true);  //使人臉偵測鈕有效
        }
        // 由圖庫取得圖片
        else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();  //選取的圖片資訊
            String[] filePathColumn = { MediaStore.Images.Media.DATA };  //圖片路徑陣列
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);  //圖片路徑
            cursor.close();
            Bitmap imageBitmap  = BitmapFactory.decodeFile(picturePath);  //取得圖片
            float height = (float)width / imageBitmap.getWidth() * imageBitmap.getHeight();  //計算同比例相片高
            selImage = Bitmap.createScaledBitmap(imageBitmap, width, (int)height, false);  //改變相片尺寸
            imageView.setImageBitmap(selImage);  //顯示相片
            detectFace.setEnabled(true);  //使人臉偵測鈕有效
        }
    }

    // 人臉偵測功能
    public void detectFace(View view) {
        if(selImage != null) {  //已顯示相片
            FirebaseVisionImage vimage = FirebaseVisionImage.fromBitmap(selImage);  //轉換圖片格式
            FirebaseVisionFaceDetectorOptions options = new FirebaseVisionFaceDetectorOptions.Builder()  //偵測參數
                    .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)  //精確模式
                    .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)  //所有特徵點
                    .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)  //所有輪廓
                    .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)  //所有分類
                    .build();
            FirebaseVisionFaceDetector detector = FirebaseVision.getInstance().getVisionFaceDetector(options);  //建立偵測物件
            detector.detectInImage(vimage)  //進行偵測
                    .addOnSuccessListener(
                            new OnSuccessListener<List<FirebaseVisionFace>>() {
                                @Override
                                public void onSuccess(List<FirebaseVisionFace> faces) {
                                    showImage(faces);
                                }
                            })
                 .addOnFailureListener(
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            textMsg.setText("偵測人臉發生錯誤！");
                        }
                    });
        }
    }

    //繪製人臉偵測輪廓
    private void showImage(List<FirebaseVisionFace> faces) {
        graphicOverlay.clear();  //清除輪廓圖形
        if(!faces.isEmpty()) {  //偵測到人臉
            FirebaseVisionFace face = faces.get(0);  //只繪製一張人臉輪廓
            FaceContourGraphic faceGraphic = new FaceContourGraphic(graphicOverlay, face);
            graphicOverlay.add(faceGraphic);
            textMsg.setText("微笑機率：" + (face.getSmilingProbability() * 100) + "%");
        } else {
            textMsg.setText("未偵測到人臉！");
        }
    }

    //檢查驗證
    private void requestStoragePermission() {
        if(Build.VERSION.SDK_INT >= 23) {  //Androis 6.0 以上
            //判斷是否已取得驗證
            int hasPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if(hasPermission != PackageManager.PERMISSION_GRANTED) {  //未取得驗證
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return;
            }
        }
    }

    //requestPermissions 觸發的事件
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {  //按 允許 鈕
                //lstMusic.setAdapter(adaSong);
            } else {
                Toast.makeText(this, "未取得權限！", Toast.LENGTH_SHORT).show();
                finish();  //結束應用程式
            }
        } else  {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
