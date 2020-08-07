package jp.techacademy.taiyu.taskapp


import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.PendingIntent
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import io.realm.Realm


//ブロードキャストレシーバークラスを継承したタスクアラームクラスを作成
class TaskAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?){

                val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                // SDKバージョンが26以上の場合、チャネルを設定する必要がある
                if (Build.VERSION.SDK_INT >= 26) {
                    val channel = NotificationChannel("default",
                        "Channel name",
                        NotificationManager.IMPORTANCE_DEFAULT)
                    channel.description = "Channel description"
                    notificationManager.createNotificationChannel(channel)
                }

                // 通知の設定を行う
                val builder = NotificationCompat.Builder(context, "default")
                builder.setSmallIcon(R.drawable.small_icon)
                builder.setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.large_icon))
                builder.setWhen(System.currentTimeMillis())
                builder.setDefaults(Notification.DEFAULT_ALL)
                builder.setAutoCancel(true)

                // EXTRA_TASK から Task の id を取得して、 id から Task のインスタンスを取得する
                val taskId = intent!!.getIntExtra(EXTRA_TASK, -1)
        //val taskId = intent.getIntExtra(EXTRA_TASK, -1) によって EXTRA_TASK から Task の id を取り出します。
        // このとき、もし EXTRA_TASK が設定されていないと taskId には第二引数で指定している既定値 -1 が代入されます。
        // 次に mTask = realm.where(Task::class.java).equalTo("id", taskId).findFirst() によって、
        // Task の id が taskId のものが検索され、findFirst() によって最初に見つかったインスタンスが返され、 mTask へ代入されます。
        // このとき、 taskId に -1 が入っていると、検索に引っかからず、 mTask には null が代入されます。
        // これは addTask で指定している、id が必ず 0 以上というアプリの仕様を利用しています。

                val realm = Realm.getDefaultInstance()
                val task = realm.where(Task::class.java).equalTo("id", taskId).findFirst()

                // タスクの情報を設定する
                builder.setTicker(task!!.title)   // 5.0以降は表示されない
                builder.setContentTitle(task.title)
                builder.setContentText(task.content)

                // 通知をタップしたらアプリを起動するようにする
                val startAppIntent = Intent(context, MainActivity::class.java)
                startAppIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
                val pendingIntent = PendingIntent.getActivity(context, 0, startAppIntent, 0)
                builder.setContentIntent(pendingIntent)

                // 通知を表示する
                notificationManager.notify(task!!.id, builder.build())
                realm.close()
            }
        }


