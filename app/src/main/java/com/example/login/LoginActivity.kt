import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*
import androidx.appcompat.app.AlertDialog
import com.example.login.R
import retrofit2.http.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

class LoginActivity : AppCompatActivity() {
    val TAG: String = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // apply edit text decimal input filter
        guardnum_login_edit.inputFilterDecimal(
            maxDigitsIncludingPoint = 11, maxDecimalPlaces = 0)
        guardpass_login_edit . inputFilterDecimal (maxDigitsIncludingPoint =4, maxDecimalPlaces = 0)
        textView.text = "로그인용 핸드폰번호는 '-' 제외 11자리 입력 바랍니다."
        textView.append("\n비밀번호 숫자 4자리 입력바랍니다.") // 로그인 버튼
            button_login.setOnClickListener {
                val intent = Intent(this, CheckActivity::class.java)
                startActivity(intent) } }
// 로그인 성공/실패 시 다이얼로그를 띄워주는 메소드
    fun dialog(type: String){
    var dialog = AlertDialog.Builder(this)
    if(type.equals("success")){
        dialog.setTitle("로그인 성공")
        dialog.setMessage("로그인 성공!") }
    else if(type.equals("fail")){
        dialog.setTitle("로그인 실패")
        dialog.setMessage("아이디와 비밀번호를 확인해주세요") }
    var dialog_listener = object: DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            when(which){ DialogInterface.BUTTON_POSITIVE -> Log.d(TAG, "")
            }
        }
    }
    dialog.setPositiveButton("확인",dialog_listener)
    dialog.show()
    }
    {
        try {
            filters = arrayOf<InputFilter>(
                DecimalDigitsInputFilter(maxDigitsIncludingPoint, maxDecimalPlaces)
            )
        }
        catch (e: PatternSyntaxException)
        {
            isEnabled = false
            hint = e.message
        }
    } // class to decimal digits input filter
     class DecimalDigitsInputFilter(
         maxDigitsIncludingPoint: Int, maxDecimalPlaces: Int ) : InputFilter {
         private val pattern: Pattern = Pattern.compile( "[0-9]{0," + (maxDigitsIncludingPoint - 1) + "}+((\\.[0-9]{" +
                 "0," + (maxDecimalPlaces - 1) + "})?)||(\\.)?" )
         override fun filter( p0: CharSequence?, p1: Int, p2: Int, p3: Spanned?, p4: Int, p5: Int ): CharSequence? {
             p3?.apply {
                 val matcher: Matcher = pattern.matcher(p3)
                 return if (!matcher.matches()) ""
                 else null
             }
             return null
         }
     }
}

