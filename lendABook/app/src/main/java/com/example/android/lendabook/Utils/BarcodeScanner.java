package com.example.android.lendabook.Utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.lendabook.Book;
import com.example.android.lendabook.Profile.BookListActivity;
import com.example.android.lendabook.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

import static com.example.android.lendabook.Home.HomeFragment.acceptedBooks;
import static com.example.android.lendabook.Home.HomeFragment.availableBooks;
import static com.example.android.lendabook.Home.HomeFragment.borrowedBooks;
import static com.example.android.lendabook.Home.HomeFragment.lentBooks;
import static com.example.android.lendabook.Profile.BookListActivity.globalBook;

/**
 * Copied from camerframent used for handing off books to borrower/owner.
 */

public class BarcodeScanner extends AppCompatActivity {
    CameraView cameraView;
    Button btnScan;
    String desiredISBN;
    String status;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        Intent intent = getIntent();
        desiredISBN = intent.getExtras().getString("desiredISBN");
        status = intent.getExtras().getString("status");
        cameraView = (CameraView) findViewById(R.id.cameraview);
        btnScan = (Button)findViewById(R.id.btn_scanreturn);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        Toast.makeText(BarcodeScanner.this , e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void processResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
        //no barcode detected
        if (firebaseVisionBarcodes.size() == 0) {
            Toast.makeText(BarcodeScanner.this , "ISBN not detected", Toast.LENGTH_SHORT).show();
            cameraView.start();
        }
        //goes through scanned barcode
        for(FirebaseVisionBarcode item: firebaseVisionBarcodes)
        {
            DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference().child("Books");
            // if you are lending a book and the barcode is correct
            if (item.getDisplayValue().equals(desiredISBN) & status.equals("lending")){
                globalBook.setStatus("borrowed"); //sets book status
                bookRef.child(desiredISBN).setValue(globalBook); //updates book on firebase
                lentBooks.add(globalBook); // adds to local array
                acceptedBooks.remove(globalBook); // removes from loca larray
                Intent intent = new Intent (BarcodeScanner.this, BookListActivity.class);
                startActivity(intent); //switches to book list activity
                //if you are returning a book and barcode is correct
            } else if (item.getDisplayValue().equals(desiredISBN) & status.equals("returning")){
                globalBook.setStatus("available"); //sets book status
                globalBook.setBorrower("none"); // clears borower
                bookRef.child(desiredISBN).setValue(globalBook); //updates book on firebase
                availableBooks.add(globalBook); //updates local arrays
                borrowedBooks.remove(globalBook);
                Intent intent = new Intent (BarcodeScanner.this, BookListActivity.class);
                startActivity(intent); //switches to book list
            }else
                Toast.makeText(BarcodeScanner.this , "ISBNs don't match.", Toast.LENGTH_SHORT).show();

        }
    }
}
