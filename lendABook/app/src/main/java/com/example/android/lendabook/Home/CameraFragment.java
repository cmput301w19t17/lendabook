package com.example.android.lendabook.Home;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.lendabook.Add.AddActivity;
import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.fetchData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Gets an image from the camera using CameraKit and scans it for an ISBN using Firebase ML Vision
 * Based on tutorial from https://www.youtube.com/watch?v=NSq0nuc6-AI
 * Made by Kostin
 */

public class CameraFragment extends Fragment {

    private static final String TAG = "CameraFragment";
    CameraView cameraView;
    Button btnScanISBN;
    AlertDialog waitingDialog;

    public static FragmentActivity cameraActivity;


    @Override
    public void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraView.stop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        cameraView = (CameraView)view.findViewById(R.id.cameraview);
        btnScanISBN = (Button)view.findViewById(R.id.btn_scanISBN);
        waitingDialog = new SpotsDialog.Builder()
                // should be (this)
                .setContext(view.getContext())
                .setMessage("Please wait")
                .setCancelable(false)
                .build();

        btnScanISBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waitingDialog.show();
                cameraView.start();
                cameraView.captureImage();
            }
        });

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                //waitingDialog.show();
                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, cameraView.getWidth(), cameraView.getHeight(), false);
                cameraView.stop();

                runDetector(bitmap);
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });

        return view;
    }

    private void runDetector(Bitmap bitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionBarcodeDetectorOptions options = new FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(
                        FirebaseVisionBarcode.FORMAT_EAN_13
                )
                .build();
        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options);

        detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
                        processResult(firebaseVisionBarcodes);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext() , e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void processResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
        Log.d("999:", Integer.toString(firebaseVisionBarcodes.size()));
        // firebaseVisionBarcodes is a list of the scanned codes
        if (firebaseVisionBarcodes.size() == 0) {
            waitingDialog.hide();
            Toast.makeText(getContext() , "ISBN not detected", Toast.LENGTH_SHORT).show();
            cameraView.start();
        }

        for(FirebaseVisionBarcode item: firebaseVisionBarcodes)
        {
            //goes to add activity with ISBN
            cameraActivity =  getActivity();


            fetchData booksApi = new fetchData();
            booksApi.execute(item.getDisplayValue());
            int value_type = item.getValueType();
            Log.d("999:", item.getDisplayValue());

        }

    }
}
