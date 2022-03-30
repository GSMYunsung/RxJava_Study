package com.kdn.rxjavastudy

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit


val mListNum = mutableListOf(1,2,3,4,5,6,7,8,9,10,11,12)

val arrayNum = arrayOf(1,2,3,4,5,6,7,8,9,10,11,12)

val arrayNumTen = arrayOf(10,20,30,40,50,60,70,80,90,100,101,102)

// 가장 간단한 생성 방식이다.

fun justOperator(){

    // observable 이 해당 시작점에서 로드된 데이터를 처리하고 데이터를 발행한다.
    val observable = Observable.just(mListNum)

    // 관찰자
    val observer = object : Observer<List<Int>> {
        override fun onSubscribe(d: Disposable) {
            Log.d(MainActivity.TAG,"onSubscribe")
        }

        override fun onNext(t: List<Int>) {
            Log.d(MainActivity.TAG,"onSubscribe : $t")
        }

        override fun onError(e: Throwable) {
            Log.d(MainActivity.TAG,"onError ${e.toString()}")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG,"onComplete")
        }

    }

    // observer 가 observable 을 구독한 알림을 가져온다.
    observable.subscribe(observer)
}

// 여러 데이터를 다뤄야 하는경우 사용한다

fun fromOperator(){
    val observable = Observable.fromArray(arrayNum, arrayNumTen)

    val observer = object : Observer<Array<Int>>{

        override fun onSubscribe(d: Disposable) {
            Log.d(MainActivity.TAG,"onSubscribe")
        }

        override fun onNext(t: Array<Int>?) {
            Log.d(MainActivity.TAG,"onNext : ${Arrays.toString(t)}")
        }

        override fun onError(e: Throwable) {
            Log.d(MainActivity.TAG,"onError ${e.toString()}")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG,"onComplete")
        }

    }

    observable.subscribe(observer)
}

// Iterable이 구현되어 있는 Class 를 observable 으로 변환해준다.
// 여기에는 ArrayList, Stack, vector 등이 있다.

fun fromIterableOperator(){
    val observable = Observable.fromIterable(mListNum)

    val observer = object : Observer<Int>{
        override fun onSubscribe(d: Disposable) {
            Log.d(MainActivity.TAG,"onSubscribe")
        }

        override fun onNext(t: Int) {
            Log.d(MainActivity.TAG,"onSubscribe : $t")
        }

        override fun onError(e: Throwable) {
            Log.d(MainActivity.TAG,"onError ${e.toString()}")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG,"onComplete")
        }

    }

    observable.subscribe(observer)
}

fun rangeOperator() : Observable<Int>{

    // Observable 을 반환한다.
    return Observable.range(1,10)
}

// 여러번 반복할 경우 repeat 메서드를 사용할경우 반복코드를 최소화할 수 있다.
fun repeatOperator() : Observable<Int>{

    return Observable.range(1,10).repeat(2)
}

// n 초 지연코드 1초마다 1씩 증가
// 각 매게 변수로는 초기지연, 반복 시간, 반복 단위

fun intervalOperator() : Observable<Long> {
    return Observable.interval(5,1, TimeUnit.SECONDS).takeWhile{values ->
            values <= 10
    }
}