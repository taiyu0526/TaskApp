package jp.techacademy.taiyu.taskapp

import io.realm.RealmObject
import java.io.Serializable
import java.util.Date//日付を取得するクラス
import io.realm.annotations.PrimaryKey//プライマリーキーとはデータベースの一つのテーブルの中でデータを唯一的に確かめるための値

open class Task:RealmObject(),Serializable {
   //シリアライズ（Serializable）インターフェースを実装している。これをすることで、生成したオブジェクトをシリアライズできる
    //シリアライズとは、データを丸ごとファイルに保存したり、TaskAppでいうと別のActivityに渡すことができるようになる

    var category: String = ""
    var title: String = ""
    var content : String = ""

    var date : Date = Date()



    @PrimaryKey
    var id: Int = 0

}