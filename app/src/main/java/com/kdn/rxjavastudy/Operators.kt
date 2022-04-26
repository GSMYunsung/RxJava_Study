package com.kdn.rxjavastudy

import android.util.Log
import com.kdn.rxjavastudy.data.Blog
import com.kdn.rxjavastudy.data.BlogDetail
import com.kdn.rxjavastudy.data.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import java.util.*
import java.util.concurrent.TimeUnit


val mListNum = mutableListOf(1,2,3,4,5,6,7,8,9,10,11,12)

val arrayNum = arrayOf(1,2,3,4,5,6,7,8,9,10,11,12)

val arrayNumTen = arrayOf(10,20,30,40,50,60,70,80,90,100,101,102)

val mUserList = mutableListOf<User>(
    User(1,"최윤성",19),
    User(2,"민경모",20),
    User(3,"김현서",10),
    User(4,"박상선",9),
    User(5,"김구치리",18),
    User(6,"코미구",19),
    User(7,"바무기",18),
    User(8,"꼬부기",20),
)

val mBlogList = mutableListOf<Blog>(
    Blog(1,"title1",1,"content1"),
    Blog(2,"title2",2,"content2"),
    Blog(3,"title3",3,"content1"),
    Blog(4,"title4",4,"content2"),
    Blog(5,"title5",5,"content3"),
    Blog(6,"title6",6,"content1"),
    Blog(1,"title7",7,"content1"),

)

val mUserProfileList = mutableListOf<UserProfile>(
    UserProfile(1,"최윤성",19,"https://test.com/1"),
    UserProfile(2,"민경모",20,"https://test.com/2"),
    UserProfile(3,"김현서",10,"https://test.com/3"),
    UserProfile(4,"박상선",9,"https://test.com/4"),
    UserProfile(5,"김구치리",18,"https://test.com/5"),
    UserProfile(6,"코미구",14,"https://test.com/6"),
    UserProfile(7,"바무기",18,"https://test.com/7"),
    UserProfile(8,"꼬부기",20,"https://test.com/8"),
)

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

fun timerOperator() : Observable<Long>{
    return Observable.timer(5,TimeUnit.SECONDS)
}

// 각 구현체에 맞는 Emitter 를 활용하여 데이터를 방출한다.
// 구독자가 stream을 구독 할 때마다 데이터를 방출 (즉, 값이 변화 할때마다의 변화)

fun createOperator() : Observable<Int>{
    return Observable.create(ObservableOnSubscribe<Int> {
        try {
            for(i in mListNum){
                it.onNext(i * 5)
            }

            it.onComplete()
        }
        catch (e : Exception){
            // null 이 아닌 error 에 대한 throw 를 보낸다.
            it.onError(e)
        }
    })
}

fun filterOperator() : Observable<User>{
    return Observable.fromIterable(mUserList)
}

fun lastOperator() : Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun distinctOperator() : Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun skipOperator() : Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun bufferOperator() : Observable<User>{
    return Observable.fromIterable(mUserList)
}

fun mapOperator() : Observable<User>{
    return Observable.fromIterable(mUserList)
}

fun flatMapOperator() : Observable<User>{
    return Observable.fromIterable(mUserList)
}

// 리스트 형태로 주었을때 형태의 변환
fun flatMapOperatorTow() : Observable<List<User>>{
    return Observable.just(mUserList)
}

// 리스트 재정렬 및 반복되는 리스트속 맞는 id 대칭시키기
fun getUserProfile(id : Long) : Observable<UserProfile> {
    return Observable.fromIterable(mUserProfileList)
        .filter{
            it.id == id
        }
}

fun groupByOperator() : Observable<User>{
    return Observable.fromIterable(mUserList)
}

fun getUser() : Observable<User>{
    return Observable.fromIterable(mUserList)
}

fun getProfile() : Observable<UserProfile>{
    return Observable.fromIterable(mUserProfileList)
}

fun mergeOperator() : Observable<Any>{
    return Observable.merge(getUser(), getProfile())
}

fun getNum1To100() : Observable<Int>{
    return Observable.range(1,100)
}

fun getNum101To50() : Observable<Int>{
    return Observable.range(101,50)
}

fun concatOperator() : Observable<Int>{
    return getNum1To100().concatWith(getNum101To50())
}

fun startWithOperator() : Observable<User> {
    return getUser().startWith(Single.just(User(0,"0",0)))

    //getNum101To50().startWith(getNum1To100())
}

fun getBlogs() : Observable<List<Blog>>{
    return Observable.just(mBlogList)
}

fun getUsers() : Observable<List<User>>{
    return Observable.just(mUserList)
}

fun zipOperator() : Observable<List<BlogDetail>>{
//    val num = Observable.just(1,2,3,4,5)
//    val char = Observable.just("A","B","C","D")
//
//    return Observable.zip(num,char, BiFunction{ t1, t2 ->
//        "$t1$t2"
//    })

   return Observable.zip(getUsers(), getBlogs(), BiFunction{t1, t2 ->
        blogDetail(t1,t2)
    })
}

fun blogDetail(t1 : List<User> , t2 : List<Blog>) : List<BlogDetail> {
    val listBlogDetail: MutableList<BlogDetail> = emptyList<BlogDetail>().toMutableList()
    t1.forEach { user ->
        t2.forEach { blog ->
            if (blog.userId == user.id) {
                listBlogDetail.add(
                    BlogDetail(
                        blog.id, blog.userId, blog.title, blog.content, user
                    )
                )
            }
        }
    }
    return listBlogDetail
}