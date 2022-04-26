package com.kdn.rxjavastudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //람다식으로 구현하는 방법

        // 반환값으로 반환된 Observable 의 해당 Observer 가 해당 구독에 대한 알림을 반환해주어 결과를 호출할 수 있다.

        /*rangeOperator().subscribe({
            Log.d(TAG,"onSubscribe : $it")
        },
        {
            Log.d(TAG,"onError $it")
        },
        {
            Log.d(TAG,"onComplete")
        })*/

        /*repeatOperator().subscribe(
            {
                Log.d(TAG,"onSubscribe : $it")
            },
            {
                Log.d(TAG,"onError $it")
            },
            {
                Log.d(TAG,"onComplete")
            }
        )*/

        /*intervalOperator().subscribe(
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
            )*/

        /*timerOperator().subscribe(
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
            )*/

//        createOperator().subscribe(
//                {
//                    Log.d(TAG,"onSubscribe : $it")
//                },
//                {
//                    Log.d(TAG,"onError $it")
//                },
//                {
//                    Log.d(TAG,"onComplete")
//                }
//            )

        // 다음과같이 filter 로 조건을 설정해준다. 즉, 원하는 조건의 데이터만 뽑아낼 수 있는것.

        /*filterOperator().filter{
            it.age <18
        }.subscribe(
                {
                    Log.d(TAG,"onSubscribe : $it")
                },
                {
                    Log.d(TAG,"onError $it")
                },
                {
                    Log.d(TAG,"onComplete")
                }
        )*/

        // last 를 쓰면 onComplete 를 사용하지 못한다.
        // 그이유는 last 의 반환값이 single 이기 때문이고,
        // Single 에는 OnSuccess , OnFailed 밖에 없기 때문.

        // 반환값 - single , 발생하는 이벤트 - OnSuccess , OnFailed

        // 데이터클래스의 마지막 데이터를 반환한다.

        // 비어있는 예외나 다른 예외를 찾고싶다면 lastOrError 를 쓰면 된다.
        /*lastOperator()
            .last( User(1,"최윤성",19))
            .subscribe(
                {
                    Log.d(TAG,"onNext : $it")
                },
                {
                    Log.d(TAG,"onError ${it}")
                }
            )*/

        // data 클래스에 값의 중복이 확인된다면 먼저 들어온값이 나오고, 그후에 나오는 중복값은 모두 무시된다
        // 또한 distinct 안에 아무것도 넣지 않으면 모든 객체가 같은지 중복을 확인한다.

       /* distinctOperator()
//            .distinct{ it.age }
              .distinct()
              .subscribe(
            {
                Log.d(TAG,"onSubscribe : $it")
            },
            {
                Log.d(TAG,"onError $it")
            },
            {
                Log.d(TAG,"onComplete")
            }
        )*/

        // skip : 첫번째부터 안에 인수까지 건너뜀 skipLast : 마지막 인수부터 역순으로 건너뜀
        // 만약 distinct 로 먼저 중복제거를 한다면 중복제거를 한후 인수를 뺀다.

        // 또한 skip 안에 시간을 넣어 딜레이를 줄수도있다.

        /*skipOperator()
            //.skip(2)
            //.skipLast(2)
            .skip(1,TimeUnit.MILLISECONDS)
            .subscribe(
                {
                    Log.d(TAG,"onSubscribe : $it")
                },
                {
                    Log.d(TAG,"onError $it")
                },
                {
                    Log.d(TAG,"onComplete")
                }
            )*/

        // buffer 에 넣은 인수만큼 데이터를 묶어서 제공해준다. 만약 딱 나누어 떨어지지 않을경우 나머지만을 묶어서 제공한다.
        /*bufferOperator()
            .buffer(3)
            .subscribe(
                {
                    Log.d(TAG,"onSubscribe : $it")
                },
                {
                    Log.d(TAG,"onError $it")
                },
                {
                    Log.d(TAG,"onComplete")
                }
            )*/

        // map 데이터로 기존의 데이터를 가공한다.
        /*mapOperator()
            .map {
           UserProfile(it.id,it.name,it.age,"https://test.com/${it.id}")
        }.subscribe(
                {
                    Log.d(TAG,"onSubscribe : $it")
                },
                {
                    Log.d(TAG,"onError $it")
                },
                {
                    Log.d(TAG,"onComplete")
                }
            )*/

        // 기존의 데이터 클래스를 사용하게 되면 오류가 발생한다.
        // ObservableSource 객체

        /*flatMapOperator()
            .flatMap {
                getUserProfile(it.id)
            }.subscribe(
                {
                    Log.d(TAG,"onSubscribe : $it")
                },
                {
                    Log.d(TAG,"onError $it")
                },
                {
                    Log.d(TAG,"onComplete")
                }
            )*/

//        flatMapOperatorTow()
//            .flatMap {
//                Observable.fromIterable(it)
//            }
//            //.map{ getUserProfile(it.id)}
//            .flatMap { getUserProfile(it.id) }
//            .subscribe(
//                {
//                    Log.d(TAG,"onSubscribe : $it")
//                },
//                {
//                    Log.d(TAG,"onError $it")
//                },
//                {
//                    Log.d(TAG,"onComplete")
//                }
//            )

//        groupByOperator()
//            .groupBy { it.age }
//            .flatMapSingle { group ->
//                group.toList()
//            }
////            .filter{ it.key == 19 }
//            .subscribe(
//                {
////                    group ->
////
////                    group.subscribe({
////                        Log.d(TAG, "Key : ${group.key} - value : $it ")
////                        },
////                        {
////                            Log.d(TAG,"onError $it")
////                        })
//
//                    Log.d(TAG,"onNext $it")
//                },
//                {
//                    Log.d(TAG,"onError $it")
//                },
//                {
//                    Log.d(TAG,"onComplete")
//                }
//            )

//        mergeOperator()
//            .subscribe(
//                {
//                    Log.d(TAG,"onNext $it")
//                },
//                {
//                    Log.d(TAG,"onError $it")
//                },
//                {
//                    Log.d(TAG,"onComplete")
//                }
//            )

//        concatOperator()
//            .subscribe(
//                {
//                    Log.d(TAG,"onNext $it")
//                },
//                {
//                    Log.d(TAG,"onError $it")
//                },
//                {
//                    Log.d(TAG,"onComplete")
//                }
//            )

//                startWithOperator()
//            .subscribe(
//                {
//                    Log.d(TAG,"onNext $it")
//                },
//                {
//                    Log.d(TAG,"onError $it")
//                },
//                {
//                    Log.d(TAG,"onComplete")
//                }
//            )

            zipOperator()
                .subscribe(
                {
                    it.forEach{
                        Log.d(TAG,"onNext $it")
                    }
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