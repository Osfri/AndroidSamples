package com.example.googleloginsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()//파이어베이스 인증 객체
    var googleSignInClient:GoogleSignInClient? = null // 구글 api 클라이언트 객체
    companion object{
        val REQ_SIGN_GOOGLE = 100 //구글 로그인 결과 코드
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val btn:SignInButton = findViewById(R.id.btn_google) //구글 로그인 버튼
        val googlesignOpations = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build() //기본 세팅

        googleSignInClient = GoogleSignIn.getClient(this,googlesignOpations)

        btn.setOnClickListener {
            googleLogin()  //first step
        }
    }
        fun googleLogin(){
            val signinIntent = googleSignInClient?.signInIntent
            startActivityForResult(signinIntent, REQ_SIGN_GOOGLE) //구글서버의 Activity실행 후 ForResult로 다시 돌아옴 REQ코드도 같이 보냄
        }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //로그인 인증 요청하고 결값을 되돌려 받는곳
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == REQ_SIGN_GOOGLE){ //돌아온 코드가 우리가 보냈던 코드인지 확인
                var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data) //구글 Activity를 갔다와서온 결과값
                if (result.isSuccess){ //이 결과값이 성공을 했냐
                    var account = result.signInAccount
                    //second step
                    firebaseAuthWithGoogle(account)
                }
            }
        }

        fun firebaseAuthWithGoogle(account:GoogleSignInAccount?){ //실질적으로 로그인이 성공 한거냐
            var credential = GoogleAuthProvider.getCredential(account?.idToken,null)
            auth?.signInWithCredential(credential)
                ?.addOnCompleteListener{
                    task ->
                    if (task.isSuccessful){ //로그인이 성공했으면
                        val intent = Intent(this,loginAF::class.java)
                        intent.putExtra("Nickname",account?.displayName)
                        intent.putExtra("imgUrl", account?.photoUrl.toString())
                        startActivity(intent)
                    }else{ // 로그인이 실패했으면

                    }
                }

        }
}
