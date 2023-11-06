package ir.meros.numberpuzzle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import ir.meros.numberpuzzle.databinding.ActivityGamePageBinding;
import ir.tapsell.plus.AdRequestCallback;
import ir.tapsell.plus.AdShowListener;
import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusInitListener;
import ir.tapsell.plus.model.AdNetworkError;
import ir.tapsell.plus.model.AdNetworks;
import ir.tapsell.plus.model.TapsellPlusAdModel;
import ir.tapsell.plus.model.TapsellPlusErrorModel;

import java.util.ArrayList;
import java.util.Random;

public class GameActivty extends AppCompatActivity {

    ActivityGamePageBinding binding;
    private Button emptyBtn;
    private static int level = 1;
    private static boolean showAd = false;
    private int satr, stoon;
    private CountDownTimer timer;
    private int min, sec = 60;
    ArrayList<Integer> pickedNumbers = new ArrayList<>();
    private String AniRewardedResponseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGamePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        satr = level;
        stoon = level;
        binding.tvLevel.setText("" + level);

        tepSellInitial();
        random();
        onClicks();
        if (showAd){
            AniRequest();
            showAd = false;
        }




        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

                sec--;
                if (sec == 0) {

                    if (min != 0){
                        min--;
                        sec = 60;
                    }
                }
                String m = String.valueOf(min);
                if (m.length() == 1) {
                    m = "0" + m;
                }
                String s = String.valueOf(sec);
                if (s.length() == 1) {
                    s = "0" + s;
                }

