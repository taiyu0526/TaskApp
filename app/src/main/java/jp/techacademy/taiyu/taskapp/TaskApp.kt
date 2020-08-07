package jp.techacademy.taiyu.taskapp

import android.app.Application
import io.realm.Realm

//onCreateメソッドをオーバーライドし、その中で、Realm,init(this)をしてレルムを初期化する
class TaskApp : Application() {

    override fun onCreate(){
        super.onCreate()
        Realm.init(this)

    }
}
