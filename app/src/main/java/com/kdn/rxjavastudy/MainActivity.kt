package com.kdn.rxjavastudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //람다식으로 구현하는 방법

        // 반환값으로 반환된 Observable 의 해당 Observer 가 해당 구독에 대한 알림을 반환해주어 결과를 호출할 수 있다.

//        rangeOperator().subscribe({
//            Log.d(TAG,"onSubscribe : $it")
//        },
//        {
//            Log.d(TAG,"onError $it")
//        },
//        {
//            Log.d(TAG,"onComplete")
//        })

//        repeatOperator().subscribe(
//            {
//                Log.d(TAG,"onSubscribe : $it")
//            },
//            {
//                Log.d(TAG,"onError $it")
//            },
//            {
//                Log.d(TAG,"onComplete")
//            }
//        )

            intervalOperator().subscribe(
                {
                    Log.d(TAG,"onSubscribe : $it")
                    getLocation()
                },
                {
                    Log.d(TAG,"onError $it")
                },
                {
                    Log.d(TAG,"onComplete")
                }
            )

    }

    private fun getLocation(){
        Log.d(TAG,"Latitude : 102.0303  Longitude : 1.2355")
    }

}