                binding.tvTime.setText(m + ":" + s);

            }

            @Override
            public void onFinish() {
                if (min == 0 && sec == 0 && !isGameOver()) {
                    binding.gameOver.setVisibility(View.VISIBLE);
                    Snackbar.make(findViewById(android.R.id.content), "زمان شما به پایان رسید", Snackbar.LENGTH_INDEFINITE)
                            .setAction("شروع دوباره", view -> {
                                showAd = true;
                                Intent intent = new Intent(GameActivty.this, GameActivty.class);
                                finish();
                                startActivity(intent);
                                binding.gameOver.setVisibility(View.GONE);
                                level = 1;
                            })
                            .setActionTextColor(getResources().getColor(R.color.activity_color1))
                            .show();

                } else {
                    timer.start();
                }


            }
        };

        timer.start();

    }

    private void btnVisibilities() {
        switch (level) {
            case 1: {
                binding.btn00.setVisibility(View.VISIBLE);
                binding.btn01.setVisibility(View.VISIBLE);
                binding.btn02.setVisibility(View.GONE);
                binding.btn03.setVisibility(View.GONE);
                binding.btn04.setVisibility(View.GONE);
                binding.btn05.setVisibility(View.GONE);
                binding.btn06.setVisibility(View.GONE);

                binding.btn10.setVisibility(View.VISIBLE);
                binding.btn11.setVisibility(View.INVISIBLE);
                binding.btn12.setVisibility(View.GONE);
                binding.btn13.setVisibility(View.GONE);
                binding.btn14.setVisibility(View.GONE);
                binding.btn15.setVisibility(View.GONE);
                binding.btn16.setVisibility(View.GONE);

                binding.s2.setVisibility(View.GONE);
                binding.btn20.setVisibility(View.GONE);
                binding.btn21.setVisibility(View.GONE);
                binding.btn22.setVisibility(View.GONE);
                binding.btn23.setVisibility(View.GONE);
                binding.btn24.setVisibility(View.GONE);
                binding.btn25.setVisibility(View.GONE);
                binding.btn26.setVisibility(View.GONE);

                binding.s3.setVisibility(View.GONE);
                binding.btn30.setVisibility(View.GONE);
                binding.btn31.setVisibility(View.GONE);
                binding.btn32.setVisibility(View.GONE);
                binding.btn33.setVisibility(View.GONE);
                binding.btn34.setVisibility(View.GONE);
                binding.btn35.setVisibility(View.GONE);
                binding.btn36.setVisibility(View.GONE);

                binding.s4.setVisibility(View.GONE);
                binding.btn40.setVisibility(View.GONE);
                binding.btn41.setVisibility(View.GONE);
                binding.btn42.setVisibility(View.GONE);
                binding.btn43.setVisibility(View.GONE);
                binding.btn44.setVisibility(View.GONE);
                binding.btn45.setVisibility(View.GONE);
                binding.btn46.setVisibility(View.GONE);


                binding.s5.setVisibility(View.GONE);
                binding.btn50.setVisibility(View.GONE);
                binding.btn51.setVisibility(View.GONE);
                binding.btn52.setVisibility(View.GONE);
                binding.btn53.setVisibility(View.GONE);
                binding.btn54.setVisibility(View.GONE);
                binding.btn55.setVisibility(View.GONE);
                binding.btn56.setVisibility(View.GONE);

                binding.s6.setVisibility(View.GONE);
                binding.btn60.setVisibility(View.GONE);
                binding.btn61.setVisibility(View.GONE);
                binding.btn62.setVisibility(View.GONE);
                binding.btn63.setVisibility(View.GONE);
                binding.btn64.setVisibility(View.GONE);
                binding.btn65.setVisibility(View.GONE);
                binding.btn66.setVisibility(View.GONE);
            }
            break;
            case 2: {
                binding.btn00.setVisibility(View.VISIBLE);
                binding.btn01.setVisibility(View.VISIBLE);
                binding.btn02.setVisibility(View.VISIBLE);
                binding.btn03.setVisibility(View.GONE);
                binding.btn04.setVisibility(View.GONE);
                binding.btn05.setVisibility(View.GONE);
                binding.btn06.setVisibility(View.GONE);

                binding.btn10.setVisibility(View.VISIBLE);
                binding.btn11.setVisibility(View.VISIBLE);
                binding.btn12.setVisibility(View.VISIBLE);
                binding.btn13.setVisibility(View.GONE);
                binding.btn14.setVisibility(View.GONE);
                binding.btn15.setVisibility(View.GONE);
                binding.btn16.setVisibility(View.GONE);

                binding.btn20.setVisibility(View.VISIBLE);
                binding.btn21.setVisibility(View.VISIBLE);
                binding.btn22.setVisibility(View.INVISIBLE);
                binding.btn23.setVisibility(View.GONE);
                binding.btn24.setVisibility(View.GONE);
                binding.btn25.setVisibility(View.GONE);
                binding.btn26.setVisibility(View.GONE);

                binding.s3.setVisibility(View.GONE);
                binding.btn30.setVisibility(View.GONE);
                binding.btn31.setVisibility(View.GONE);
                binding.btn32.setVisibility(View.GONE);
                binding.btn33.setVisibility(View.GONE);
                binding.btn34.setVisibility(View.GONE);
                binding.btn35.setVisibility(View.GONE);
                binding.btn36.setVisibility(View.GONE);

                binding.s4.setVisibility(View.GONE);
                binding.btn40.setVisibility(View.GONE);
                binding.btn41.setVisibility(View.GONE);
                binding.btn42.setVisibility(View.GONE);
                binding.btn43.setVisibility(View.GONE);
                binding.btn44.setVisibility(View.GONE);
                binding.btn45.setVisibility(View.GONE);
                binding.btn46.setVisibility(View.GONE);


                binding.s5.setVisibility(View.GONE);
                binding.btn50.setVisibility(View.GONE);
                binding.btn51.setVisibility(View.GONE);
                binding.btn52.setVisibility(View.GONE);
                binding.btn53.setVisibility(View.GONE);
                binding.btn54.setVisibility(View.GONE);
                binding.btn55.setVisibility(View.GONE);
                binding.btn56.setVisibility(View.GONE);

                binding.s6.setVisibility(View.GONE);
                binding.btn60.setVisibility(View.GONE);
                binding.btn61.setVisibility(View.GONE);
                binding.btn62.setVisibility(View.GONE);
                binding.btn63.setVisibility(View.GONE);
                binding.btn64.setVisibility(View.GONE);
                binding.btn65.setVisibility(View.GONE);
                binding.btn66.setVisibility(View.GONE);
            }
            break;
            case 3: {
                binding.btn00.setVisibility(View.VISIBLE);
                binding.btn01.setVisibility(View.VISIBLE);
                binding.btn02.setVisibility(View.VISIBLE);
                binding.btn03.setVisibility(View.VISIBLE);
                binding.btn04.setVisibility(View.GONE);
                binding.btn05.setVisibility(View.GONE);
                binding.btn06.setVisibility(View.GONE);

                binding.btn10.setVisibility(View.VISIBLE);
                binding.btn11.setVisibility(View.VISIBLE);
                binding.btn12.setVisibility(View.VISIBLE);
                binding.btn13.setVisibility(View.VISIBLE);
                binding.btn14.setVisibility(View.GONE);
                binding.btn15.setVisibility(View.GONE);
                binding.btn16.setVisibility(View.GONE);

                binding.btn20.setVisibility(View.VISIBLE);
                binding.btn21.setVisibility(View.VISIBLE);
                binding.btn22.setVisibility(View.VISIBLE);
                binding.btn23.setVisibility(View.VISIBLE);
                binding.btn24.setVisibility(View.GONE);
                binding.btn25.setVisibility(View.GONE);
                binding.btn26.setVisibility(View.GONE);

                binding.btn30.setVisibility(View.VISIBLE);
                binding.btn31.setVisibility(View.VISIBLE);
                binding.btn32.setVisibility(View.VISIBLE);
                binding.btn33.setVisibility(View.INVISIBLE);
                binding.btn34.setVisibility(View.GONE);
                binding.btn35.setVisibility(View.GONE);
                binding.btn36.setVisibility(View.GONE);

                binding.s4.setVisibility(View.GONE);
                binding.btn40.setVisibility(View.GONE);
                binding.btn41.setVisibility(View.GONE);
                binding.btn42.setVisibility(View.GONE);
                binding.btn43.setVisibility(View.GONE);
                binding.btn44.setVisibility(View.GONE);
                binding.btn45.setVisibility(View.GONE);
                binding.btn46.setVisibility(View.GONE);


                binding.s5.setVisibility(View.GONE);
                binding.btn50.setVisibility(View.GONE);
                binding.btn51.setVisibility(View.GONE);
                binding.btn52.setVisibility(View.GONE);
                binding.btn53.setVisibility(View.GONE);
                binding.btn54.setVisibility(View.GONE);
                binding.btn55.setVisibility(View.GONE);
                binding.btn56.setVisibility(View.GONE);

                binding.s6.setVisibility(View.GONE);
                binding.btn60.setVisibility(View.GONE);
                binding.btn61.setVisibility(View.GONE);
                binding.btn62.setVisibility(View.GONE);
                binding.btn63.setVisibility(View.GONE);
                binding.btn64.setVisibility(View.GONE);
                binding.btn65.setVisibility(View.GONE);
                binding.btn66.setVisibility(View.GONE);
            }
            break;
            case 4: {
                binding.btn00.setVisibility(View.VISIBLE);
                binding.btn01.setVisibility(View.VISIBLE);
                binding.btn02.setVisibility(View.VISIBLE);
                binding.btn03.setVisibility(View.VISIBLE);
                binding.btn04.setVisibility(View.VISIBLE);
                binding.btn05.setVisibility(View.GONE);
                binding.btn06.setVisibility(View.GONE);

                binding.btn10.setVisibility(View.VISIBLE);
                binding.btn11.setVisibility(View.VISIBLE);
                binding.btn12.setVisibility(View.VISIBLE);
                binding.btn13.setVisibility(View.VISIBLE);
                binding.btn14.setVisibility(View.VISIBLE);
                binding.btn15.setVisibility(View.GONE);
                binding.btn16.setVisibility(View.GONE);

                binding.btn20.setVisibility(View.VISIBLE);
                binding.btn21.setVisibility(View.VISIBLE);
                binding.btn22.setVisibility(View.VISIBLE);
                binding.btn23.setVisibility(View.VISIBLE);
                binding.btn24.setVisibility(View.VISIBLE);
                binding.btn25.setVisibility(View.GONE);
                binding.btn26.setVisibility(View.GONE);

                binding.btn30.setVisibility(View.VISIBLE);
                binding.btn31.setVisibility(View.VISIBLE);
                binding.btn32.setVisibility(View.VISIBLE);
                binding.btn33.setVisibility(View.VISIBLE);
                binding.btn34.setVisibility(View.VISIBLE);
                binding.btn35.setVisibility(View.GONE);
                binding.btn36.setVisibility(View.GONE);

                binding.btn40.setVisibility(View.VISIBLE);
                binding.btn41.setVisibility(View.VISIBLE);
                binding.btn42.setVisibility(View.VISIBLE);
                binding.btn43.setVisibility(View.VISIBLE);
                binding.btn44.setVisibility(View.INVISIBLE);
                binding.btn45.setVisibility(View.GONE);
                binding.btn46.setVisibility(View.GONE);

                binding.s5.setVisibility(View.GONE);
                binding.btn50.setVisibility(View.GONE);
                binding.btn51.setVisibility(View.GONE);
                binding.btn52.setVisibility(View.GONE);
                binding.btn53.setVisibility(View.GONE);
                binding.btn54.setVisibility(View.GONE);
                binding.btn55.setVisibility(View.GONE);
                binding.btn56.setVisibility(View.GONE);

                binding.s6.setVisibility(View.GONE);
                binding.btn60.setVisibility(View.GONE);
                binding.btn61.setVisibility(View.GONE);
                binding.btn62.setVisibility(View.GONE);
                binding.btn63.setVisibility(View.GONE);
                binding.btn64.setVisibility(View.GONE);
                binding.btn65.setVisibility(View.GONE);
                binding.btn66.setVisibility(View.GONE);
            }
            break;
            case 5: {
                binding.btn00.setVisibility(View.VISIBLE);
                binding.btn01.setVisibility(View.VISIBLE);
                binding.btn02.setVisibility(View.VISIBLE);
                binding.btn03.setVisibility(View.VISIBLE);
                binding.btn04.setVisibility(View.VISIBLE);
                binding.btn05.setVisibility(View.VISIBLE);
                binding.btn06.setVisibility(View.GONE);

                binding.btn10.setVisibility(View.VISIBLE);
                binding.btn11.setVisibility(View.VISIBLE);
                binding.btn12.setVisibility(View.VISIBLE);
                binding.btn13.setVisibility(View.VISIBLE);
                binding.btn14.setVisibility(View.VISIBLE);
                binding.btn15.setVisibility(View.VISIBLE);
                binding.btn16.setVisibility(View.GONE);

                binding.btn20.setVisibility(View.VISIBLE);
                binding.btn21.setVisibility(View.VISIBLE);
                binding.btn22.setVisibility(View.VISIBLE);
                binding.btn23.setVisibility(View.VISIBLE);
                binding.btn24.setVisibility(View.VISIBLE);
                binding.btn25.setVisibility(View.VISIBLE);
                binding.btn26.setVisibility(View.GONE);

                binding.btn30.setVisibility(View.VISIBLE);
                binding.btn31.setVisibility(View.VISIBLE);
                binding.btn32.setVisibility(View.VISIBLE);
                binding.btn33.setVisibility(View.VISIBLE);
                binding.btn34.setVisibility(View.VISIBLE);
                binding.btn35.setVisibility(View.VISIBLE);
                binding.btn36.setVisibility(View.GONE);

                binding.btn40.setVisibility(View.VISIBLE);
                binding.btn41.setVisibility(View.VISIBLE);
                binding.btn42.setVisibility(View.VISIBLE);
                binding.btn43.setVisibility(View.VISIBLE);
                binding.btn44.setVisibility(View.VISIBLE);
                binding.btn45.setVisibility(View.VISIBLE);
                binding.btn46.setVisibility(View.GONE);

                binding.btn50.setVisibility(View.VISIBLE);
                binding.btn51.setVisibility(View.VISIBLE);
                binding.btn52.setVisibility(View.VISIBLE);
                binding.btn53.setVisibility(View.VISIBLE);
                binding.btn54.setVisibility(View.VISIBLE);
                binding.btn55.setVisibility(View.INVISIBLE);
                binding.btn56.setVisibility(View.GONE);

                binding.s6.setVisibility(View.GONE);
                binding.btn60.setVisibility(View.GONE);
                binding.btn61.setVisibility(View.GONE);
                binding.btn62.setVisibility(View.GONE);
                binding.btn63.setVisibility(View.GONE);
                binding.btn64.setVisibility(View.GONE);
                binding.btn65.setVisibility(View.GONE);
                binding.btn66.setVisibility(View.GONE);
            }
            break;
            case 6: {
                binding.btn00.setVisibility(View.VISIBLE);
                binding.btn01.setVisibility(View.VISIBLE);
                binding.btn02.setVisibility(View.VISIBLE);
                binding.btn03.setVisibility(View.VISIBLE);
                binding.btn04.setVisibility(View.VISIBLE);
                binding.btn05.setVisibility(View.VISIBLE);
                binding.btn06.setVisibility(View.VISIBLE);

                binding.btn10.setVisibility(View.VISIBLE);
                binding.btn11.setVisibility(View.VISIBLE);
                binding.btn12.setVisibility(View.VISIBLE);
                binding.btn13.setVisibility(View.VISIBLE);
                binding.btn14.setVisibility(View.VISIBLE);
                binding.btn15.setVisibility(View.VISIBLE);
                binding.btn16.setVisibility(View.VISIBLE);

                binding.btn20.setVisibility(View.VISIBLE);
                binding.btn21.setVisibility(View.VISIBLE);
                binding.btn22.setVisibility(View.VISIBLE);
                binding.btn23.setVisibility(View.VISIBLE);
                binding.btn24.setVisibility(View.VISIBLE);
                binding.btn25.setVisibility(View.VISIBLE);
                binding.btn26.setVisibility(View.VISIBLE);

                binding.btn30.setVisibility(View.VISIBLE);
                binding.btn31.setVisibility(View.VISIBLE);
                binding.btn32.setVisibility(View.VISIBLE);
                binding.btn33.setVisibility(View.VISIBLE);
                binding.btn34.setVisibility(View.VISIBLE);
                binding.btn35.setVisibility(View.VISIBLE);
                binding.btn36.setVisibility(View.VISIBLE);

                binding.btn40.setVisibility(View.VISIBLE);
                binding.btn41.setVisibility(View.VISIBLE);
                binding.btn42.setVisibility(View.VISIBLE);
                binding.btn43.setVisibility(View.VISIBLE);
                binding.btn44.setVisibility(View.VISIBLE);
                binding.btn45.setVisibility(View.VISIBLE);
                binding.btn46.setVisibility(View.VISIBLE);

                binding.btn50.setVisibility(View.VISIBLE);
                binding.btn51.setVisibility(View.VISIBLE);
                binding.btn52.setVisibility(View.VISIBLE);
                binding.btn53.setVisibility(View.VISIBLE);
                binding.btn54.setVisibility(View.VISIBLE);
                binding.btn55.setVisibility(View.VISIBLE);
                binding.btn56.setVisibility(View.VISIBLE);

                binding.btn60.setVisibility(View.VISIBLE);
                binding.btn61.setVisibility(View.VISIBLE);
                binding.btn62.setVisibility(View.VISIBLE);
                binding.btn63.setVisibility(View.VISIBLE);
                binding.btn64.setVisibility(View.VISIBLE);
                binding.btn65.setVisibility(View.VISIBLE);
                binding.btn66.setVisibility(View.INVISIBLE);
            }
            break;
        }
    }

    private void onClicks() {


        binding.btn00.setOnClickListener(view -> onButtonsClicked(0, 0, binding.btn00));
        binding.btn01.setOnClickListener(view -> onButtonsClicked(0, 1, binding.btn01));
        binding.btn02.setOnClickListener(view -> onButtonsClicked(0, 2, binding.btn02));
        binding.btn03.setOnClickListener(view -> onButtonsClicked(0, 3, binding.btn03));
        binding.btn04.setOnClickListener(view -> onButtonsClicked(0, 4, binding.btn04));
        binding.btn05.setOnClickListener(view -> onButtonsClicked(0, 5, binding.btn05));
        binding.btn06.setOnClickListener(view -> onButtonsClicked(0, 6, binding.btn06));


        binding.btn10.setOnClickListener(view -> onButtonsClicked(1, 0, binding.btn10));
        binding.btn11.setOnClickListener(view -> onButtonsClicked(1, 1, binding.btn11));
        binding.btn12.setOnClickListener(view -> onButtonsClicked(1, 2, binding.btn12));
        binding.btn13.setOnClickListener(view -> onButtonsClicked(1, 3, binding.btn13));
        binding.btn14.setOnClickListener(view -> onButtonsClicked(1, 4, binding.btn14));
        binding.btn15.setOnClickListener(view -> onButtonsClicked(1, 5, binding.btn15));
        binding.btn16.setOnClickListener(view -> onButtonsClicked(1, 6, binding.btn16));


        binding.btn20.setOnClickListener(view -> onButtonsClicked(2, 0, binding.btn20));
        binding.btn21.setOnClickListener(view -> onButtonsClicked(2, 1, binding.btn21));
        binding.btn22.setOnClickListener(view -> onButtonsClicked(2, 2, binding.btn22));
        binding.btn23.setOnClickListener(view -> onButtonsClicked(2, 3, binding.btn23));
        binding.btn24.setOnClickListener(view -> onButtonsClicked(2, 4, binding.btn24));
        binding.btn25.setOnClickListener(view -> onButtonsClicked(2, 5, binding.btn25));
        binding.btn26.setOnClickListener(view -> onButtonsClicked(2, 6, binding.btn26));


        binding.btn30.setOnClickListener(view -> onButtonsClicked(3, 0, binding.btn30));
        binding.btn31.setOnClickListener(view -> onButtonsClicked(3, 1, binding.btn31));
        binding.btn32.setOnClickListener(view -> onButtonsClicked(3, 2, binding.btn32));
        binding.btn33.setOnClickListener(view -> onButtonsClicked(3, 3, binding.btn33));
        binding.btn34.setOnClickListener(view -> onButtonsClicked(3, 4, binding.btn34));
        binding.btn35.setOnClickListener(view -> onButtonsClicked(3, 5, binding.btn35));
        binding.btn36.setOnClickListener(view -> onButtonsClicked(3, 6, binding.btn36));


        binding.btn40.setOnClickListener(view -> onButtonsClicked(4, 0, binding.btn40));
        binding.btn41.setOnClickListener(view -> onButtonsClicked(4, 1, binding.btn41));
        binding.btn42.setOnClickListener(view -> onButtonsClicked(4, 2, binding.btn42));
        binding.btn43.setOnClickListener(view -> onButtonsClicked(4, 3, binding.btn43));
        binding.btn44.setOnClickListener(view -> onButtonsClicked(4, 4, binding.btn44));
        binding.btn45.setOnClickListener(view -> onButtonsClicked(4, 5, binding.btn45));
        binding.btn46.setOnClickListener(view -> onButtonsClicked(4, 6, binding.btn46));


        binding.btn50.setOnClickListener(view -> onButtonsClicked(5, 0, binding.btn50));
        binding.btn51.setOnClickListener(view -> onButtonsClicked(5, 1, binding.btn51));
        binding.btn52.setOnClickListener(view -> onButtonsClicked(5, 2, binding.btn52));
        binding.btn53.setOnClickListener(view -> onButtonsClicked(5, 3, binding.btn53));
        binding.btn54.setOnClickListener(view -> onButtonsClicked(5, 4, binding.btn54));
        binding.btn55.setOnClickListener(view -> onButtonsClicked(5, 5, binding.btn55));
        binding.btn56.setOnClickListener(view -> onButtonsClicked(5, 6, binding.btn56));


        binding.btn60.setOnClickListener(view -> onButtonsClicked(6, 0, binding.btn60));
        binding.btn61.setOnClickListener(view -> onButtonsClicked(6, 1, binding.btn61));
        binding.btn62.setOnClickListener(view -> onButtonsClicked(6, 2, binding.btn62));
        binding.btn63.setOnClickListener(view -> onButtonsClicked(6, 3, binding.btn63));
        binding.btn64.setOnClickListener(view -> onButtonsClicked(6, 4, binding.btn64));
        binding.btn65.setOnClickListener(view -> onButtonsClicked(6, 5, binding.btn65));
        binding.btn66.setOnClickListener(view -> onButtonsClicked(6, 6, binding.btn66));


    }

    private void onButtonsClicked(int s, int st, Button btn) {
        int size = level;
        if (s <= size && st <= size) {
            if ((((s - 1) == satr || (s + 1) == satr) && st == stoon) || (s == satr && ((st - 1) == stoon || (st + 1) == stoon))) {
                changeView(s, st, btn, btn.getText().toString());
            }
        }


    }


    private boolean isGameOver() {
        switch (level) {
            case 1:
                if (!binding.btn00.getText().toString().equals("1")) return false;
                if (!binding.btn01.getText().toString().equals("2")) return false;
                if (!binding.btn10.getText().toString().equals("3")) return false;
                break;
            case 2:
                if (!binding.btn00.getText().toString().equals("1")) return false;
                if (!binding.btn01.getText().toString().equals("2")) return false;
                if (!binding.btn02.getText().toString().equals("3")) return false;

                if (!binding.btn10.getText().toString().equals("4")) return false;
                if (!binding.btn11.getText().toString().equals("5")) return false;
                if (!binding.btn12.getText().toString().equals("6")) return false;

                if (!binding.btn20.getText().toString().equals("7")) return false;
                if (!binding.btn21.getText().toString().equals("8")) return false;
                break;
            case 3:
                if (!binding.btn00.getText().toString().equals("1")) return false;
                if (!binding.btn01.getText().toString().equals("2")) return false;
                if (!binding.btn02.getText().toString().equals("3")) return false;
                if (!binding.btn03.getText().toString().equals("4")) return false;

                if (!binding.btn10.getText().toString().equals("5")) return false;
                if (!binding.btn11.getText().toString().equals("6")) return false;
                if (!binding.btn12.getText().toString().equals("7")) return false;
                if (!binding.btn13.getText().toString().equals("8")) return false;

                if (!binding.btn20.getText().toString().equals("9")) return false;
                if (!binding.btn21.getText().toString().equals("10")) return false;
                if (!binding.btn22.getText().toString().equals("11")) return false;
                if (!binding.btn23.getText().toString().equals("12")) return false;

                if (!binding.btn30.getText().toString().equals("13")) return false;
                if (!binding.btn31.getText().toString().equals("14")) return false;
                if (!binding.btn32.getText().toString().equals("15")) return false;
                break;
            case 4:
                if (!binding.btn00.getText().toString().equals("1")) return false;
                if (!binding.btn01.getText().toString().equals("2")) return false;
                if (!binding.btn02.getText().toString().equals("3")) return false;
                if (!binding.btn03.getText().toString().equals("4")) return false;
                if (!binding.btn04.getText().toString().equals("5")) return false;

                if (!binding.btn10.getText().toString().equals("6")) return false;
                if (!binding.btn11.getText().toString().equals("7")) return false;
                if (!binding.btn12.getText().toString().equals("8")) return false;
                if (!binding.btn13.getText().toString().equals("9")) return false;
                if (!binding.btn14.getText().toString().equals("10")) return false;

                if (!binding.btn20.getText().toString().equals("11")) return false;
                if (!binding.btn21.getText().toString().equals("12")) return false;
                if (!binding.btn22.getText().toString().equals("13")) return false;
                if (!binding.btn23.getText().toString().equals("14")) return false;
                if (!binding.btn24.getText().toString().equals("15")) return false;

                if (!binding.btn30.getText().toString().equals("16")) return false;
                if (!binding.btn31.getText().toString().equals("17")) return false;
                if (!binding.btn32.getText().toString().equals("18")) return false;
                if (!binding.btn33.getText().toString().equals("19")) return false;
                if (!binding.btn34.getText().toString().equals("20")) return false;

                if (!binding.btn40.getText().toString().equals("21")) return false;
                if (!binding.btn41.getText().toString().equals("22")) return false;
                if (!binding.btn42.getText().toString().equals("23")) return false;
                if (!binding.btn43.getText().toString().equals("24")) return false;
                break;
            case 5:
                if (!binding.btn00.getText().toString().equals("1")) return false;
                if (!binding.btn01.getText().toString().equals("2")) return false;
                if (!binding.btn02.getText().toString().equals("3")) return false;
                if (!binding.btn03.getText().toString().equals("4")) return false;
                if (!binding.btn04.getText().toString().equals("5")) return false;
                if (!binding.btn05.getText().toString().equals("6")) return false;

                if (!binding.btn10.getText().toString().equals("7")) return false;
                if (!binding.btn11.getText().toString().equals("8")) return false;
                if (!binding.btn12.getText().toString().equals("9")) return false;
                if (!binding.btn13.getText().toString().equals("10")) return false;
                if (!binding.btn14.getText().toString().equals("11")) return false;
                if (!binding.btn15.getText().toString().equals("12")) return false;

                if (!binding.btn20.getText().toString().equals("13")) return false;
                if (!binding.btn21.getText().toString().equals("14")) return false;
                if (!binding.btn22.getText().toString().equals("15")) return false;
                if (!binding.btn23.getText().toString().equals("16")) return false;
                if (!binding.btn24.getText().toString().equals("17")) return false;
                if (!binding.btn25.getText().toString().equals("18")) return false;

                if (!binding.btn30.getText().toString().equals("19")) return false;
                if (!binding.btn31.getText().toString().equals("20")) return false;
                if (!binding.btn32.getText().toString().equals("21")) return false;
                if (!binding.btn33.getText().toString().equals("22")) return false;
                if (!binding.btn34.getText().toString().equals("23")) return false;
                if (!binding.btn35.getText().toString().equals("24")) return false;

                if (!binding.btn40.getText().toString().equals("25")) return false;
                if (!binding.btn41.getText().toString().equals("26")) return false;
                if (!binding.btn42.getText().toString().equals("27")) return false;
                if (!binding.btn43.getText().toString().equals("28")) return false;
                if (!binding.btn44.getText().toString().equals("29")) return false;
                if (!binding.btn45.getText().toString().equals("30")) return false;

                if (!binding.btn50.getText().toString().equals("31")) return false;
                if (!binding.btn51.getText().toString().equals("32")) return false;
                if (!binding.btn52.getText().toString().equals("33")) return false;
                if (!binding.btn53.getText().toString().equals("34")) return false;
                if (!binding.btn54.getText().toString().equals("35")) return false;
                break;
            case 6:
                if (!binding.btn00.getText().toString().equals("1")) return false;
                if (!binding.btn01.getText().toString().equals("2")) return false;
                if (!binding.btn02.getText().toString().equals("3")) return false;
                if (!binding.btn03.getText().toString().equals("4")) return false;
                if (!binding.btn04.getText().toString().equals("5")) return false;
                if (!binding.btn05.getText().toString().equals("6")) return false;
                if (!binding.btn06.getText().toString().equals("7")) return false;

                if (!binding.btn10.getText().toString().equals("8")) return false;
                if (!binding.btn11.getText().toString().equals("9")) return false;
                if (!binding.btn12.getText().toString().equals("10")) return false;
                if (!binding.btn13.getText().toString().equals("11")) return false;
                if (!binding.btn14.getText().toString().equals("12")) return false;
                if (!binding.btn15.getText().toString().equals("13")) return false;
                if (!binding.btn16.getText().toString().equals("14")) return false;

                if (!binding.btn20.getText().toString().equals("15")) return false;
                if (!binding.btn21.getText().toString().equals("16")) return false;
                if (!binding.btn22.getText().toString().equals("17")) return false;
                if (!binding.btn23.getText().toString().equals("18")) return false;
                if (!binding.btn24.getText().toString().equals("19")) return false;
                if (!binding.btn25.getText().toString().equals("20")) return false;
                if (!binding.btn26.getText().toString().equals("21")) return false;

                if (!binding.btn30.getText().toString().equals("22")) return false;
                if (!binding.btn31.getText().toString().equals("23")) return false;
                if (!binding.btn32.getText().toString().equals("24")) return false;
                if (!binding.btn33.getText().toString().equals("25")) return false;
                if (!binding.btn34.getText().toString().equals("26")) return false;
                if (!binding.btn35.getText().toString().equals("27")) return false;
                if (!binding.btn36.getText().toString().equals("28")) return false;

                if (!binding.btn40.getText().toString().equals("29")) return false;
                if (!binding.btn41.getText().toString().equals("30")) return false;
                if (!binding.btn42.getText().toString().equals("31")) return false;
                if (!binding.btn43.getText().toString().equals("32")) return false;
                if (!binding.btn44.getText().toString().equals("33")) return false;
                if (!binding.btn45.getText().toString().equals("34")) return false;
                if (!binding.btn46.getText().toString().equals("35")) return false;

                if (!binding.btn50.getText().toString().equals("36")) return false;
                if (!binding.btn51.getText().toString().equals("37")) return false;
                if (!binding.btn52.getText().toString().equals("38")) return false;
                if (!binding.btn53.getText().toString().equals("39")) return false;
                if (!binding.btn54.getText().toString().equals("40")) return false;
                if (!binding.btn55.getText().toString().equals("41")) return false;
                if (!binding.btn56.getText().toString().equals("42")) return false;

                if (!binding.btn60.getText().toString().equals("43")) return false;
                if (!binding.btn61.getText().toString().equals("44")) return false;
                if (!binding.btn62.getText().toString().equals("45")) return false;
                if (!binding.btn63.getText().toString().equals("46")) return false;
                if (!binding.btn64.getText().toString().equals("47")) return false;
                if (!binding.btn65.getText().toString().equals("48")) return false;
                break;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("شما برند شدید");

        if (level < 6) {
            builder.setPositiveButton("مرحله بعدی", (dialogInterface, i) -> {
                showAd = true;
                level++;
                Intent intent = new Intent(GameActivty.this, GameActivty.class);
                finish();
                startActivity(intent);
            });
        } else {
            builder.setPositiveButton("شروع دوباره", (dialogInterface, i) -> {
                showAd = true;
                level = 0;
                Intent intent = new Intent(GameActivty.this, GameActivty.class);
                finish();
                startActivity(intent);
            });
        }
        builder.show();
        binding.tvLevel.setText("" + level);
        return true;


    }


    private void random() {
        switch (level) {
            case 1:
                binding.btn00.setText("" + whileLoop());
                binding.btn01.setText("" + whileLoop());

                binding.btn10.setText("" + whileLoop());
                break;
            case 2:
                binding.btn00.setText("" + whileLoop());
                binding.btn01.setText("" + whileLoop());
                binding.btn02.setText("" + whileLoop());

                binding.btn10.setText("" + whileLoop());
                binding.btn11.setText("" + whileLoop());
                binding.btn12.setText("" + whileLoop());

                binding.btn20.setText("" + whileLoop());
                binding.btn21.setText("" + whileLoop());

                break;
            case 3:
                binding.btn00.setText("" + whileLoop());
                binding.btn01.setText("" + whileLoop());
                binding.btn02.setText("" + whileLoop());
                binding.btn03.setText("" + whileLoop());

                binding.btn10.setText("" + whileLoop());
                binding.btn11.setText("" + whileLoop());
                binding.btn12.setText("" + whileLoop());
                binding.btn13.setText("" + whileLoop());

                binding.btn20.setText("" + whileLoop());
                binding.btn21.setText("" + whileLoop());
                binding.btn22.setText("" + whileLoop());
                binding.btn23.setText("" + whileLoop());

                binding.btn30.setText("" + whileLoop());
                binding.btn31.setText("" + whileLoop());
                binding.btn32.setText("" + whileLoop());

                break;
            case 4:
                binding.btn00.setText("" + whileLoop());
                binding.btn01.setText("" + whileLoop());
                binding.btn02.setText("" + whileLoop());
                binding.btn03.setText("" + whileLoop());
                binding.btn04.setText("" + whileLoop());

                binding.btn10.setText("" + whileLoop());
                binding.btn11.setText("" + whileLoop());
                binding.btn12.setText("" + whileLoop());
                binding.btn13.setText("" + whileLoop());
                binding.btn14.setText("" + whileLoop());

                binding.btn20.setText("" + whileLoop());
                binding.btn21.setText("" + whileLoop());
                binding.btn22.setText("" + whileLoop());
                binding.btn23.setText("" + whileLoop());
                binding.btn24.setText("" + whileLoop());

                binding.btn30.setText("" + whileLoop());
                binding.btn31.setText("" + whileLoop());
                binding.btn32.setText("" + whileLoop());
                binding.btn33.setText("" + whileLoop());
                binding.btn34.setText("" + whileLoop());

                binding.btn40.setText("" + whileLoop());
                binding.btn41.setText("" + whileLoop());
                binding.btn42.setText("" + whileLoop());
                binding.btn43.setText("" + whileLoop());


                break;
            case 5:
                binding.btn00.setText("" + whileLoop());
                binding.btn01.setText("" + whileLoop());
                binding.btn02.setText("" + whileLoop());
                binding.btn03.setText("" + whileLoop());
                binding.btn04.setText("" + whileLoop());
                binding.btn05.setText("" + whileLoop());

                binding.btn10.setText("" + whileLoop());
                binding.btn11.setText("" + whileLoop());
                binding.btn12.setText("" + whileLoop());
                binding.btn13.setText("" + whileLoop());
                binding.btn14.setText("" + whileLoop());
                binding.btn15.setText("" + whileLoop());

                binding.btn20.setText("" + whileLoop());
                binding.btn21.setText("" + whileLoop());
                binding.btn22.setText("" + whileLoop());
                binding.btn23.setText("" + whileLoop());
                binding.btn24.setText("" + whileLoop());
                binding.btn25.setText("" + whileLoop());

                binding.btn30.setText("" + whileLoop());
                binding.btn31.setText("" + whileLoop());
                binding.btn32.setText("" + whileLoop());
                binding.btn33.setText("" + whileLoop());
                binding.btn34.setText("" + whileLoop());
                binding.btn35.setText("" + whileLoop());

                binding.btn40.setText("" + whileLoop());
                binding.btn41.setText("" + whileLoop());
                binding.btn42.setText("" + whileLoop());
                binding.btn43.setText("" + whileLoop());
                binding.btn44.setText("" + whileLoop());
                binding.btn45.setText("" + whileLoop());

                binding.btn50.setText("" + whileLoop());
                binding.btn51.setText("" + whileLoop());
                binding.btn52.setText("" + whileLoop());
                binding.btn53.setText("" + whileLoop());
                binding.btn54.setText("" + whileLoop());

                break;
            case 6:
                binding.btn00.setText("" + whileLoop());
                binding.btn01.setText("" + whileLoop());
                binding.btn02.setText("" + whileLoop());
                binding.btn03.setText("" + whileLoop());
                binding.btn04.setText("" + whileLoop());
                binding.btn05.setText("" + whileLoop());
                binding.btn06.setText("" + whileLoop());

                binding.btn10.setText("" + whileLoop());
                binding.btn11.setText("" + whileLoop());
                binding.btn12.setText("" + whileLoop());
                binding.btn13.setText("" + whileLoop());
                binding.btn14.setText("" + whileLoop());
                binding.btn15.setText("" + whileLoop());
                binding.btn16.setText("" + whileLoop());

                binding.btn20.setText("" + whileLoop());
                binding.btn21.setText("" + whileLoop());
                binding.btn22.setText("" + whileLoop());
                binding.btn23.setText("" + whileLoop());
                binding.btn24.setText("" + whileLoop());
                binding.btn25.setText("" + whileLoop());
                binding.btn26.setText("" + whileLoop());

                binding.btn30.setText("" + whileLoop());
                binding.btn31.setText("" + whileLoop());
                binding.btn32.setText("" + whileLoop());
                binding.btn33.setText("" + whileLoop());
                binding.btn34.setText("" + whileLoop());
                binding.btn35.setText("" + whileLoop());
                binding.btn36.setText("" + whileLoop());

                binding.btn40.setText("" + whileLoop());
                binding.btn41.setText("" + whileLoop());
                binding.btn42.setText("" + whileLoop());
                binding.btn43.setText("" + whileLoop());
                binding.btn44.setText("" + whileLoop());
                binding.btn45.setText("" + whileLoop());
                binding.btn46.setText("" + whileLoop());

                binding.btn50.setText("" + whileLoop());
                binding.btn51.setText("" + whileLoop());
                binding.btn52.setText("" + whileLoop());
                binding.btn53.setText("" + whileLoop());
                binding.btn54.setText("" + whileLoop());
                binding.btn55.setText("" + whileLoop());
                binding.btn56.setText("" + whileLoop());

                binding.btn60.setText("" + whileLoop());
                binding.btn61.setText("" + whileLoop());
                binding.btn62.setText("" + whileLoop());
                binding.btn63.setText("" + whileLoop());
                binding.btn64.setText("" + whileLoop());
                binding.btn65.setText("" + whileLoop());

                break;
        }
        min = level;
        btnVisibilities();


    }

    private int whileLoop() {
        Random random = new Random();
        int a = (random.nextInt(((level + 1) * (level + 1))) - 1);
        a++;
        if (a == ((level + 1) * (level + 1))) {
            a--;
        }
        if (a == 0) {
            a++;
        }
        while (pickedNumbers.contains(a)) {
            if (a < (((level + 1) * (level + 1)) - 1)) {
                a++;
            } else {
                a = (random.nextInt(((level + 1) * (level + 1))) - 1);
                a++;
                if (a == ((level + 1) * (level + 1))) {
                    a--;
                }
                if (a == 0) {
                    a++;
                }
            }
        }
        pickedNumbers.add(a);
        return a;
    }

    private void changeView(int chaneFromStar, int changeFromStoon, Button btn, String number) {
        switch (satr) {
            case 0:
                switch (stoon) {
                    case 0:
                        emptyBtn = findViewById(R.id.btn_0_0);
                        break;
                    case 1:
                        emptyBtn = findViewById(R.id.btn_0_1);
                        break;
                    case 2:
                        emptyBtn = findViewById(R.id.btn_0_2);
                        break;
                    case 3:
                        emptyBtn = findViewById(R.id.btn_0_3);
                        break;
                    case 4:
                        emptyBtn = findViewById(R.id.btn_0_4);
                        break;
                    case 5:
                        emptyBtn = findViewById(R.id.btn_0_5);
                        break;
                    case 6:
                        emptyBtn = findViewById(R.id.btn_0_6);
                        break;
                }
                break;
            case 1:
                switch (stoon) {
                    case 0:
                        emptyBtn = findViewById(R.id.btn_1_0);
                        break;
                    case 1:
                        emptyBtn = findViewById(R.id.btn_1_1);
                        break;
                    case 2:
                        emptyBtn = findViewById(R.id.btn_1_2);
                        break;
                    case 3:
                        emptyBtn = findViewById(R.id.btn_1_3);
                        break;
                    case 4:
                        emptyBtn = findViewById(R.id.btn_1_4);
                        break;
                    case 5:
                        emptyBtn = findViewById(R.id.btn_1_5);
                        break;
                    case 6:
                        emptyBtn = findViewById(R.id.btn_1_6);
                        break;
                }
                break;
            case 2:
                switch (stoon) {
                    case 0:
                        emptyBtn = findViewById(R.id.btn_2_0);
                        break;
                    case 1:
                        emptyBtn = findViewById(R.id.btn_2_1);
                        break;
                    case 2:
                        emptyBtn = findViewById(R.id.btn_2_2);
                        break;
                    case 3:
                        emptyBtn = findViewById(R.id.btn_2_3);
                        break;
                    case 4:
                        emptyBtn = findViewById(R.id.btn_2_4);
                        break;
                    case 5:
                        emptyBtn = findViewById(R.id.btn_2_5);
                        break;
                    case 6:
                        emptyBtn = findViewById(R.id.btn_2_6);
                        break;
                }
                break;
            case 3:
                switch (stoon) {
                    case 0:
                        emptyBtn = findViewById(R.id.btn_3_0);
                        break;
                    case 1:
                        emptyBtn = findViewById(R.id.btn_3_1);
                        break;
                    case 2:
                        emptyBtn = findViewById(R.id.btn_3_2);
                        break;
                    case 3:
                        emptyBtn = findViewById(R.id.btn_3_3);
                        break;
                    case 4:
                        emptyBtn = findViewById(R.id.btn_3_4);
                        break;
                    case 5:
                        emptyBtn = findViewById(R.id.btn_3_5);
                        break;
                    case 6:
                        emptyBtn = findViewById(R.id.btn_3_6);
                        break;
                }
                break;
            case 4:
                switch (stoon) {
                    case 0:
                        emptyBtn = findViewById(R.id.btn_4_0);
                        break;
                    case 1:
                        emptyBtn = findViewById(R.id.btn_4_1);
                        break;
                    case 2:
                        emptyBtn = findViewById(R.id.btn_4_2);
                        break;
                    case 3:
                        emptyBtn = findViewById(R.id.btn_4_3);
                        break;
                    case 4:
                        emptyBtn = findViewById(R.id.btn_4_4);
                        break;
                    case 5:
                        emptyBtn = findViewById(R.id.btn_4_5);
                        break;
                    case 6:
                        emptyBtn = findViewById(R.id.btn_4_6);
                        break;
                }
                break;
            case 5:
                switch (stoon) {
                    case 0:
                        emptyBtn = findViewById(R.id.btn_5_0);
                        break;
                    case 1:
                        emptyBtn = findViewById(R.id.btn_5_1);
                        break;
                    case 2:
                        emptyBtn = findViewById(R.id.btn_5_2);
                        break;
                    case 3:
                        emptyBtn = findViewById(R.id.btn_5_3);
                        break;
                    case 4:
                        emptyBtn = findViewById(R.id.btn_5_4);
                        break;
                    case 5:
                        emptyBtn = findViewById(R.id.btn_5_5);
                        break;
                    case 6:
                        emptyBtn = findViewById(R.id.btn_5_6);
                        break;
                }
                break;
            case 6:
                switch (stoon) {
                    case 0:
                        emptyBtn = findViewById(R.id.btn_6_0);
                        break;
                    case 1:
                        emptyBtn = findViewById(R.id.btn_6_1);
                        break;
                    case 2:
                        emptyBtn = findViewById(R.id.btn_6_2);
                        break;
                    case 3:
                        emptyBtn = findViewById(R.id.btn_6_3);
                        break;
                    case 4:
                        emptyBtn = findViewById(R.id.btn_6_4);
                        break;
                    case 5:
                        emptyBtn = findViewById(R.id.btn_6_5);
                        break;
                    case 6:
                        emptyBtn = findViewById(R.id.btn_6_6);
                        break;
                }
                break;

        }

        emptyBtn.setVisibility(View.VISIBLE);
        emptyBtn.setText(number);
        btn.setVisibility(View.INVISIBLE);
        btn.setText("");
        satr = chaneFromStar;
        stoon = changeFromStoon;

        isGameOver();
    }


    public void restart(View view) {
        showAd = true;
        Intent intent = new Intent(GameActivty.this, GameActivty.class);
        finish();
        startActivity(intent);
    }




    private void tepSellInitial() {
        TapsellPlus.initialize(this, "dtfojbgotpgicipjkjaprhompqptqcfpbmembdcltercjnshmjsldkngdgsbsktpnmnggt",
                new TapsellPlusInitListener() {
                    @Override
                    public void onInitializeSuccess(AdNetworks adNetworks) {
                        Log.d("onInitializeSuccess", adNetworks.name());
                    }

                    @Override
                    public void onInitializeFailed(AdNetworks adNetworks,
                                                   AdNetworkError adNetworkError) {
                        Log.i("TAG", "onInitializeFailed: " + adNetworkError.getErrorMessage());
                        Log.e("onInitializeFailed", "ad network: " + adNetworks.name() + ", error: " + adNetworkError.getErrorMessage());
                    }
                });
        TapsellPlus.setGDPRConsent(this, true);

    }

    private void AniRequest() {
        TapsellPlus.requestInterstitialAd(
                this,
                "6549263db030e958527d8476",
                new AdRequestCallback() {
                    @Override
                    public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.response(tapsellPlusAdModel);
                        AniRewardedResponseId = tapsellPlusAdModel.getResponseId();
                        AniShow();
                        //Ad is ready to show
                        //Put the ad's responseId to your responseId variable
                    }

                    @Override
                    public void error(String message) {

                        Log.i("TAG", "error: " + message);}

                });
    }



    private void AniShow() {
        TapsellPlus.showInterstitialAd(this, AniRewardedResponseId,
                new AdShowListener() {
                    @Override
                    public void onOpened(TapsellPlusAdModel tapsellPlusAdModel) {

                        super.onOpened(tapsellPlusAdModel);
                    }

                    @Override
                    public void onClosed(TapsellPlusAdModel tapsellPlusAdModel) {
                        sec = 60;
                        super.onClosed(tapsellPlusAdModel);
                    }

                    @Override
                    public void onError(TapsellPlusErrorModel tapsellPlusErrorModel) {


                        super.onError(tapsellPlusErrorModel);
                    }
                });
    }
}