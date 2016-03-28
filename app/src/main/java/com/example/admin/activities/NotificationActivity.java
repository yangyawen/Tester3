package com.example.admin.activities;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.admin.tester.R;

/**
 * Created by admin on 2016/2/22.
 */
public class NotificationActivity extends Activity implements View.OnClickListener{

    Button sentRealTimeNote;
    Button sentDelayedNote;
    private RadioGroup mNoteStyles;
    private EditText delayedTime;

    @Override
    public void onCreate(Bundle savedIncstanceState){
        super.onCreate(savedIncstanceState);
        setContentView(R.layout.notification_layout);
        init();
    }

    protected void init() {
        mNoteStyles = (RadioGroup) findViewById(R.id.id_note_styles);

        sentRealTimeNote = (Button) findViewById(R.id.id_realtime_sent);
        sentRealTimeNote.setOnClickListener(this);
        sentDelayedNote = (Button) findViewById(R.id.id_delayed_sent);
        sentDelayedNote.setOnClickListener(this);

        delayedTime = (EditText) findViewById(R.id.id_delayedTime);

    }

    @Override
    public void onClick(View v){

        if(v.getId() == R.id.id_realtime_sent) {
            final int noteStyle = mNoteStyles.getCheckedRadioButtonId();
            final Notification note;
            note = buildRealTimeNote(noteStyle);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(noteStyle, note);
        }
        if(v.getId() == R.id.id_delayed_sent) {

            Toast.makeText(this, "抱歉！该功能暂未实现", Toast.LENGTH_SHORT).show();
            /*
            if(delayedTime.getText().toString().equals("")){
                Toast.makeText(this, "延时时间不能为空！", Toast.LENGTH_SHORT).show();
            }else {
                buildNote(delayedTime.getText().toString());
            }*/
        }

    }

    private Notification buildRealTimeNote(int noteType) {
        Intent launchIntent = new Intent(this, NotificationActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);

        //构造基础通知
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setTicker("您有新的消息！")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("新通知")
                .setContentText("中国解放了！点击查看消息详情")
                .setContentIntent(contentIntent);

        switch (noteType) {
            case R.id.id_basic_style:
                return builder.build();
            case R.id.id_picture_style:
                //包含一个动作
                builder.addAction(android.R.drawable.ic_menu_compass,"View Location", contentIntent);
                //在展开时使用BigPictureStyle
                NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle(builder);
                pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.elephant));

                return pictureStyle.build();
            case R.id.id_bittext_style:
                builder.setContentTitle("大通知")
                        .setContentText("按住并下拉可显示详情");
                //包含两个动作
                builder.addAction(android.R.drawable.ic_menu_call, "报警", contentIntent);
                builder.addAction(android.R.drawable.ic_menu_recent_history, "忽略", contentIntent);
                //在展开时使用BigTextStyle
                NotificationCompat.BigTextStyle textStyle = new NotificationCompat.BigTextStyle(builder);
                textStyle.bigText("2015年度《上海市公共场所控烟状况》白皮书昨天对外发布。"+"上海市健康促进委员会同时透露，《条例》修订工作已成为2016年市人大立法计划正式项目。"+"这也意味着年内上海有望实施室内全面禁烟。");

                return textStyle.build();
            case R.id.id_list_style:
                builder.setContentTitle("列表通知")
                        .setContentText("按住并下拉可显示消息列表");
                //在展开时使用
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle(builder);
                inboxStyle.setSummaryText("您有4个新消息");
                inboxStyle.addLine("Jasmine请求加您为好友");
                inboxStyle.addLine("韩梅梅发来一个活动邀请");
                inboxStyle.addLine("李烈同意了您的活动邀请");
                inboxStyle.addLine("收到李磊的100元打车费");

                return inboxStyle.build();
            default:
                throw new IllegalArgumentException("未知的消息类型");
        }
    }

    private void buildNote(String delayedSec) {

    }
}
