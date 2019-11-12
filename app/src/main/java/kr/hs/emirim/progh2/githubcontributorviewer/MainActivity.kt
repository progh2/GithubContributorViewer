package kr.hs.emirim.progh2.githubcontributorviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    val TAG:String = "레트로핏"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = KotlinOKHttpRetrofitRxJavaManager.getInstance()
// "ldhcjs", "GetPackagesName" 에는 각자의 Github id와 Repository를 넣으셔도 됩니다.
        adapter.requestContributors("ldhcjs", "GetPackagesName")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError {
                Toast.makeText(this, "doOnError", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "doOnError")
            }
            .unsubscribeOn(Schedulers.io())
            .onErrorReturn { t: Throwable ->
                Log.d(TAG, "onErrorReturn : " + t.message)
                arrayOf(Contributors())
            }
            .subscribe { result ->
                if ("User" == result[0].type) {
                    Toast.makeText(this, "Good", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "subscribe good")
                } else {
                    Log.d(TAG, "subscribe bad")
                }
            }
    }
}
